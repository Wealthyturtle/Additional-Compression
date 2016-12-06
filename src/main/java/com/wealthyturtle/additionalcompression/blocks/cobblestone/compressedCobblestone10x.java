package com.wealthyturtle.additionalcompression.blocks.cobblestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class compressedCobblestone10x extends Block {

	public compressedCobblestone10x(Material material) {
		super(material);
		
		this.setHardness(3.0F);
		this.setResistance(2000000.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(getCreativeTabToDisplayOn().tabBlock);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister) {
		this.blockIcon = iconregister.registerIcon("additionalcompression" + ":" + this.getUnlocalizedName().substring(5));
	}
}
