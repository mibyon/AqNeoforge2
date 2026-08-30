package net.mibyon.aquariomod.item;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.block.ModBlocks;
import net.mibyon.aquariomod.item.custom.util.CellPhoneItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Moditems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AquarioMod.MODID);

    // miscellaneous
    public static final DeferredItem<Item> PLACA_PUNK = ITEMS.register("placa_punk",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_DIVO = ITEMS.register("placa_divo",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_NERD = ITEMS.register("placa_nerd",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_ESPADACHIM = ITEMS.register("placa_espadachim",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_BOTANICO = ITEMS.register("placa_botanico",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_CIENTISTA = ITEMS.register("placa_cientista",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_DORY = ITEMS.register("placa_dory",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_FOFOQUEIRO = ITEMS.register("placa_fofoqueiro",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<SwordItem> KATANA_TURQUESA = ITEMS.register("katana_turquesa",
        () -> new SwordItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.HYDROLITH, 3, 3f))));

    // artifacts
    public static final DeferredItem<Item> COLAR_CORACAO = ITEMS.register("colar_coracao",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOLITEMIS_CAPACETE = ITEMS.register("volitemis_capacete",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ANEL_DA_PROTECAO = ITEMS.register("anel_da_protecao",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POMPOM = ITEMS.register("pompom",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLAR_FLAMEJANTE = ITEMS.register("colar_flamejante",
        () -> new Item(new Item.Properties()));

    // ores
    public static final DeferredItem<Item> ARKANICITA = ITEMS.register("arkanicita",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ANTI_ARKANICITA = ITEMS.register("anti_arkanicita",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HYDROLITH = ITEMS.register("hydrolith",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HYDROLITH_INGOT = ITEMS.register("hydrolith_ingot",
        () -> new Item(new Item.Properties()));

    // images icons
    public static final DeferredItem<Item> PEIXE_ICON = ITEMS.register("peixe_icon",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FABIO_ICON = ITEMS.register("fabio_icon",
        () -> new Item(new Item.Properties()));

    // tools arkanicita
    public static final DeferredItem<SwordItem> ARKANICITA_SWORD = ITEMS.register("arkanicita_sword",
        () -> new SwordItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.ARKANICITA, 5, 3f))));
    public static final DeferredItem<ShovelItem> ARKANICITA_SHOVEL = ITEMS.register("arkanicita_shovel",
        () -> new ShovelItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(ShovelItem.createAttributes(ModToolsTiers.ARKANICITA, 3, 3f))));
    public static final DeferredItem<PickaxeItem> ARKANICITA_PICKAXE = ITEMS.register("arkanicita_pickaxe",
        () -> new PickaxeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(ModToolsTiers.ARKANICITA, 3, 3f))));
    public static final DeferredItem<AxeItem> ARKANICITA_AXE = ITEMS.register("arkanicita_axe",
        () -> new AxeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(AxeItem.createAttributes(ModToolsTiers.ARKANICITA, 3, 3f))));
    public static final DeferredItem<HoeItem> ARKANICITA_HOE = ITEMS.register("arkanicita_hoe",
        () -> new HoeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(HoeItem.createAttributes(ModToolsTiers.ARKANICITA, 2, 3f))));

    // tools anti_arkanicita
    public static final DeferredItem<SwordItem> ANTI_ARKANICITA_SWORD = ITEMS.register("anti_arkanicita_sword",
        () -> new SwordItem(ModToolsTiers.ANTI_ARKANICITA, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.ANTI_ARKANICITA, 5, 3f))));
    public static final DeferredItem<ShovelItem> ANTI_ARKANICITA_SHOVEL = ITEMS.register("anti_arkanicita_shovel",
        () -> new ShovelItem(ModToolsTiers.ANTI_ARKANICITA, new Item.Properties()
            .attributes(ShovelItem.createAttributes(ModToolsTiers.ANTI_ARKANICITA, 3, 3f))));
    public static final DeferredItem<PickaxeItem> ANTI_ARKANICITA_PICKAXE = ITEMS.register("anti_arkanicita_pickaxe",
        () -> new PickaxeItem(ModToolsTiers.ANTI_ARKANICITA, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(ModToolsTiers.ANTI_ARKANICITA, 3, 3f))));
    public static final DeferredItem<AxeItem> ANTI_ARKANICITA_AXE = ITEMS.register("anti_arkanicita_axe",
        () -> new AxeItem(ModToolsTiers.ANTI_ARKANICITA, new Item.Properties()
            .attributes(AxeItem.createAttributes(ModToolsTiers.ANTI_ARKANICITA, 3, 3f))));
    public static final DeferredItem<HoeItem> ANTI_ARKANICITA_HOE = ITEMS.register("anti_arkanicita_hoe",
        () -> new HoeItem(ModToolsTiers.ANTI_ARKANICITA, new Item.Properties()
            .attributes(HoeItem.createAttributes(ModToolsTiers.ANTI_ARKANICITA, 2, 3f))));

    // tools hydrolith
    public static final DeferredItem<SwordItem> HYDROLITH_SWORD = ITEMS.register("hydrolith_sword",
        () -> new SwordItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.HYDROLITH, 5, 3f))));
    public static final DeferredItem<ShovelItem> HYDROLITH_SHOVEL = ITEMS.register("hydrolith_shovel",
        () -> new ShovelItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(ShovelItem.createAttributes(ModToolsTiers.HYDROLITH, 3, 3f))));
    public static final DeferredItem<PickaxeItem> HYDROLITH_PICKAXE = ITEMS.register("hydrolith_pickaxe",
        () -> new PickaxeItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(ModToolsTiers.HYDROLITH, 3, 3f))));
    public static final DeferredItem<AxeItem> HYDROLITH_AXE = ITEMS.register("hydrolith_axe",
        () -> new AxeItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(AxeItem.createAttributes(ModToolsTiers.HYDROLITH, 3, 3f))));
    public static final DeferredItem<HoeItem> HYDROLITH_HOE = ITEMS.register("hydrolith_hoe",
        () -> new HoeItem(ModToolsTiers.HYDROLITH, new Item.Properties()
            .attributes(HoeItem.createAttributes(ModToolsTiers.HYDROLITH, 2, 3f))));

    // custom weapons
    public static final DeferredItem<SwordItem> BAHAMUT_HAMMER = ITEMS.register("bahamut_hammer",
        () -> new SwordItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.ARKANICITA, 5, 3f))));
    public static final DeferredItem<SwordItem> DAEMYSH_SPEAR = ITEMS.register("daemysh_spear",
        () -> new SwordItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolsTiers.ARKANICITA, 5, 3f))));

    // util

    public static final DeferredItem<Item> CELL_PHONE = ITEMS.register("cell_phone",
        () -> new CellPhoneItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> TICKET = ITEMS.registerSimpleItem("ticket");
    
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}