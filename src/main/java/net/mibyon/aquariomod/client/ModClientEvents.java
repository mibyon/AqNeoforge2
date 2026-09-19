package net.mibyon.aquariomod.client;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.block.client.TvBlockEntityRenderer;
import net.mibyon.aquariomod.block.entity.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import net.mibyon.aquariomod.client.WorkCardOverlay;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

import net.mibyon.aquariomod.entity.ModEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@EventBusSubscriber(modid = AquarioMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TV_BE.get(), TvBlockEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.WORK_CARD.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
                ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "work_card_overlay"),
                WorkCardOverlay::render
        );
    }

}