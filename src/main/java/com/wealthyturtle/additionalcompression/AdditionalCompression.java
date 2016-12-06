package com.wealthyturtle.additionalcompression; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.wealthyturtle.additionalcompression.blocks.cobblestone.compressedCobblestone9x;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION)

public class AdditionalCompression
{
    public static final String MODID = "additionalcompression";
    public static final String VERSION = "1.0";
    
    public static Block compressedCobblestone9x;
    
    @EventHandler
    public void preInit(FMLInitializationEvent preEvent)
    {
    	compressedCobblestone9x = new compressedCobblestone9x(Material.rock).setBlockName("compressedCobblestone9x");
    	GameRegistry.registerBlock(compressedCobblestone9x, "NonupleCompressedCobblestone");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
