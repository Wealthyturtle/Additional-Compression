package com.wealthyturtle.additionalcompression.blocks;

import java.util.List;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockCompressedComplicated extends BlockCompressed {

	List<Integer> existingLevels;

	public BlockCompressedComplicated(String base, int max, List<Integer> existing) {
		super(base, max);

		existingLevels = existing;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (tab.equals(AdditionalCompression.creativeTabs))
			for (int i = 0; i < maxCompression; i++) {
				if (!existingLevels.contains(i + 1))
					list.add(new ItemStack(this, 1, i));
			}
	}
}