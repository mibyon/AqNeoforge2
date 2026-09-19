package net.mibyon.aquariomod.entity;

import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(Registries.ENTITY_TYPE, AquarioMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<WorkCardEntity>> WORK_CARD =
        ENTITY_TYPES.register("work_card", () -> EntityType.Builder.<WorkCardEntity>of(WorkCardEntity::new, MobCategory.MISC)
                .sized(0.25f, 0.25f)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("work_card"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}