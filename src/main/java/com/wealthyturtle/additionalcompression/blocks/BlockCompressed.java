package com.wealthyturtle.additionalcompression.blocks;

import java.util.List;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockCompressed extends Block {

	public static final PropertyInteger[] levelsArray = {
		PropertyInteger.create("level", 0, 1),
		PropertyInteger.create("level", 0, 2),
		PropertyInteger.create("level", 0, 3),
		PropertyInteger.create("level", 0, 4),
		PropertyInteger.create("level", 0, 5),
		PropertyInteger.create("level", 0, 6),
		PropertyInteger.create("level", 0, 7),
		PropertyInteger.create("level", 0, 8),
		PropertyInteger.create("level", 0, 9),
		PropertyInteger.create("level", 0, 10),
		PropertyInteger.create("level", 0, 11),
		PropertyInteger.create("level", 0, 12),
		PropertyInteger.create("level", 0, 13),
		PropertyInteger.create("level", 0, 14),
		PropertyInteger.create("level", 0, 15),
		PropertyInteger.create("level", 0, 16),
		PropertyInteger.create("level", 0, 17),
		PropertyInteger.create("level", 0, 18),
		PropertyInteger.create("level", 0, 19),
		PropertyInteger.create("level", 0, 20)
	};

	static int maxCompression = 10;
	Float miningSpeed = 3.0F;
	String basicBlock;
	public static final PropertyInteger LEVELS = levelsArray[maxCompression - 2];

	public BlockCompressed(String base, int max) {
		super(Material.ROCK);

		basicBlock = base;
		maxCompression = max;

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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{LEVELS});
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return 3.0F * (getMetaFromState(state) + 2);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxCompression; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
}