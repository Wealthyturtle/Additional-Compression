package com.wealthyturtle.additionalcompression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressedSimple;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompressedBlockRegistry {

	static List<CompressedInfos> compressedBlocks= new ArrayList<CompressedInfos>();
	static List<CompressedInfos> compressedBlocksSimple= new ArrayList<CompressedInfos>();

	public static void registerCompressableBlock(String name, Item base, int meta, int max) {
		Block compressedBlock = new BlockCompressed(name.toLowerCase(), max).setBlockName("compressed." + name.toLowerCase());
		if (max == 1) {
			GameRegistry.registerBlock(compressedBlock, ItemBlockCompressedSimple.class, "compressed_" + name.toLowerCase());
			compressedBlocksSimple.add(new CompressedInfos(name, compressedBlock, base, meta));
		}
		else {
			GameRegistry.registerBlock(compressedBlock, ItemBlockCompressed.class, "compressed_" + name.toLowerCase());	
			compressedBlocks.add(new CompressedInfos(name, compressedBlock, base, meta));
		};
	}

	public static void init() {
		for (CompressedInfos block : compressedBlocks) {
			String name = block.compressedName;
			String compressedName = "compressed" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Block compressedBlock = block.compressedBlock;

			OreDictionary.registerOre("block" + name.substring(0, 1).toUpperCase() + name.substring(1), new ItemStack(compressedBlock, 1, 0));
			OreDictionary.registerOre(compressedName + "1x", new ItemStack(compressedBlock, 1, 0));
			OreDictionary.registerOre(compressedName + "2x", new ItemStack(compressedBlock, 1, 1));
			OreDictionary.registerOre(compressedName + "3x", new ItemStack(compressedBlock, 1, 2));
			OreDictionary.registerOre(compressedName + "4x", new ItemStack(compressedBlock, 1, 3));
			OreDictionary.registerOre(compressedName + "5x", new ItemStack(compressedBlock, 1, 4));
			OreDictionary.registerOre(compressedName + "6x", new ItemStack(compressedBlock, 1, 5));
			OreDictionary.registerOre(compressedName + "7x", new ItemStack(compressedBlock, 1, 6));
			OreDictionary.registerOre(compressedName + "8x", new ItemStack(compressedBlock, 1, 7));
			OreDictionary.registerOre(compressedName + "9x", new ItemStack(compressedBlock, 1, 8));
			OreDictionary.registerOre(compressedName + "10x", new ItemStack(compressedBlock, 1, 9));

			if (OreDictionary.doesOreNameExist(name))
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', name));
			else
				GameRegistry.addRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', new ItemStack(block.baseItem, 1, block.baseMeta));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 1), "XXX", "XXX", "XXX", 'X', compressedName + "1x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 2), "XXX", "XXX", "XXX", 'X', compressedName + "2x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 3), "XXX", "XXX", "XXX", 'X', compressedName + "3x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 4), "XXX", "XXX", "XXX", 'X', compressedName + "4x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 5), "XXX", "XXX", "XXX", 'X', compressedName + "5x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 6), "XXX", "XXX", "XXX", 'X', compressedName + "6x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 7), "XXX", "XXX", "XXX", 'X', compressedName + "7x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 8), "XXX", "XXX", "XXX", 'X', compressedName + "8x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 9), "XXX", "XXX", "XXX", 'X', compressedName + "9x"));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block.baseItem, 9, block.baseMeta), "X", 'X', compressedName + "1x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 0), "X", 'X', compressedName + "2x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 1), "X", 'X', compressedName + "3x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 2), "X", 'X', compressedName + "4x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 3), "X", 'X', compressedName + "5x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 4), "X", 'X', compressedName + "6x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 5), "X", 'X', compressedName + "7x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 6), "X", 'X', compressedName + "8x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 7), "X", 'X', compressedName + "9x"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 9, 8), "X", 'X', compressedName + "10x"));
		};

		for (CompressedInfos block : compressedBlocksSimple) {
			String name = block.compressedName;
			String compressedName = "block" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Block compressedBlock = block.compressedBlock;

			OreDictionary.registerOre(compressedName, new ItemStack(compressedBlock, 1, 0));
			OreDictionary.registerOre("compressed" + name.substring(0, 1).toUpperCase() + name.substring(1) + "1x", new ItemStack(compressedBlock, 1, 0));

			if (OreDictionary.doesOreNameExist(name))
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', name));
			else
				GameRegistry.addRecipe(new ItemStack(compressedBlock, 1, 0), "XXX", "XXX", "XXX", 'X', new ItemStack(block.baseItem, 1, block.baseMeta));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block.baseItem, 9, block.baseMeta), "X", 'X', compressedName));
		};
	}

	public static class CompressedInfos {
		public String compressedName;
		public Block compressedBlock;
		public Item baseItem;
		public int baseMeta;

		public CompressedInfos(String name, Block block, Item base, int meta) {
			compressedName = name;
			compressedBlock = block;
			baseItem = base;
			baseMeta = meta;
		}
	}
}
