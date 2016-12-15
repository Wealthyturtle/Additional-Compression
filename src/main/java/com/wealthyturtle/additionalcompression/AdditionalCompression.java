package com.wealthyturtle.additionalcompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
		CompressedBlockRegistry.registerCompressableBlock("cobblestone", "minecraft", "cobblestone", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("stone", "minecraft", "stone", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("gravel", "minecraft", "gravel", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("sand", "minecraft", "sand", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("dirt", "minecraft", "dirt", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("clay", "minecraft", "clay", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("cobblestoneMossy", "minecraft", "mossy_cobblestone", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("flint", "minecraft", "flint", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("netherrack", "minecraft", "netherrack", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("soulsand", "minecraft", "soul_sand", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("endstone", "minecraft", "end_stone", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("dust", "exnihilo", "dust", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("gravelNether", "exnihilo", "exnihilo.gravel_nether", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("gravelEnd", "exnihilo", "exnihilo.gravel_ender", 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("cropCarrot", "minecraft", "carrot", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("netherstar", "minecraft", "nether_star", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("charcoal", "minecraft", "coal", 1, 1);
		CompressedBlockRegistry.registerCompressableBlock("apple", "minecraft", "apple", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("bread", "minecraft", "bread", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("porkchop", "minecraft", "porkchop", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("fish", "minecraft", "fish", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cookie", "minecraft", "cookie", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("melon", "minecraft", "melon", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("beef", "minecraft", "beef", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("chicken", "minecraft", "chicken", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("rottenflesh", "minecraft", "rotten_flesh", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("spidereye", "minecraft", "spider_eye", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropPotato", "minecraft", "potato", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("string", "minecraft", "string", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("feather", "minecraft", "feather", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("dustGunpowder", "minecraft", "gunpowder", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropWheat", "minecraft", "wheat", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("leather", "minecraft", "leather", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropSugarcane", "minecraft", "reeds", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("egg", "minecraft", "egg", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("dustSugar", "minecraft", "sugar", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("blaze", "minecraft", "blaze_rod", 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("pearlEnder", "minecraft", "ender_pearl", 0, 1);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		CompressedBlockRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	}
}
