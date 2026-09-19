package net.mibyon.aquariomod.block.entity;

import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class VendingMachineBlockEntity extends MultiPartBlockEntity {

    private static final int COOLDOWN_TICKS = 40;

    private static final List<DeferredItem<? extends Item>> POOL = List.of(
            Moditems.PIPOCA,
            Moditems.MILKSHAKE,
            Moditems.ALGODAO_DOCE,
            Moditems.LATINHA1,
            Moditems.LATINHA2,
            Moditems.LATINHA3,
            Moditems.CARTEIRA_TRABALHO
    );

    private final Map<UUID, Long> lastUseGameTime = new HashMap<>();

    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VENDING_MACHINE_BE.get(), pos, state);
    }

    public boolean tryDispense(Player player, long gameTime) {
        UUID playerId = player.getUUID();
        Long lastUse = lastUseGameTime.get(playerId);

        if (lastUse != null && gameTime - lastUse < COOLDOWN_TICKS) {
            return false;
        }

        lastUseGameTime.put(playerId, gameTime);

        int index = player.getRandom().nextInt(POOL.size());
        Item chosenItem = POOL.get(index).get();
        ItemStack drop = new ItemStack(chosenItem);

        if (!player.getInventory().add(drop)) {
            player.drop(drop, false);
        }

        return true;
    }
}