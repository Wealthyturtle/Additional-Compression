package com.wealthyturtle.additionalcompression.proxy;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		super.preInit(preEvent);

		for (final CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			for (int i = 0; i < block.maxCompression; i++)
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block.compressedBlock), i, new ModelResourceLocation(block.compressedBlock.getRegistryName(), "level=" + i));
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		super.postInit(postEvent);
	}
}
