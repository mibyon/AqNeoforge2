package net.mibyon.aquariomod.network;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber
public class ModNetworking {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
            CellPhoneMessagePayload.TYPE,
            CellPhoneMessagePayload.STREAM_CODEC,
            ModNetworking::handleCellPhoneMessage
        );
    }

    private static void handleCellPhoneMessage(CellPhoneMessagePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer sender = (ServerPlayer) context.player();
            ServerPlayer target = sender.getServer().getPlayerList().getPlayerByName(payload.targetName());

            if (target == null) {
                sender.sendSystemMessage(Component.literal("Player is not online."));
                return;
            }

            Component senderView = Component.literal("You whisper to " + target.getName().getString() + ": " + payload.message());
            Component targetView = Component.literal(sender.getName().getString() + " whispers to you: " + payload.message());

            sender.sendSystemMessage(senderView);
            target.sendSystemMessage(targetView);
        });
    }
}