package com.pixelsky.cheese.handler;

import com.pixelsky.cheese.Reference;
import com.pixelsky.cheese.capability.cheese.CheeseProvider;
import com.pixelsky.cheese.capability.haditems.HadItemsProvider;
import com.pixelsky.cheese.capability.money.MoneyProvider;
import com.pixelsky.cheese.capability.shouldgiveitems.ShouldGiveItemsProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheeseCapabilityHandler {
	
	@SubscribeEvent
	public void attachCapabilityEntity(AttachCapabilitiesEvent.Entity event) {
		if(event.getEntity() instanceof EntityPlayer) {
			event.addCapability(new ResourceLocation(Reference.MODID + ":had_items"), new HadItemsProvider());
			event.addCapability(new ResourceLocation(Reference.MODID + ":cheese"), new CheeseProvider());
			event.addCapability(new ResourceLocation(Reference.MODID + ":money"), new MoneyProvider());
		}
	}
	
	@SubscribeEvent
	public void attachCapabilityWorld(AttachCapabilitiesEvent.World event) {
		event.addCapability(new ResourceLocation(Reference.MODID + ":should_give"), new ShouldGiveItemsProvider());
	}
	
}
