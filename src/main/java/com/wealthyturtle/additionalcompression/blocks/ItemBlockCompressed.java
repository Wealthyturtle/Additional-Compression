package com.wealthyturtle.additionalcompression.blocks;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockCompressed extends ItemBlock {

	public ItemBlockCompressed(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	public int getMetadata(int damage)
	{
		return damage;
	}

	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), I18n.translateToLocal("compression.level." + (item.getItemDamage() + 1) + ".name"));
	}
}