package net.mibyon.aquariomod.block;

import net.mibyon.aquariomod.block.entity.MultiPartBlockEntity;
import net.mibyon.aquariomod.block.entity.TvBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class TvBlock extends MultiPartBlock {

    private static final List<BlockPos> FOOTPRINT = List.of(BlockPos.ZERO);
    private static final VoxelShape SHAPE = Shapes.block();
    public TvBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected List<BlockPos> footprint() {
        return FOOTPRINT;
    }

    @Override
    protected MultiPartBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TvBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult onDebugStaffInteract(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof TvBlockEntity tv)) {
            return InteractionResult.FAIL;
        }

        tv.resetCounter();
        return InteractionResult.CONSUME;
    }

    @Override
    protected void onRedstonePulse(Level level, BlockPos pos, BlockState state, Direction side) {
        if (!(level.getBlockEntity(pos) instanceof TvBlockEntity tv)) {
            return;
        }

        Direction facing = state.getValue(FACING);
        Direction backSide = facing.getOpposite();

        if (side == backSide) {
            tv.increaseCounter();
        } else if (side == Direction.DOWN) {
            tv.decreaseCounter();
        }
    }
}