package net.mibyon.aquariomod.block.entity;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, AquarioMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TicketAtmBlockEntity>> TICKET_ATM_BE =
        BLOCK_ENTITIES.register("ticket_atm_be", () -> BlockEntityType.Builder.of(
            TicketAtmBlockEntity::new, ModBlocks.TICKET_ATM.get()
        ).build(null));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}