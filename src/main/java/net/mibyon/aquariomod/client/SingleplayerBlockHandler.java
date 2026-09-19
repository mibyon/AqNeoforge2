package net.mibyon.aquariomod.client;

import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = AquarioMod.MODID, value = Dist.CLIENT)
public class SingleplayerBlockHandler {

    @SubscribeEvent
    public static void onLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        Minecraft minecraft = Minecraft.getInstance();
        AquarioMod.LOGGER.info("LoggingIn disparou, singleplayer = {}", Minecraft.getInstance().hasSingleplayerServer());

        if (!minecraft.hasSingleplayerServer()) {
            return;
        }

        String nickname = minecraft.getUser().getName();

        if (AquarioMod.DEV_ACCOUNTS.contains(nickname)) {
            return;
        }

        minecraft.setScreen(null);
        minecraft.level = null;

        if (minecraft.getConnection() != null) {
            minecraft.getConnection().getConnection().disconnect(
                    Component.literal("Esse mod so funciona no nosso servidor.")
            );
        }
    }
}