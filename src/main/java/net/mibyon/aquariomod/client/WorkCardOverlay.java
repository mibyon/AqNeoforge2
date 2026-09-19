package net.mibyon.aquariomod.client;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.effect.ModEffects;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

public class WorkCardOverlay {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "textures/gui/virou_clt_overlay.png");

    public static void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        if (!player.hasEffect(ModEffects.VIROU_CLT)) {
            return;
        }

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        graphics.blit(TEXTURE, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
    }
}