package com.wealthyturtle.additionalcompression.compat;

import minetweaker.MineTweakerImplementationAPI;
import minetweaker.util.IEventHandler;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "MineTweaker3", iface = "minetweaker.util.IEventHandler", striprefs = true)
public class MineTweaker implements IEventHandler<MineTweakerImplementationAPI.ReloadEvent> {

	public MineTweaker() {
		MineTweakerImplementationAPI.onPostReload(this);
	}

	@Override
	public void handle(MineTweakerImplementationAPI.ReloadEvent reloadEvent) {
		ExCompressum.addSifting();
	}
}
