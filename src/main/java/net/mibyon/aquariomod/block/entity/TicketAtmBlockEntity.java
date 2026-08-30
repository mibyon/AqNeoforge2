package net.mibyon.aquariomod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TicketAtmBlockEntity extends BlockEntity {

    private int nextTicketNumber = 1;
    private final Set<UUID> playersWhoClaimed = new HashSet<>();

    public TicketAtmBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TICKET_ATM_BE.get(), pos, state);
    }

    public int claimTicket(UUID playerId) {
        if (playersWhoClaimed.contains(playerId)) {
            return -1;
        }
        int number = nextTicketNumber++;
        playersWhoClaimed.add(playerId);
        setChanged();
        return number;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("nextTicketNumber", nextTicketNumber);

        ListTag list = new ListTag();
        for (UUID id : playersWhoClaimed) {
            list.add(NbtUtils.createUUID(id));
        }
        tag.put("claimed", list);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        nextTicketNumber = tag.getInt("nextTicketNumber");

        playersWhoClaimed.clear();
        ListTag list = tag.getList("claimed", 11);
        for (int i = 0; i < list.size(); i++) {
            playersWhoClaimed.add(NbtUtils.loadUUID(list.get(i)));
        }
    }
}