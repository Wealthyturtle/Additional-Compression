package com.wealthyturtle.additionalcompression.blocks;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCompressedSimple extends Block {

	String basicBlock;

	public BlockCompressedSimple(String base) {
		super(Material.ROCK);

		basicBlock = base;

		setHardness(6.0F);
		setCreativeTab(AdditionalCompression.creativeTabs);
		setUnlocalizedName("compressed." + base.toLowerCase());
		setRegistryName(base.toLowerCase() + "_compressed");
		setDefaultState(blockState.getBaseState().withProperty(BlockCompressed.levelsArray[0], 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess idkWhatThisIs, BlockPos pos) {
		return getDefaultState();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{BlockCompressed.levelsArray[0]});
	}
}