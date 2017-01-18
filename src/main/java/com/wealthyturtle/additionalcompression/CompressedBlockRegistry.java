package com.wealthyturtle.additionalcompression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.ExistingInfos;
import com.wealthyturtle.additionalcompression.blocks.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.BlockCompressedComplicated;
import com.wealthyturtle.additionalcompression.blocks.BlockCompressedSimple;
import com.wealthyturtle.additionalcompression.blocks.ItemBlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.ItemBlockCompressedSimple;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompressedBlockRegistry {

	public static List<CompressedInfos> compressedBlocks = new ArrayList<CompressedInfos>();
	public static Map<String, List<ExistingInfos>> existingBlocks = new HashMap<String, List<ExistingInfos>>();


	public static void registerExistingBlock(String name, String modID, String itemID, int meta, int max) {
		if (!Loader.isModLoaded(modID) && !modID.equals("minecraft"))
			return;

		if (existingBlocks.containsKey(name)) {
			existingBlocks.get(name).add(new ExistingInfos(modID, itemID, meta, max));
		} else {
			List<ExistingInfos> arr = new ArrayList<>();
			arr.add(new ExistingInfos(modID, itemID, meta, max));
			existingBlocks.put(name, arr);
		}
	}

	public static void registerCompressableBlock(String name, String modID, String itemID, int meta, int max) {
		if (!Loader.isModLoaded(modID) && !modID.equals("minecraft"))
			return;

		Block compressedBlock;
		List<Integer> existingLevels = new ArrayList<Integer>();

		if (existingBlocks.containsKey(name)) {
			List<ExistingInfos> infoList = existingBlocks.get(name);

			int dontBother = 0;
			for (ExistingInfos info : infoList) {
				existingLevels.add(info.compressionLevel);

				for (int i = 0; i < max; i++) {
					if (info.compressionLevel == i)
						dontBother++;
				}
			}
			if (dontBother > max)
				return;

			compressedBlock = new BlockCompressedComplicated(name.toLowerCase(), max, existingLevels).setBlockName("compressed." + name.toLowerCase());
			GameRegistry.registerBlock(compressedBlock, ItemBlockCompressed.class, "compressed_" + name.toLowerCase());	


		} else {
			if (max == 1) {
				compressedBlock = new BlockCompressedSimple(name.toLowerCase()).setBlockName("compressed." + name.toLowerCase());
				GameRegistry.registerBlock(compressedBlock, ItemBlockCompressedSimple.class, "compressed_" + name.toLowerCase());
			}
			else {
				compressedBlock = new BlockCompressed(name.toLowerCase(), max).setBlockName("compressed." + name.toLowerCase());
				GameRegistry.registerBlock(compressedBlock, ItemBlockCompressed.class, "compressed_" + name.toLowerCase());	
			}
		}
		compressedBlocks.add(new CompressedInfos(name, compressedBlock, modID, itemID, meta, max, existingLevels));
	}

	public static void addComprecipes() {
		for (CompressedInfos block : compressedBlocks) {
			String name = block.compressedName;
			String compressedName = "compressed" + name.substring(0, 1).toUpperCase() + name.substring(1);
			String blockName = "block" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Block compressedBlock = block.compressedBlock;
			Item baseItem = GameRegistry.findItem(block.modID, block.itemID);
			Boolean itsComplicated = existingBlocks.containsKey(name);

			String shrunkName = shrinkName(name);
			if (shrunkName != name &&
					(name.startsWith("block") ||
							name.startsWith("item") ||
							name.startsWith("ingot") ||
							name.startsWith("gem") ||
							name.startsWith("dust"))) {
				compressedName = "compressed" + shrunkName;
				blockName = "block" + shrunkName;
			}

			if(itsComplicated) {
				complicatedComprecipe(block, name, blockName, compressedName, compressedBlock, baseItem);
				continue;
			}

			if (!OreDictionary.doesOreNameExist(blockName) && blockName != name)
				OreDictionary.registerOre(blockName, new ItemStack(compressedBlock, 1, 0));

			if (OreDictionary.doesOreNameExist(name))
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', name));
			else
				GameRegistry.addRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', new ItemStack(baseItem, 1, block.baseMeta));

			//GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(baseItem, 9, block.baseMeta), "X", 'X', blockName));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(baseItem, 9, block.baseMeta), "X", 'X', compressedName + "1x"));

			//if (GameRegistry.getFuelValue(new ItemStack(baseItem, 1, block.baseMeta)) != 0) {
			//GameRegistry.registerFuelHandler(new CompressedBlockFuelHandler(new ItemStack(compressedBlock, 1, 0).getItem(), baseItem, block.baseMeta));
			//}
			//System.out.println(GameRegistry.getFuelValue(new ItemStack(baseItem, 1, block.baseMeta)));

			//if (block.maxCompression > 1)
			//GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 1), "XXX", "XXX", "XXX", 'X', blockName));

			for (int i = 0; i < block.maxCompression; i++) {
				OreDictionary.registerOre(compressedName + (i + 1) + "x", new ItemStack(compressedBlock, 1, i));

				if (i < block.maxCompression - 1) {
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, i + 1), "XXX", "XXX", "XXX", 'X', compressedName + (i + 1) + "x"));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, i), "X", 'X', compressedName + (i + 2) + "x"));
				}
			}
		}
	}

	public static void complicatedComprecipe(CompressedInfos block, String name, String blockName, String compressedName, Block compressedBlock, Item baseItem) {
		List<ExistingInfos> infoList = existingBlocks.get(name);

		if (!block.existingLevels.contains(1)) {
			if (!OreDictionary.doesOreNameExist(blockName) && blockName != name)
				OreDictionary.registerOre(blockName, new ItemStack(compressedBlock, 1, 0));

			if (OreDictionary.doesOreNameExist(name))
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', name));
			else
				GameRegistry.addRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', new ItemStack(baseItem, 1, block.baseMeta));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(baseItem, 9, block.baseMeta), "X", 'X', compressedName + "1x"));
		}

		for (int i = 0; i < block.maxCompression; i++) {
			if (block.existingLevels.contains(i + 1)) {
				if (block.existingLevels.contains(i + 2))
					continue;

				ExistingInfos existing = infoList.get(0);
				for (ExistingInfos info : infoList){
					if (info.compressionLevel == i + 1) {
						existing = info;
						break;
					}
				}
				Item existingItem = GameRegistry.findItem(existing.modID, existing.itemID);

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(existingItem, 9, existing.itemMeta), "X", 'X', compressedName + (i + 2) + "x"));
				if (i < block.maxCompression - 1) {
					GameRegistry.addRecipe(new ItemStack(compressedBlock, 1, i + 1), "XXX", "XXX", "XXX", 'X', new ItemStack(existingItem, 1, existing.itemMeta));
				}
			} else {
				OreDictionary.registerOre(compressedName + (i + 1) + "x", new ItemStack(compressedBlock, 1, i));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, i), "X", 'X', compressedName + (i + 2) + "x"));
				if (i < block.maxCompression - 1) {
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, i + 1), "XXX", "XXX", "XXX", 'X', compressedName + (i + 1) + "x"));
				}
			}
		}
	}

	public static String shrinkName(String fullName) {
		String[] splitName = StringUtils.splitByCharacterTypeCamelCase(fullName);
		if (splitName[0] != fullName && splitName.length > 1) {
			String shrunkName = "";
			for (int i = 1; i < splitName.length; i++) {
				shrunkName = shrunkName + splitName[i];
			}
			if (shrunkName != "")
				return shrunkName;
		}
		return fullName;
	}

	/*public static void addComprecipesPostInit() {
		for (CompressedInfos block : compressedBlocks) {
			Block compressedBlock = block.compressedBlock;
			Item baseItem = GameRegistry.findItem(block.modID, block.itemID);
			Integer baseburn = GameRegistry.getFuelValue(new ItemStack(baseItem, 1, block.baseMeta));
			if (baseburn != 0) {
				GameRegistry.registerFuelHandler(new CompressedBlockFuelHandler(new ItemStack(compressedBlock, 1, 0).getItem(), baseItem, block.baseMeta));
			}
			System.out.println(baseburn);
		}
	}*/

	public static class CompressedInfos {
		public String compressedName;
		public Block compressedBlock;
		public String modID;
		public String itemID;
		public int baseMeta;
		public int maxCompression;
		public List<Integer> existingLevels;

		public CompressedInfos(String name, Block block, String mod, String item, int meta, int max, List<Integer> existing) {
			compressedName = name;
			compressedBlock = block;
			modID = mod;
			itemID = item;
			baseMeta = meta;
			maxCompression = max;
			existingLevels = existing;
		}
	}

	public static class ExistingInfos {
		public String modID;
		public String itemID;
		public int itemMeta;
		public int compressionLevel;

		public ExistingInfos(String mod, String item, int meta, int level) {
			modID = mod;
			itemID = item;
			itemMeta = meta;
			compressionLevel = level;
		}
	}

	/*private static class CompressedBlockFuelHandler implements IFuelHandler {
		private final Item compressedBlock;
		private final Item baseItem;
		private final int baseMeta;

		private CompressedBlockFuelHandler(final Item compressedBlock, final Item baseItem, final int baseMeta) {
			this.compressedBlock = compressedBlock;
			this.baseItem = baseItem;
			this.baseMeta = baseMeta;
		}

		@Override
		public int getBurnTime(final ItemStack fuel) {
			//System.out.println("hoi");
			if (fuel == null || fuel.getItem() != compressedBlock)
				return 0;

			int burnTime = (int) (GameRegistry.getFuelValue(new ItemStack(baseItem, 1, baseMeta)) * Math.pow(10, (fuel.getItemDamage() + 1)));
			System.out.println(burnTime);
			return burnTime != Integer.MAX_VALUE ? burnTime : 0;
		}
	}*/
}
