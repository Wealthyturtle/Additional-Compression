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

public class BlockCompressedSimple extends Block {

	String basicBlock;

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;

	public BlockCompressedSimple(String base) {
		super(Material.rock);

		basicBlock = base;

		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(AdditionalCompression.creativeTabs);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.blockIcon;
	}

	public float getBlockHardness(World world, int x, int y, int z) {
		return 6.0F;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcon = ir.registerIcon("additionalcompression:" + basicBlock + "_compressed_0");
	}
}