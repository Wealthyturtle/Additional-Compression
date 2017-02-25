package com.wealthyturtle.additionalcompression.client.gui;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.AdditionalCompression;
import com.wealthyturtle.additionalcompression.ConfigHandler;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(parentScreen), AdditionalCompression.MODID, false, false, "gui." + AdditionalCompression.MODID + ".config.title");
	}

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(GuiScreen parent) {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		list.add(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.compressedBlocks.toLowerCase())));
		list.add(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.compatibility.toLowerCase())));

		return list;
	}
}