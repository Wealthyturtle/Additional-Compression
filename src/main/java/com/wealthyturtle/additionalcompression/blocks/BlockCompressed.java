package com.wealthyturtle.additionalcompression.blocks;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockCompressed extends Block {

	int maxCompression;
	String basicBlock;
	public final PropertyInteger LEVELS;

	public BlockCompressed(String base, int max, PropertyInteger propLevels) {
		super(Material.ROCK);

		basicBlock = base;
		maxCompression = max;
		LEVELS = propLevels;

		setHardness(6.0F);
		setCreativeTab(AdditionalCompression.creativeTabs);
		setUnlocalizedName("compressed." + base.toLowerCase());
		setRegistryName(base.toLowerCase() + "_compressed");
		setDefaultState(blockState.getBaseState().withProperty(LEVELS, 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LEVELS);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LEVELS, meta);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
	}

	@Override
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return 3.0F * (getMetaFromState(state) + 2);
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab.equals(AdditionalCompression.creativeTabs))
			for (int i = 0; i < maxCompression; i++) {
				items.add(new ItemStack(this, 1, i));
			}
	}
}