package com.wealthyturtle.additionalcompression.compat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;

import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftReward;
import exnihilo.registries.helpers.SiftingResult;
import exnihilo.registries.helpers.Smashable;
import net.blay09.mods.excompressum.registry.AutoSieveSkinRegistry;
import net.blay09.mods.excompressum.registry.CompressedHammerRegistry;
import net.blay09.mods.excompressum.registry.HeavySieveRegistry;
import net.blay09.mods.excompressum.registry.data.ItemAndMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ExCompressum {

	public static void preInit() {

	}

	public static void exComprecipes() {
		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block compressedBlock = block.compressedBlock;
			Item baseItem = GameRegistry.findItem(block.modID, block.itemID);
			ArrayList<Smashable> smashResults = HammerRegistry.getRewards(Block.getBlockFromItem(baseItem), block.baseMeta);

			if (smashResults != null)
			for (Smashable result : smashResults) {
				for (int i = 0; i < 9; i++){
					CompressedHammerRegistry.getSmashables().put(new ItemAndMetadata(compressedBlock, 0), new Smashable(compressedBlock, 0, result.item, result.meta, result.chance, result.luckMultiplier));
				}
			}
		}
	}

	public static void exComprecipesPostInit() {
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

		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block compressedBlock = block.compressedBlock;
			Item baseItem = GameRegistry.findItem(block.modID, block.itemID);
			ArrayList<SiftReward> siftResults = SieveRegistry.getRewards(Block.getBlockFromItem(baseItem), block.baseMeta);

			if (siftResults != null)
			for (SiftReward result : siftResults) {
				for (int i = 0; i < 6; i++){
					HeavySieveRegistry.getSiftables().put(new ItemAndMetadata(compressedBlock, 0), new SiftingResult(result.item, result.meta, result.rarity));
				}
			}
		}
	}
}
