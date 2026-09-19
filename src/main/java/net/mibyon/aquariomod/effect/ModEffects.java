package net.mibyon.aquariomod.effect;

import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, AquarioMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> VIROU_CLT = EFFECTS.register("virou_clt",
            () -> new MobEffect(MobEffectCategory.HARMFUL, 0x4a4a4a) {
            });

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}