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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockCompressedSimple extends ItemBlock {

	public ItemBlockCompressedSimple(Block block) {
		super(block);
	}

	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), StatCollector.translateToLocal("compression.level.0.name"));
	}
}