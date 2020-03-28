package com.wealthyturtle.additionalcompression.blocks;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockCompressedSimple extends Block {

	String basicBlock;
	public static final PropertyEnum LEVEL = PropertyEnum.create("level", singleLevel.class);

	public BlockCompressedSimple(String base) {
		super(Material.ROCK);

		basicBlock = base;

		setHardness(6.0F);
		setCreativeTab(AdditionalCompression.creativeTabs);
		setUnlocalizedName("compressed." + base.toLowerCase());
		setRegistryName(base.toLowerCase() + "_compressed");
		setDefaultState(blockState.getBaseState().withProperty(LEVEL, singleLevel.ZERO));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{LEVEL});
	}

	public enum singleLevel implements IStringSerializable {
		ZERO("0");

		public final String name;

		singleLevel(String level) {
			name = level;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}