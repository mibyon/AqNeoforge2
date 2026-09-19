package net.mibyon.aquariomod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.Set;

public abstract class MultiPartBlockEntity extends BlockEntity {

    private BlockPos offsetFromOrigin = BlockPos.ZERO;
    private final Set<Direction> poweredSides = EnumSet.noneOf(Direction.class);

    protected MultiPartBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void setOffsetFromOrigin(BlockPos offset) {
        this.offsetFromOrigin = offset;
        setChanged();
    }

    public BlockPos getOffsetFromOrigin() {
        return offsetFromOrigin;
    }

    public boolean isOrigin() {
        return offsetFromOrigin.equals(BlockPos.ZERO);
    }

    public BlockPos getOriginPos() {
        return getBlockPos().subtract(offsetFromOrigin);
    }

    public boolean isSidePowered(Direction side) {
        return poweredSides.contains(side);
    }

    public void setSidePowered(Direction side, boolean powered) {
        if (powered) {
            poweredSides.add(side);
        } else {
            poweredSides.remove(side);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("offsetX", offsetFromOrigin.getX());
        tag.putInt("offsetY", offsetFromOrigin.getY());
        tag.putInt("offsetZ", offsetFromOrigin.getZ());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        int x = tag.getInt("offsetX");
        int y = tag.getInt("offsetY");
        int z = tag.getInt("offsetZ");
        offsetFromOrigin = new BlockPos(x, y, z);
    }
}