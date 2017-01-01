package com.wealthyturtle.additionalcompression;

import net.blay09.mods.excompressum.registry.CompressedHammerRegistry;
import net.blay09.mods.excompressum.registry.HeavySieveRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;
import com.wealthyturtle.additionalcompression.proxy.CommonProxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exnihilo.registries.HammerRegistry;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION)

public class AdditionalCompression {

    //@SidedProxy(clientSide = "com.wealthyturtle.additionalcompression.proxy.ClientProxy", serverSide = "com.wealthyturtle.additionalcompression.proxy.CommonProxy")
    //public static CommonProxy proxy;
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
		//proxy.preInit(preEvent);

		ConfigHandler.init(preEvent.getSuggestedConfigurationFile());

		for (String entry : ConfigHandler.compressedBlocksWhitelist) {
			String[] entries = entry.split(":");

			CompressedBlockRegistry.registerCompressableBlock(entries[0], entries[1], entries[2], Integer.parseInt(entries[3]), Integer.parseInt(entries[4]));
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//proxy.init(event);
		//CompressedHammerRegistry.register(Blocks.bedrock, 0, Item.getItemFromBlock(Blocks.gravel), 0, 9.0F, 0.0F);
		//HeavySieveRegistry.register(Blocks.bedrock, 0, Item.getItemFromBlock(Blocks.gravel), 0, 9);
		CompressedBlockRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		//proxy.postInit(postEvent);
	}
}
