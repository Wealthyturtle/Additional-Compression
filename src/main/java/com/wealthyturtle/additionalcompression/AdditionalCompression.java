package com.wealthyturtle.additionalcompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION)

public class AdditionalCompression {
	public static final String MODID = "additionalcompression";
	public static final String VERSION = "1.0";

	public static final CreativeTabs creativeTabs = new CreativeTabs(MODID) {
		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(CompressedBlockRegistry.compressedBlocks.get(0).compressedBlock);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		CompressedBlockRegistry.registerCompressableBlock("cobblestone", Blocks.cobblestone, 0);
		CompressedBlockRegistry.registerCompressableBlock("stone", Blocks.stone, 0);
		CompressedBlockRegistry.registerCompressableBlock("gravel", Blocks.gravel, 0);
		CompressedBlockRegistry.registerCompressableBlock("sand", Blocks.sand, 0);
		CompressedBlockRegistry.registerCompressableBlock("dirt", Blocks.dirt, 0);
		CompressedBlockRegistry.registerCompressableBlock("clay", Blocks.clay, 0);
		CompressedBlockRegistry.registerCompressableBlock("cobblemossy", Blocks.mossy_cobblestone, 0);
		CompressedBlockRegistry.registerCompressableBlock("netherrack", Blocks.netherrack, 0);
		CompressedBlockRegistry.registerCompressableBlock("soulsand", Blocks.soul_sand, 0);
		CompressedBlockRegistry.registerCompressableBlock("endstone", Blocks.end_stone, 0);
		if (Loader.isModLoaded("exnihilo"))
			CompressedBlockRegistry.registerCompressableBlock("dust", GameRegistry.findBlock("exnihilo", "dust"), 0);

		CompressedBlockRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	}
}
