package net.mibyon.aquariomod.item;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.item.custom.weapons.BahamutHammerItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Moditems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AquarioMod.MODID);

    public static final DeferredItem<Item>PLACA_PUNK = ITEMS.register("placa_punk",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>PLACA_DIVO = ITEMS.register("placa_divo",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>PLACA_NERD = ITEMS.register("placa_nerd",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>PLACA_ESPADACHIM = ITEMS.register("placa_espadachim",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>PLACA_BOTANICO = ITEMS.register("placa_botanico",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>PLACA_CIENTISTA = ITEMS.register("placa_cientista",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>VOLITEMIS_CAPACETE = ITEMS.register("volitemis_capacete",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEIXE_ICON= ITEMS.register("peixe_icon",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_DORY= ITEMS.register("placa_dory",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLACA_FOFOQUEIRO= ITEMS.register("placa_fofoqueiro",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLAR_CORACAO= ITEMS.register("colar_coracao",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ARKANICITA= ITEMS.register("arkanicita",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FABIO_ICON= ITEMS.register("fabio_icon",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> ARKANICITA_SWORD = ITEMS.register("arkanicita_sword",
            () -> new SwordItem(ModToolsTiers.ARKANICITA, new Item.Properties()
            .attributes(SwordItem.createAttributes((ModToolsTiers.ARKANICITA),5 , 3f))));

    public static final DeferredItem<ShovelItem> ARKANICITA_SHOVEL = ITEMS.register("arkanicita_shovel",
            () -> new ShovelItem(ModToolsTiers.ARKANICITA, new Item.Properties()
                    .attributes(ShovelItem.createAttributes((ModToolsTiers.ARKANICITA),3 , 3f))));

    public static final DeferredItem<PickaxeItem> ARKANICITA_PICKAXE = ITEMS.register("arkanicita_pickaxe",
            () -> new PickaxeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes((ModToolsTiers.ARKANICITA),3 , 3f))));

    public static final DeferredItem<AxeItem> ARKANICITA_AXE = ITEMS.register("arkanicita_axe",
            () -> new AxeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
                    .attributes(AxeItem.createAttributes((ModToolsTiers.ARKANICITA),3 , 3f))));

    public static final DeferredItem<HoeItem> ARKANICITA_HOE = ITEMS.register("arkanicita_hoe",
            () -> new HoeItem(ModToolsTiers.ARKANICITA, new Item.Properties()
                    .attributes(HoeItem.createAttributes((ModToolsTiers.ARKANICITA),2, 3f))));

    public static final DeferredItem<SwordItem> BAHAMUT_HAMMER = ITEMS.register("bahamut_hammer",
            () -> new SwordItem(ModToolsTiers.ARKANICITA, new Item.Properties()
                    .attributes(SwordItem.createAttributes((ModToolsTiers.ARKANICITA),5 , 3f))));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



}

