package net.mibyon.aquariomod.network;

import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CellPhoneMessagePayload(String targetName, String message) implements CustomPacketPayload {

    public static final Type<CellPhoneMessagePayload> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "cell_phone_message"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CellPhoneMessagePayload> STREAM_CODEC =
        StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, CellPhoneMessagePayload::targetName,
            ByteBufCodecs.STRING_UTF8, CellPhoneMessagePayload::message,
            CellPhoneMessagePayload::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}