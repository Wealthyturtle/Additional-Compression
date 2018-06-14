package com.wealthyturtle.additionalcompression.proxy;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.ConfigHandler;
import com.wealthyturtle.additionalcompression.compat.ExCompressum;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent preEvent) {
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

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent postEvent) {
		CompressedBlockRegistry.addComprecipes();
		//CompressedBlockRegistry.addComprecipesPostInit();
		if (Loader.isModLoaded("excompressum") && ConfigHandler.exCompressum)
			ExCompressum.exComprecipes();
	}
}
