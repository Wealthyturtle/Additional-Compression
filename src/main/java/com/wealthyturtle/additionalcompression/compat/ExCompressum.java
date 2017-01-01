package com.wealthyturtle.additionalcompression.compat;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.BlockCompressedSimple;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressed;
import com.wealthyturtle.additionalcompression.blocks.cobblestone.ItemBlockCompressedSimple;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.registries.HammerRegistry;
import net.blay09.mods.excompressum.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ExCompressum {

	public static void preInit() {

	}

	public static void init() {
		HammerRegistry.register(Blocks.bedrock, 0, Item.getItemFromBlock(Blocks.gravel), 0, 9.0F, 0.0F);
	}

	public static void postInit() {

	}

}
