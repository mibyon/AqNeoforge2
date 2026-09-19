package net.mibyon.aquariomod.block;

import net.mibyon.aquariomod.block.entity.MultiPartBlockEntity;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPartBlock extends Block implements EntityBlock {

    public static final BooleanProperty IS_ORIGIN = BooleanProperty.create("is_origin");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final DeferredItem<Item> DEBUG_STAFF_ITEM = Moditems.NEPTOOL;
    
    public MultiPartBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(IS_ORIGIN, true)
                .setValue(FACING, Direction.NORTH));
    }

    // offsets definidos como se o bloco estivesse virado pra north, sem rotação
    protected abstract List<BlockPos> footprint();

    protected abstract MultiPartBlockEntity createBlockEntity(BlockPos pos, BlockState state);

    protected InteractionResult onEmptyHandInteract(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    protected InteractionResult onItemInteract(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    protected InteractionResult onDebugStaffInteract(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    protected void onRedstonePulse(Level level, BlockPos pos, BlockState state, Direction side) {
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_ORIGIN);
        builder.add(FACING);
    }

    private List<BlockPos> rotatedFootprint(Direction facing) {
        List<BlockPos> rotated = new ArrayList<>();

        for (BlockPos offset : footprint()) {
            rotated.add(rotateOffset(offset, facing));
        }

        return rotated;
    }

    private BlockPos rotateOffset(BlockPos offset, Direction facing) {
        int x = offset.getX();
        int y = offset.getY();
        int z = offset.getZ();

        if (facing == Direction.EAST) {
            return new BlockPos(-z, y, x);
        }

        if (facing == Direction.SOUTH) {
            return new BlockPos(-x, y, -z);
        }

        if (facing == Direction.WEST) {
            return new BlockPos(z, y, -x);
        }

        return new BlockPos(x, y, z);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos originPos = context.getClickedPos();
        Level level = context.getLevel();
        Direction facing = context.getHorizontalDirection().getOpposite();

        for (BlockPos offset : rotatedFootprint(facing)) {
            BlockPos targetPos = originPos.offset(offset);

            if (targetPos.getY() >= level.getMaxBuildHeight()) {
                return null;
            }

            if (!level.getBlockState(targetPos).canBeReplaced(context)) {
                return null;
            }
        }

        return this.defaultBlockState().setValue(FACING, facing);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        Direction facing = state.getValue(FACING);

        for (BlockPos offset : rotatedFootprint(facing)) {
            if (offset.equals(BlockPos.ZERO)) {
                continue;
            }

            BlockPos partPos = pos.offset(offset);
            BlockState partState = this.defaultBlockState().setValue(IS_ORIGIN, false).setValue(FACING, facing);
            level.setBlock(partPos, partState, 3);

            if (level.getBlockEntity(partPos) instanceof MultiPartBlockEntity partEntity) {
                partEntity.setOffsetFromOrigin(offset);
            }
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof MultiPartBlockEntity partEntity) {
                BlockPos originPos = partEntity.getOriginPos();
                Direction facing = level.getBlockState(originPos).getValue(FACING);

                for (BlockPos offset : rotatedFootprint(facing)) {
                    BlockPos otherPos = originPos.offset(offset);

                    if (otherPos.equals(pos)) {
                        continue;
                    }

                    if (level.getBlockState(otherPos).is(this)) {
                        level.setBlock(otherPos, Blocks.AIR.defaultBlockState(), 35);
                    }
                }
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        if (state.getValue(IS_ORIGIN)) {
            return RenderShape.MODEL;
        }

        return RenderShape.INVISIBLE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        return onEmptyHandInteract(state, level, pos, player, hitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return ItemInteractionResult.SUCCESS;
        }

        InteractionResult result;

        if (stack.is(DEBUG_STAFF_ITEM.get())) {
            result = onDebugStaffInteract(state, level, pos, player, hitResult);
        } else {
            result = onItemInteract(state, level, pos, player, stack, hitResult);
        }

        return convertResult(result);
    }

    private static ItemInteractionResult convertResult(InteractionResult result) {
        if (result == InteractionResult.SUCCESS) {
            return ItemInteractionResult.SUCCESS;
        }

        if (result == InteractionResult.CONSUME) {
            return ItemInteractionResult.CONSUME;
        }

        if (result == InteractionResult.FAIL) {
            return ItemInteractionResult.FAIL;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);

        if (level.isClientSide) {
            return;
        }

        if (!(level.getBlockEntity(pos) instanceof MultiPartBlockEntity partEntity)) {
            return;
        }

        for (Direction side : Direction.values()) {
            BlockPos sidePos = pos.relative(side);
            boolean isPowered = level.getSignal(sidePos, side) > 0;
            boolean wasPowered = partEntity.isSidePowered(side);

            if (isPowered && !wasPowered) {
                onRedstonePulse(level, pos, state, side);
            }

            partEntity.setSidePowered(side, isPowered);
        }
    }
}