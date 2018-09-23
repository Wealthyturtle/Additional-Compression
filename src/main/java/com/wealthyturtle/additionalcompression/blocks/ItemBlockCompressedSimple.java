package com.wealthyturtle.additionalcompression.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockCompressedSimple extends ItemBlock {

	public int burnTime = -1;

	public ItemBlockCompressedSimple(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), I18n.translateToLocal("compression.level.0.name"));
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		if (burnTime == -1)
			return burnTime;
		return (int) (burnTime * Math.pow(10, stack.getMetadata() + 1));
	}
}