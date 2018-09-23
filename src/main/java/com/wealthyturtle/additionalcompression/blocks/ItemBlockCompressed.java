package com.wealthyturtle.additionalcompression.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockCompressed extends ItemBlock {

	public int burnTime = -1;

	public ItemBlockCompressed(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), I18n.translateToLocal("compression.level." + (item.getItemDamage() + 1) + ".name"));
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		this.block.getSubBlocks(tab, items);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		if (burnTime == -1)
			return burnTime;
		return (int) (burnTime * Math.pow(10, stack.getMetadata() + 1));
	}
}