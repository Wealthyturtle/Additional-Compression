package com.wealthyturtle.additionalcompression;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.wealthyturtle.additionalcompression.compat.ExCompressum;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION, dependencies = "after:excompressum;after:exnihilo")

public class AdditionalCompression {

	//@SidedProxy(clientSide = "com.wealthyturtle.additionalcompression.proxy.ClientProxy", serverSide = "com.wealthyturtle.additionalcompression.proxy.CommonProxy")
	//public static CommonProxy proxy;
	public static final String MODID = "additionalcompression";
	public static final String VERSION = "3.0";

	public static final CreativeTabs creativeTabs = new CreativeTabs(MODID) {
		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(CompressedBlockRegistry.compressedBlocks.get(0).compressedBlock);
		}

		@Override
		public int func_151243_f() {
			int firstMeta = 0;
			for (int i = 0; i < CompressedBlockRegistry.compressedBlocks.get(0).maxCompression; i++) {
				if (!CompressedBlockRegistry.compressedBlocks.get(0).existingLevels.contains(i + 1)) {
					firstMeta = i;
					break;
				}
			}
			return firstMeta;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		//proxy.preInit(preEvent);

		ConfigHandler.init(preEvent.getSuggestedConfigurationFile());

		for (String entry : ConfigHandler.existingBlocksList) {
			String[] entries = entry.split(":");
			CompressedBlockRegistry.registerExistingBlock(entries[0], entries[1], entries[2], Integer.parseInt(entries[3]), Integer.parseInt(entries[4]));
		}
		for (String entry : ConfigHandler.compressedBlocksWhitelist) {
			String[] entries = entry.split(":");
			CompressedBlockRegistry.registerCompressableBlock(entries[0], entries[1], entries[2], Integer.parseInt(entries[3]), Integer.parseInt(entries[4]));
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//proxy.init(event);
		CompressedBlockRegistry.addComprecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		//proxy.postInit(postEvent);
		//CompressedBlockRegistry.addComprecipesPostInit();
		if (Loader.isModLoaded("excompressum") && ConfigHandler.exCompressum)
			ExCompressum.exComprecipes();
	}
}
