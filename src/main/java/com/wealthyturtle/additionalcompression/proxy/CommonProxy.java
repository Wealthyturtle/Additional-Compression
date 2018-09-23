package com.wealthyturtle.additionalcompression.proxy;

import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.ConfigHandler;
import com.wealthyturtle.additionalcompression.compat.ExCompressum;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public static List<ItemBlock> blocks = new ArrayList<ItemBlock>();
	
	public static ExCompressum compressum = new ExCompressum();

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
		if (Loader.isModLoaded("excompressum") && ConfigHandler.exCompressum)
			MinecraftForge.EVENT_BUS.register(compressum);
	}

	public void init(FMLInitializationEvent event) {
		CompressedBlockRegistry.addComprecipes();
	}

	public void postInit(FMLPostInitializationEvent postEvent) {
		if (Loader.isModLoaded("excompressum") && ConfigHandler.exCompressum)
			compressum.exComprecipes();
	}

	public void registerBlocks(RegistryEvent.Register<Block> event) {
		for (ItemBlock block : blocks)
			event.getRegistry().register(block.getBlock());
	}
	
	public void registerItems(RegistryEvent.Register<Item> event) {
		for (ItemBlock block : blocks)
			event.getRegistry().register(block);
	}
}
