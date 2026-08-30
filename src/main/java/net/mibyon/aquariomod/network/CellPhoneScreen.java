package net.mibyon.aquariomod.network;

import net.mibyon.aquariomod.network.CellPhoneMessagePayload;
import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

// Todo esse sistema não está bem feito porque eu fiz meio ás pressas
// no decorrer da semana eu faço uma arquitetura melhor de como fazer essas coisas
// de celular. Mas já serve como uma prove of concept boa o suficiente por enquanto
public class CellPhoneScreen extends Screen {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "textures/gui/aqphone_gui.png");

    int imageWidth = 222;
    int imageHeight = 176;

    private int leftPos;
    private int topPos;

    private int screenX;
    private int screenY;
    private int screenWidth;
    private int screenHeight;

    private final List<String> onlinePlayerNames = new ArrayList<>();
    private String selectedPlayer = null;
    private EditBox messageBox;

    public CellPhoneScreen() {
        super(Component.literal("Cell Phone"));
    }

    @Override
    protected void init() {
        Minecraft.getInstance().getTextureManager().getTexture(TEXTURE).setFilter(false, false);

        onlinePlayerNames.clear();
        this.leftPos = (this.width - imageWidth) / 2;
        this.topPos = (this.height - imageHeight) / 2;

        int insetX = 20;
        int insetY = 30;
        int insetBottom = 20;

        this.screenX = leftPos + insetX;
        this.screenY = topPos + insetY;
        this.screenWidth = imageWidth - (insetX * 2);
        this.screenHeight = imageHeight - insetY - insetBottom;

        for (Player p : Minecraft.getInstance().level.players()) {
            if (p != Minecraft.getInstance().player) {
                onlinePlayerNames.add(p.getName().getString());
            }
        }

        int y = screenY;
        for (String name : onlinePlayerNames) {
            addRenderableWidget(Button.builder(Component.literal(name), button -> selectedPlayer = name)
                .bounds(screenX, y, screenWidth, 20)
                .build());
            y += 22;
        }

        messageBox = new EditBox(this.font, screenX, screenY + screenHeight - 44, screenWidth, 20, Component.literal("Message"));
        addRenderableWidget(messageBox);

        addRenderableWidget(Button.builder(Component.literal("Send"), button -> sendMessage())
            .bounds(screenX, screenY + screenHeight - 20, screenWidth, 20)
            .build());
    }

    private void sendMessage() {
        if (selectedPlayer == null || messageBox.getValue().isBlank()) {
            return;
        }

        PacketDistributor.sendToServer(new CellPhoneMessagePayload(selectedPlayer, messageBox.getValue()));
        messageBox.setValue("");
        this.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0.0f, 0.0f, imageWidth, imageHeight, imageWidth, imageHeight);
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, topPos + 10, 0xFFFFFF);
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}