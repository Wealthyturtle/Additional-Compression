package com.wealthyturtle.additionalcompression.blocks.cobblestone;

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

public class BlockCompressed extends Block {

	int maxCompression = 10;
	Float miningSpeed = 3.0F;
	String basicBlock;

	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcons;

	public BlockCompressed(String base, int max) {
		super(Material.rock);

		basicBlock = base;
		maxCompression = max;

		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(AdditionalCompression.creativeTabs);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= maxCompression) {
			meta = 0;
		}

		return this.blockIcons[meta];
	}

	public int damageDropped(int meta) {
		return meta;
	}

	public float getBlockHardness(World world, int x, int y, int z) {
		return 3.0F * (world.getBlockMetadata(x, y, z) + 2);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxCompression; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcons = new IIcon[maxCompression];

		for (int x = 0; x < maxCompression; x++) {
			blockIcons[x] = ir.registerIcon("additionalcompression:" + basicBlock + "_compressed_" + x);
		}
	}
}