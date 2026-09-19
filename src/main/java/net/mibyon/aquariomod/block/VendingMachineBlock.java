package net.mibyon.aquariomod.block;

import net.mibyon.aquariomod.block.entity.MultiPartBlockEntity;
import net.mibyon.aquariomod.block.entity.VendingMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class VendingMachineBlock extends MultiPartBlock {

    private static final List<BlockPos> FOOTPRINT = List.of(
            new BlockPos(0, 0, 0),
            new BlockPos(1, 0, 0),
            new BlockPos(0, 1, 0),
            new BlockPos(1, 1, 0)
    );

    private static final VoxelShape MAIN_COLUMN_SHAPE = Block.box(0, 0, 5, 16, 16, 16);
    private static final VoxelShape SIDE_COLUMN_SHAPE = Block.box(0, 0, 5, 3, 16, 16);

    public VendingMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected List<BlockPos> footprint() {
        return FOOTPRINT;
    }

    @Override
    protected MultiPartBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VendingMachineBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (!(level.getBlockEntity(pos) instanceof MultiPartBlockEntity partEntity)) {
            return MAIN_COLUMN_SHAPE;
        }

        Direction facing = state.getValue(FACING);
        BlockPos rawOffset = unrotateOffset(partEntity.getOffsetFromOrigin(), facing);

        VoxelShape baseShape;

        if (rawOffset.getX() == 0) {
            baseShape = MAIN_COLUMN_SHAPE;
        } else {
            baseShape = SIDE_COLUMN_SHAPE;
        }

        return rotateShape(baseShape, facing);
    }

    private BlockPos unrotateOffset(BlockPos rotatedOffset, Direction facing) {
        int x = rotatedOffset.getX();
        int y = rotatedOffset.getY();
        int z = rotatedOffset.getZ();

        if (facing == Direction.EAST) {
            return new BlockPos(z, y, -x);
        }

        if (facing == Direction.SOUTH) {
            return new BlockPos(-x, y, -z);
        }

        if (facing == Direction.WEST) {
            return new BlockPos(-z, y, x);
        }

        return new BlockPos(x, y, z);
    }

    private VoxelShape rotateShape(VoxelShape shape, Direction facing) {
        if (facing == Direction.NORTH) {
            return shape;
        }

        double minX = shape.min(Direction.Axis.X) * 16;
        double maxX = shape.max(Direction.Axis.X) * 16;
        double minY = shape.min(Direction.Axis.Y) * 16;
        double maxY = shape.max(Direction.Axis.Y) * 16;
        double minZ = shape.min(Direction.Axis.Z) * 16;
        double maxZ = shape.max(Direction.Axis.Z) * 16;

        double[] corner1 = rotatePoint(minX, minZ, facing);
        double[] corner2 = rotatePoint(maxX, maxZ, facing);

        double rotatedMinX = Math.min(corner1[0], corner2[0]);
        double rotatedMaxX = Math.max(corner1[0], corner2[0]);
        double rotatedMinZ = Math.min(corner1[1], corner2[1]);
        double rotatedMaxZ = Math.max(corner1[1], corner2[1]);

        return Block.box(rotatedMinX, minY, rotatedMinZ, rotatedMaxX, maxY, rotatedMaxZ);
    }

    private double[] rotatePoint(double x, double z, Direction facing) {
        double centeredX = x - 8;
        double centeredZ = z - 8;

        double rotatedX;
        double rotatedZ;

        if (facing == Direction.EAST) {
            rotatedX = -centeredZ;
            rotatedZ = centeredX;
        } else if (facing == Direction.SOUTH) {
            rotatedX = -centeredX;
            rotatedZ = -centeredZ;
        } else if (facing == Direction.WEST) {
            rotatedX = centeredZ;
            rotatedZ = -centeredX;
        } else {
            rotatedX = centeredX;
            rotatedZ = centeredZ;
        }

        return new double[]{rotatedX + 8, rotatedZ + 8};
    }

    @Override
    protected InteractionResult onEmptyHandInteract(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof MultiPartBlockEntity partEntity)) {
            return InteractionResult.FAIL;
        }

        BlockPos originPos = partEntity.getOriginPos();

        if (!(level.getBlockEntity(originPos) instanceof VendingMachineBlockEntity vendingMachine)) {
            return InteractionResult.FAIL;
        }

        boolean dispensed = vendingMachine.tryDispense(player, level.getGameTime());

        if (!dispensed) {
            return InteractionResult.CONSUME;
        }

        level.playSound(null, pos, SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0f, 1.0f);

        return InteractionResult.CONSUME;
    }
}