package com.wealthyturtle.additionalcompression.compat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wealthyturtle.additionalcompression.CompressedBlockRegistry;
import com.wealthyturtle.additionalcompression.CompressedBlockRegistry.CompressedInfos;
import com.wealthyturtle.additionalcompression.ConfigHandler;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.HammerReward;
import net.blay09.mods.excompressum.api.ReloadRegistryEvent.CompressedHammer;
import net.blay09.mods.excompressum.api.ReloadRegistryEvent.HeavySieve;
import net.blay09.mods.excompressum.api.compressedhammer.CompressedHammerReward;
import net.blay09.mods.excompressum.api.heavysieve.HeavySieveReward;
import net.blay09.mods.excompressum.config.ModConfig;
import net.blay09.mods.excompressum.registry.AutoSieveSkinRegistry;
import net.blay09.mods.excompressum.registry.ExRegistro;
import net.blay09.mods.excompressum.registry.heavysieve.HeavySieveRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExCompressum {
	
	public ExCompressum() {
	}

	public void exComprecipes() {
		if (!ModConfig.client.skipAutoSieveSkins) {
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
	}

	@SubscribeEvent
	public void addSifting(HeavySieve event) {
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

			if (ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(new ItemStack(baseBlock, 1, block.baseMeta)).isEmpty())
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxSifting); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;

				//HeavySieveRegistryEntry siftEntry = new HeavySieveRegistryEntry(block.compressedBlock.getStateFromMeta(m), false);
				//siftEntry.addRewards(ExRegistro.generateHeavyRewards(new ItemStack(baseBlock, 1, block.baseMeta), (int) Math.pow(9 - siftingLoss, m + 1)));
				//HeavySieveRegistry.INSTANCE.add(siftEntry);
				event.register(block.compressedBlock.getStateFromMeta(m), false, new ArrayList<HeavySieveReward>(ExRegistro.generateHeavyRewards(new ItemStack(baseBlock, 1, block.baseMeta), (int) Math.pow(9 - siftingLoss, m + 1))));
			}
		}
	}

	@SubscribeEvent
	public void addHammering(CompressedHammer event) {
		for (CompressedInfos block : CompressedBlockRegistry.compressedBlocks) {
			Block baseBlock = Block.REGISTRY.getObject(new ResourceLocation(block.modID, block.itemID));
			NonNullList<HammerReward> rewards = ExNihiloRegistryManager.HAMMER_REGISTRY.getRewards(baseBlock.getStateFromMeta(block.baseMeta));

			if (rewards.isEmpty())
				continue;

			for (int m = 0; m < Math.min(block.maxCompression, ConfigHandler.maxHammering); m++) {
				if (block.existingLevels.contains(m + 1))
					continue;


				List<CompressedHammerReward> newRewards = new ArrayList<CompressedHammerReward>();
				//CompressedHammerRegistryEntry hammerEntry = new CompressedHammerRegistryEntry(block.compressedBlock.getStateFromMeta(m), false);
				for (HammerReward reward : rewards) {
					newRewards.add(new CompressedHammerReward(new ItemStack(reward.getStack().getItem(), (int) Math.pow(9, m + 1), reward.getStack().getMetadata()), reward.getChance(), reward.getFortuneChance()));
				}
				//CompressedHammerRegistry.INSTANCE.add(hammerEntry);
				event.register(block.compressedBlock.getStateFromMeta(m), false, newRewards);
			}
		}
	}
}
