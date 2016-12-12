package com.wealthyturtle.additionalcompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION)

public class AdditionalCompression {
	public static final String MODID = "additionalcompression";
	public static final String VERSION = "1.0";

	public static Block compressedCobblestone;

	public static final CreativeTabs creativeTabs = new CreativeTabs(MODID) {
		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(compressedCobblestone);
		}
	};

	@EventHandler
	public void preInit(FMLInitializationEvent preEvent) {
		compressedCobblestone = new BlockCompressed(Material.rock, "cobble").setBlockName("compressed.cobble");
		GameRegistry.registerBlock(compressedCobblestone, ItemBlockCompressed.class, "compressed_cobblestone");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public void postInit(FMLInitializationEvent postEvent) {
	}
}
