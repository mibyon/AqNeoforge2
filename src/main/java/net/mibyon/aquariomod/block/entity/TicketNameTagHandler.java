package net.mibyon.aquariomod.block.entity;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;

@EventBusSubscriber(modid = AquarioMod.MODID, value = Dist.CLIENT)
public class TicketNameTagHandler {

    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();
        if (!heldItem.is(Moditems.TICKET.get())) {
            return;
        }

        Component ticketName = heldItem.get(DataComponents.CUSTOM_NAME);
        if (ticketName == null) {
            return;
        }

        String numberOnly = ticketName.getString().replace("Senha ", "");

        Component combined = event.getContent().copy()
            .append(Component.literal(" - "))
            .append(Component.literal(numberOnly));

        event.setContent(combined);
    }
}