package net.mibyon.aquariomod.block.entity;

import net.mibyon.aquariomod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TvBlockEntity extends MultiPartBlockEntity {

    private int counterValue = 0;

    public TvBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TV_BE.get(), pos, state);
    }

    public int getCounterValue() {
        return counterValue;
    }

    public void increaseCounter() {
        counterValue++;
        syncToClients();

        if (level != null && !level.isClientSide) {
            level.playSound(null, getBlockPos(), ModSounds.TV_COUNTER_UP.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
        }
    }

    public void decreaseCounter() {
        if (counterValue <= 0) {
            return;
        }

        counterValue--;
        syncToClients();
    }

    public void resetCounter() {
        counterValue = 0;
        syncToClients();
    }

    private void syncToClients() {
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("counterValue", counterValue);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        counterValue = tag.getInt("counterValue");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("counterValue", counterValue);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        counterValue = tag.getInt("counterValue");
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}