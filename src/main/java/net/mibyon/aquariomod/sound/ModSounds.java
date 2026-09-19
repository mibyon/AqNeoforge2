package net.mibyon.aquariomod.sound;

import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.SOUND_EVENT, AquarioMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> TV_COUNTER_UP = SOUNDS.register("tv_counter_up",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "tv_counter_up")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}