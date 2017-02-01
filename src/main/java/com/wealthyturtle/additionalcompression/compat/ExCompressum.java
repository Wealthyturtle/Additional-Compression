package com.wealthyturtle.additionalcompression.compat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;
import com.wealthyturtle.additionalcompression.ConfigHandler;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftingResult;
import exnihilo.registries.helpers.Smashable;
import net.blay09.mods.excompressum.registry.AutoSieveSkinRegistry;
import net.blay09.mods.excompressum.registry.CompressedHammerRegistry;
import net.blay09.mods.excompressum.registry.HeavySieveRegistry;
import net.blay09.mods.excompressum.registry.data.ItemAndMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ExCompressum {

	public static void exComprecipes() {
		addHammering();
		addSifting();

		if (Loader.isModLoaded("MineTweaker3"))
			new MineTweaker();

		Object skinRegistry = new AutoSieveSkinRegistry();
		try {
			Field f = skinRegistry.getClass().getDeclaredField("availableSkins");
			f.setAccessible(true);
			try {
				List<String> availableSkins = (List<String>) f.get(skinRegistry);
				availableSkins.add("RCXcrafter");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void addSifting() {
		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block compressedBlock = block.compressedBlock;
			Block baseBlock = GameRegistry.findBlock(block.modID, block.itemID);
			ArrayList<SiftingResult> siftResults = SieveRegistry.getSiftingOutput(baseBlock, block.baseMeta);

			if (siftResults == null)
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxSifting); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;

				for (SiftingResult result : siftResults) {
					for (int i = 0; i < (Math.pow(6, m + 1)); i++) {
						HeavySieveRegistry.getSiftables().put(new ItemAndMetadata(compressedBlock, m), new SiftingResult(result.item, result.meta, result.rarity));
					}
				}
			}
		}
	}

	public static void addHammering() {
		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block compressedBlock = block.compressedBlock;
			Block baseBlock = GameRegistry.findBlock(block.modID, block.itemID);
			ArrayList<Smashable> smashResults = HammerRegistry.getRewards(baseBlock, block.baseMeta);

			if (smashResults == null)
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxHammering); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;

				for (Smashable result : smashResults) {
					for (int i = 0; i < Math.pow(9, m + 1); i++) {
						CompressedHammerRegistry.getSmashables().put(new ItemAndMetadata(compressedBlock, m), new Smashable(compressedBlock, m, result.item, result.meta, result.chance, result.luckMultiplier));
					}
				}
			}
		}
	}
}
