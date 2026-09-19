package net.mibyon.aquariomod.block;

import net.mibyon.aquariomod.block.entity.MultiPartBlockEntity;
import net.mibyon.aquariomod.block.entity.TicketAtmBlockEntity;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class TicketAtmBlock extends MultiPartBlock {

    private static final List<BlockPos> FOOTPRINT = List.of(BlockPos.ZERO, new BlockPos(0, 1, 0));
    private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 16, 14);

    public TicketAtmBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected List<BlockPos> footprint() {
        return FOOTPRINT;
    }

    @Override
    protected MultiPartBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TicketAtmBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult onEmptyHandInteract(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof MultiPartBlockEntity partEntity)) {
            return InteractionResult.FAIL;
        }

        BlockPos originPos = partEntity.getOriginPos();

        if (!(level.getBlockEntity(originPos) instanceof TicketAtmBlockEntity atm)) {
            return InteractionResult.FAIL;
        }

        int number = atm.claimTicket(player.getUUID());

        if (number == -1) {
            player.displayClientMessage(Component.literal("Você já pegou sua senha."), true);
            return InteractionResult.CONSUME;
        }

        ItemStack ticket = new ItemStack(Moditems.TICKET.get());
        ticket.set(DataComponents.CUSTOM_NAME, Component.literal("Senha #" + number));

        if (!player.getInventory().add(ticket)) {
            player.drop(ticket, false);
        }

        level.playSound(null, pos, SoundEvents.NOTE_BLOCK_CHIME.value(), SoundSource.BLOCKS, 1.0f, 1.0f);

        return InteractionResult.CONSUME;
    }
}