package com.wealthyturtle.additionalcompression.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockCompressedSimple extends ItemBlock {

	public ItemBlockCompressedSimple(Block block) {
		super(block);
	}

	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), StatCollector.translateToLocal("compression.level.0.name"));
	}
}