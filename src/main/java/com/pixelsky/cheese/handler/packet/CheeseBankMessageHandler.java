package com.pixelsky.cheese.handler.packet;

import com.pixelsky.cheese.capability.money.IMoney;
import com.pixelsky.cheese.capability.money.MoneyProvider;
import com.pixelsky.cheese.init.CheeseItems;
import com.pixelsky.cheese.packet.CheeseBankMessage;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CheeseBankMessageHandler implements IMessageHandler<CheeseBankMessage, IMessage> {
	@Override
	public IMessage onMessage(final CheeseBankMessage message, final MessageContext ctx) {
		final EntityPlayerMP player = ctx.getServerHandler().player;
		IThreadListener mainThread = (WorldServer) player.world;
		mainThread.addScheduledTask(new Runnable() {
			@Override
			public void run() {
				IMoney money = player.getCapability(MoneyProvider.MONEY_CAP, null);
				if (message.getValue() == 10) {
					player.inventory.clearMatchingItems(CheeseItems.CHEESE_MONEY, -1, 1, null);
					money.add(message.getValue());
				} else if (message.getValue() == 50) {
					player.inventory.clearMatchingItems(Items.GOLD_INGOT, -1, 1, null);
					money.add(message.getValue());
				} else if (message.getValue() == -10) {
					player.world.spawnEntity(new EntityItem(player.world, player.posX, player.posY, player.posZ, new ItemStack(CheeseItems.CHEESE_MONEY, 1)));
					money.remove(10);
				} else if (message.getValue() == -50) {
					player.world.spawnEntity(new EntityItem(player.world, player.posX, player.posY, player.posZ, new ItemStack(Items.GOLD_INGOT, 1)));
					money.remove(50);
				}
			}
		});
		return null;
	}
}
