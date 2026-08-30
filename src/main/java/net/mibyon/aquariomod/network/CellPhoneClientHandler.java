package net.mibyon.aquariomod.network;

import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class CellPhoneClientHandler {

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().is(Moditems.CELL_PHONE.get())) {
            Minecraft.getInstance().setScreen(new CellPhoneScreen());
        }
    }
}