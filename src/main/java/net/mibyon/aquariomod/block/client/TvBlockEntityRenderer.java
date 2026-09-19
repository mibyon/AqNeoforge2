package net.mibyon.aquariomod.block.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.mibyon.aquariomod.block.TvBlock;
import net.mibyon.aquariomod.block.entity.TvBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class TvBlockEntityRenderer implements BlockEntityRenderer<TvBlockEntity> {

    public TvBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(TvBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        Direction facing = state.getValue(TvBlock.FACING);
        String text = String.format("%03d", blockEntity.getCounterValue());

        poseStack.pushPose();
        poseStack.translate(0.5, 0.68, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(facingToYRotation(facing)));
        poseStack.translate(0, 0, -0.02);
        poseStack.scale(-0.0375f, -0.0375f, 0.0375f);

        Font font = Minecraft.getInstance().font;
        int textWidth = font.width(text);

        font.drawInBatch(text, -textWidth / 2f, 0, 0xFFFFFF, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);

        poseStack.popPose();
    }

    private float facingToYRotation(Direction facing) {
        if (facing == Direction.NORTH) {
            return 0f;
        }

        if (facing == Direction.EAST) {
            return 90f;
        }

        if (facing == Direction.SOUTH) {
            return 180f;
        }

        if (facing == Direction.WEST) {
            return 270f;
        }

        return 0f;
    }
}