package com.wealthyturtle.additionalcompression.blocks;

import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCompressedComplicated extends BlockCompressed {

	int maxCompression = 10;
	Float miningSpeed = 3.0F;
	String basicBlock;
	List<Integer> existingLevels;

	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcons;

	public BlockCompressedComplicated(String base, int max, List<Integer> existing) {
		super(base, max);

		basicBlock = base;
		maxCompression = max;
		existingLevels = existing;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= maxCompression || existingLevels.contains(meta + 1)) {
			meta = 0;
		}

		return this.blockIcons[meta];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxCompression; i++) {
			if (!existingLevels.contains(i + 1))
				list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcons = new IIcon[maxCompression];
		for (int x = 0; x < maxCompression; x++) {
			if (!existingLevels.contains(x + 1))
				blockIcons[x] = ir.registerIcon("additionalcompression:" + basicBlock + "_compressed_" + x);
		}
	}
}