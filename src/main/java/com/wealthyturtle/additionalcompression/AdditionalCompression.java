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
		CompressedBlockRegistry.registerCompressableBlock("cobblestone", Item.getItemFromBlock(Blocks.cobblestone), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("stone", Item.getItemFromBlock(Blocks.stone), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("gravel", Item.getItemFromBlock(Blocks.gravel), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("sand", Item.getItemFromBlock(Blocks.sand), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("dirt", Item.getItemFromBlock(Blocks.dirt), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("clay", Item.getItemFromBlock(Blocks.clay), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("cobblestoneMossy", Item.getItemFromBlock(Blocks.mossy_cobblestone), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("flint", Items.flint, 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("netherrack", Item.getItemFromBlock(Blocks.netherrack), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("soulsand", Item.getItemFromBlock(Blocks.soul_sand), 0, 10);
		CompressedBlockRegistry.registerCompressableBlock("endstone", Item.getItemFromBlock(Blocks.end_stone), 0, 10);
		if (Loader.isModLoaded("exnihilo")) {
			CompressedBlockRegistry.registerCompressableBlock("dust", GameRegistry.findItem("exnihilo", "dust"), 0, 10);
			CompressedBlockRegistry.registerCompressableBlock("gravelNether", GameRegistry.findItem("exnihilo", "exnihilo.gravel_nether"), 0, 10);
			CompressedBlockRegistry.registerCompressableBlock("gravelEnd", GameRegistry.findItem("exnihilo", "exnihilo.gravel_ender"), 0, 10);
		};
		CompressedBlockRegistry.registerCompressableBlock("cropCarrot", Items.carrot, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("netherstar", Items.nether_star, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("charcoal", Items.coal, 1, 1);
		CompressedBlockRegistry.registerCompressableBlock("apple", Items.apple, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("bread", Items.bread, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("porkchop", Items.porkchop, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("fish", Items.fish, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cookie", Items.cookie, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("melon", Items.melon, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("beef", Items.beef, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("chicken", Items.chicken, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("rottenflesh", Items.rotten_flesh, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("spidereye", Items.spider_eye, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropPotato", Items.potato, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("string", Items.string, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("feather", Items.feather, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("dustGunpowder", Items.gunpowder, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropWheat", Items.wheat, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("leather", Items.leather, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("cropSugarcane", Items.reeds, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("egg", Items.egg, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("dustSugar", Items.sugar, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("blaze", Items.blaze_rod, 0, 1);
		CompressedBlockRegistry.registerCompressableBlock("pearlEnder", Items.ender_pearl, 0, 1);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		CompressedBlockRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	}
}
