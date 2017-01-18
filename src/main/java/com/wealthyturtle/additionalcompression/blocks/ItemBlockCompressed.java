package com.wealthyturtle.additionalcompression.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockCompressed extends ItemBlockWithMetadata {

	public ItemBlockCompressed(Block block) {
		super(block, block);
	}

	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), StatCollector.translateToLocal("compression.level." + (item.getItemDamage() + 1) + ".name"));
	}
}