package com.wealthyturtle.additionalcompression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressedSimple;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressedSimple;

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

	public static void registerCompressableBlock(String name, String modID, String itemID, int meta, int max) {
		if (!Loader.isModLoaded(modID) && !modID.equals("minecraft"))
			return;

		String shrunkName = name.replace("item", "").replace("block", "");

		Block compressedBlock;
		if (max == 1) {
			compressedBlock = new BlockCompressedSimple(shrunkName.toLowerCase()).setBlockName("compressed." + shrunkName.toLowerCase());
			GameRegistry.registerBlock(compressedBlock, ItemBlockCompressedSimple.class, "compressed_" + shrunkName.toLowerCase());
		}
		else {
			compressedBlock = new BlockCompressed(shrunkName.toLowerCase(), max).setBlockName("compressed." + shrunkName.toLowerCase());
			GameRegistry.registerBlock(compressedBlock, ItemBlockCompressed.class, "compressed_" + shrunkName.toLowerCase());	
		}
		compressedBlocks.add(new CompressedInfos(name, compressedBlock, modID, itemID, meta, max));
	}

	public static void addComprecipes() {
		for (CompressedInfos block : compressedBlocks) {
			String name = block.compressedName;
			String compressedName = "compressed" + name.substring(0, 1).toUpperCase() + name.substring(1);
			String blockName = "block" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Block compressedBlock = block.compressedBlock;
			Item baseItem = GameRegistry.findItem(block.modID, block.itemID);
			if (name.contains("block")) {
				blockName = name;
				compressedName = name.replace("block", "compressed");
			}

			if (name.contains("item")) {
				blockName = name.replace("item", "block");
				compressedName = name.replace("item", "compressed");
			}

			if (!(OreDictionary.doesOreNameExist(blockName) || blockName == name))
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

		public CompressedInfos(String name, Block block, String mod, String item, int meta, int max) {
			compressedName = name;
			compressedBlock = block;
			modID = mod;
			itemID = item;
			baseMeta = meta;
			maxCompression = max;
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
