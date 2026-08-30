package net.mibyon.aquariomod;

import net.mibyon.aquariomod.block.ModBlocks;
import net.mibyon.aquariomod.item.ModCreativeModeTabs;
import net.mibyon.aquariomod.item.Moditems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
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
import net.neoforged.neoforge.registries.DeferredRegister;

import net.mibyon.aquariomod.block.entity.ModBlockEntities;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AquarioMod.MODID)
public class AquarioMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "aquariomod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);







    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AquarioMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        Moditems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ModCreativeModeTabs.AQUARIO_ITEMS) {
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
            event.accept(Moditems.ARKANICITA_SWORD);
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
            event.accept(Moditems.ANTI_ARKANICITA_SWORD);
            event.accept(Moditems.ANTI_ARKANICITA_HOE);

            event.accept(Moditems.HYDROLITH_AXE);
            event.accept(Moditems.HYDROLITH_SWORD);
            event.accept(Moditems.HYDROLITH_SHOVEL);
            event.accept(Moditems.HYDROLITH_SWORD);
            event.accept(Moditems.HYDROLITH_HOE);
            event.accept(Moditems.KATANA_TURQUESA);




        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
