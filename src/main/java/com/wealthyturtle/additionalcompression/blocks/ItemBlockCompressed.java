package com.wealthyturtle.additionalcompression.blocks;

import java.util.List;

import com.wealthyturtle.additionalcompression.AdditionalCompression;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockCompressed extends ItemBlockWithMetadata {

	public ItemBlockCompressed(Block block) {
		super(block, block);
	}

	public String getItemStackDisplayName(ItemStack item) {
		return String.format(super.getItemStackDisplayName(item), StatCollector.translateToLocal("compression.level." + (item.getItemDamage() + 1) + ".name"));
	}
}