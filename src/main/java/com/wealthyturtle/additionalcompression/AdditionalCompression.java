package com.wealthyturtle.additionalcompression;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.wealthyturtle.additionalcompression.proxy.CommonProxy;

@Mod(modid = AdditionalCompression.MODID, version = AdditionalCompression.VERSION, dependencies = "after:excompressum;after:exnihilocreatio")

public class AdditionalCompression {

	@SidedProxy(clientSide = "com.wealthyturtle.additionalcompression.proxy.ClientProxy", serverSide = "com.wealthyturtle.additionalcompression.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static final String MODID = "additionalcompression";
	public static final String VERSION = "3.0";

	public static final CreativeTabs creativeTabs = new CreativeTabs(MODID) {
		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack getTabIconItem() {
			int firstMeta = 0;
			for (int i = 0; i < CompressedBlockRegistry.compressedBlocks.get(0).maxCompression; i++) {
				if (!CompressedBlockRegistry.compressedBlocks.get(0).existingLevels.contains(i + 1)) {
					firstMeta = i;
					break;
				}
			}
			return new ItemStack(CompressedBlockRegistry.compressedBlocks.get(0).compressedBlock, 1, firstMeta);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		MinecraftForge.EVENT_BUS.register(this);
		proxy.preInit(preEvent);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		proxy.postInit(postEvent);
	}

	@SubscribeEvent
	public void blockRegistry(final RegistryEvent.Register<Block> event) {
		proxy.registerBlocks(event);
	}

	@SubscribeEvent
	public void itemRegistry(final RegistryEvent.Register<Item> event) {
		proxy.registerItems(event);
	}
}
