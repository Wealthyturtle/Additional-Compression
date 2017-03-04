package com.wealthyturtle.additionalcompression.compat;

import java.lang.reflect.Field;
import java.util.List;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;
import com.wealthyturtle.additionalcompression.ConfigHandler;

import exnihiloomnia.registries.hammering.HammerRegistry;
import exnihiloomnia.registries.hammering.HammerRegistryEntry;
import exnihiloomnia.registries.hammering.HammerReward;
import exnihiloomnia.registries.sifting.SieveRegistry;
import exnihiloomnia.util.enums.EnumMetadataBehavior;
import net.blay09.mods.excompressum.registry.AutoSieveSkinRegistry;
import net.blay09.mods.excompressum.registry.ExRegistro;
import net.blay09.mods.excompressum.registry.compressedhammer.CompressedHammerRegistry;
import net.blay09.mods.excompressum.registry.compressedhammer.CompressedHammerRegistryEntry;
import net.blay09.mods.excompressum.registry.compressedhammer.CompressedHammerReward;
import net.blay09.mods.excompressum.registry.heavysieve.HeavySieveRegistry;
import net.blay09.mods.excompressum.registry.heavysieve.HeavySieveRegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class ExCompressum {

	public static void exComprecipes() {
		addHammering();
		addSifting();

		//if (Loader.isModLoaded("MineTweaker3"))
			//new MineTweaker();

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
		int siftingLoss = 2;
		Object heavySiftingReg = HeavySieveRegistry.INSTANCE;
		try {
			Field f = heavySiftingReg.getClass().getDeclaredField("defaultLoss");
			f.setAccessible(true);
			try {
				siftingLoss = (int) f.get(heavySiftingReg);
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
			Block baseBlock = Block.REGISTRY.getObject(new ResourceLocation(block.modID, block.itemID));

			if (SieveRegistry.getEntryForBlockState(baseBlock.getStateFromMeta(block.baseMeta), EnumMetadataBehavior.IGNORED) == null)
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxSifting); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;

				HeavySieveRegistryEntry siftEntry = new HeavySieveRegistryEntry(block.compressedBlock.getStateFromMeta(m), false);
				siftEntry.addRewards(ExRegistro.generateHeavyRewards(new ItemStack(baseBlock, block.baseMeta), (int) Math.pow(9 - siftingLoss, m + 1)));
				HeavySieveRegistry.INSTANCE.add(siftEntry);
			}
		}
	}

	public static void addHammering() {
		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block baseBlock = Block.REGISTRY.getObject(new ResourceLocation(block.modID, block.itemID));
			HammerRegistryEntry rewards = HammerRegistry.getEntryForBlockState(baseBlock.getStateFromMeta(block.baseMeta));

			if (rewards == null)
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxHammering); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;

				CompressedHammerRegistryEntry hammerEntry = new CompressedHammerRegistryEntry(block.compressedBlock.getStateFromMeta(m), false);
				for (HammerReward reward : rewards.getRewards()) {
					hammerEntry.addReward(new CompressedHammerReward(new ItemStack(reward.getItem().getItem(), (int) Math.pow(9, m + 1)), reward.getBaseChance() / 100, reward.getFortuneModifier()));
				}
				CompressedHammerRegistry.INSTANCE.add(hammerEntry);
			}
		}
	}
}
