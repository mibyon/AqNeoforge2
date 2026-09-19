package net.mibyon.aquariomod;

import net.mibyon.aquariomod.block.ModBlocks;
import net.mibyon.aquariomod.item.ModCreativeModeTabs;
import net.mibyon.aquariomod.item.Moditems;
import net.mibyon.aquariomod.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import net.mibyon.aquariomod.block.entity.ModBlockEntities;
import net.mibyon.aquariomod.effect.ModEffects;
import net.mibyon.aquariomod.entity.ModEntities;

@Mod(AquarioMod.MODID)
public class AquarioMod {
    public static final String MODID = "aquariomod";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static final java.util.Set<String> DEV_ACCOUNTS = java.util.Set.of("Nepoun", "Dev", "Dev2", "MibyonPeixe");

    public AquarioMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        Moditems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != ModCreativeModeTabs.AQUARIO_ITEMS) {
            return;
        }

        boolean isDevAccount = DEV_ACCOUNTS.contains(net.minecraft.client.Minecraft.getInstance().getUser().getName());

        if (!isDevAccount) {
            return;
        }

        event.accept(Moditems.PLACA_PUNK);
        event.accept(Moditems.PLACA_DIVO);
        event.accept(Moditems.PLACA_NERD);
        event.accept(Moditems.PLACA_ESPADACHIM);
        event.accept(Moditems.PLACA_BOTANICO);
        event.accept(Moditems.PLACA_CIENTISTA);
        event.accept(Moditems.VOLITEMIS_CAPACETE);
        event.accept(Moditems.PEIXE_ICON);
        event.accept(Moditems.PLACA_DORY);
        event.accept(Moditems.PLACA_FOFOQUEIRO);
        event.accept(Moditems.COLAR_CORACAO);
        event.accept(Moditems.POMPOM);
        event.accept(Moditems.ANEL_DA_PROTECAO);
        event.accept(Moditems.COLAR_FLAMEJANTE);
        event.accept(ModBlocks.ARKANICITA_ORE);
        event.accept(Moditems.ARKANICITA);
        event.accept(ModBlocks.ARKANICITA_BLOCK);
        event.accept(Moditems.ARKANICITA_AXE);
        event.accept(Moditems.ARKANICITA_SWORD);
        event.accept(Moditems.ARKANICITA_SHOVEL);
        event.accept(Moditems.ARKANICITA_HOE);
        event.accept(ModBlocks.ANTI_ARKANICITA_BLOCK);
        event.accept(ModBlocks.ANTI_ARKANICITA_ORE);
        event.accept(Moditems.FABIO_ICON);
        event.accept(Moditems.BAHAMUT_HAMMER);
        event.accept(Moditems.DAEMYSH_SPEAR);
        event.accept(ModBlocks.HYDROLITH_ORE);
        event.accept(ModBlocks.HYDROLITH_BLOCK);
        event.accept(Moditems.ANTI_ARKANICITA);
        event.accept(Moditems.HYDROLITH);
        event.accept(Moditems.HYDROLITH_INGOT);

        event.accept(Moditems.ANTI_ARKANICITA_AXE);
        event.accept(Moditems.ANTI_ARKANICITA_SWORD);
        event.accept(Moditems.ANTI_ARKANICITA_SHOVEL);
        event.accept(Moditems.ANTI_ARKANICITA_HOE);

        event.accept(Moditems.HYDROLITH_AXE);
        event.accept(Moditems.HYDROLITH_SWORD);
        event.accept(Moditems.HYDROLITH_SHOVEL);
        event.accept(Moditems.HYDROLITH_HOE);
        event.accept(Moditems.KATANA_TURQUESA);

        event.accept(Moditems.CELL_PHONE);
        event.accept(Moditems.NEPTOOL);
        event.accept(Moditems.CARTEIRA_TRABALHO);

        event.accept(ModBlocks.TICKET_ATM);
        event.accept(ModBlocks.TV);
        event.accept(ModBlocks.VENDING_MACHINE);

        event.accept(Moditems.PIPOCA);
        event.accept(Moditems.MILKSHAKE);
        event.accept(Moditems.ALGODAO_DOCE);
        event.accept(Moditems.LATINHA1);
        event.accept(Moditems.LATINHA2);
        event.accept(Moditems.LATINHA3);
        
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}