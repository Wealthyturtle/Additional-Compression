package com.wealthyturtle.additionalcompression.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCompressedComplicated extends BlockCompressed {

	int maxCompression = 10;
	Float miningSpeed = 3.0F;
	String basicBlock;
	List<Integer> existingLevels;

	public BlockCompressedComplicated(String base, int max, List<Integer> existing) {
		super(base, max);

		basicBlock = base;
		maxCompression = max;
		existingLevels = existing;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxCompression; i++) {
			if (!existingLevels.contains(i + 1))
				list.add(new ItemStack(item, 1, i));
		}
	}
}