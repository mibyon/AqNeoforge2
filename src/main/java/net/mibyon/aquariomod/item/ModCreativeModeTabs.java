package net.mibyon.aquariomod.item;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AquarioMod.MODID);

    public static final Supplier<CreativeModeTab> AQUARIO_ITEMS = CREATIVE_MODE_TABS.register("aquario_items",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(Moditems.PEIXE_ICON.get()))
                    .title(Component.translatable("creativetab.aquariomod.aquario_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //placas

                        output.accept(Moditems.PLACA_PUNK.get());
                        output.accept(Moditems.PLACA_DIVO.get());
                        output.accept(Moditems.PLACA_ESPADACHIM.get());
                        output.accept(Moditems.PLACA_NERD.get());
                        output.accept(Moditems.PLACA_BOTANICO.get());
                        output.accept(Moditems.PLACA_CIENTISTA.get());
                        output.accept(Moditems.PLACA_DORY.get());
                        output.accept(Moditems.PLACA_FOFOQUEIRO.get());
                        output.accept(Moditems.KATANA_TURQUESA.get());

                        //artefatos


                        output.accept(Moditems.VOLITEMIS_CAPACETE.get());
                        output.accept(Moditems.COLAR_CORACAO.get());
                        output.accept(Moditems.COLAR_FLAMEJANTE.get());
                        output.accept(Moditems.ANEL_DA_PROTECAO.get());
                        output.accept(Moditems.POMPOM.get());

                        //arkanicita

                        output.accept(Moditems.ARKANICITA.get());
                        output.accept(Moditems.ARKANICITA_AXE.get());
                        output.accept(Moditems.ARKANICITA_HOE.get());
                        output.accept(Moditems.ARKANICITA_SWORD.get());
                        output.accept(Moditems.ARKANICITA_PICKAXE.get());
                        output.accept(Moditems.ARKANICITA_SHOVEL.get());

                        //anti arkanicita


                        output.accept(Moditems.ANTI_ARKANICITA.get());
                        output.accept(Moditems.ANTI_ARKANICITA_AXE.get());
                        output.accept(Moditems.ANTI_ARKANICITA_HOE.get());
                        output.accept(Moditems.ANTI_ARKANICITA_SWORD.get());
                        output.accept(Moditems.ANTI_ARKANICITA_PICKAXE.get());
                        output.accept(Moditems.ANTI_ARKANICITA_SHOVEL.get());

                        //hydrolith

                        output.accept(Moditems.HYDROLITH.get());
                        output.accept(Moditems.HYDROLITH_AXE.get());
                        output.accept(Moditems.HYDROLITH_HOE.get());
                        output.accept(Moditems.HYDROLITH_SWORD.get());
                        output.accept(Moditems.HYDROLITH_PICKAXE.get());
                        output.accept(Moditems.HYDROLITH_SHOVEL.get());
                        output.accept(Moditems.HYDROLITH_INGOT.get());

                        output.accept(Moditems.BAHAMUT_HAMMER.get());
                        output.accept(Moditems.DAEMYSH_SPEAR.get());

                    }).build());


    public static final Supplier<CreativeModeTab> AQUARIO_BLOCOS = CREATIVE_MODE_TABS.register("aquario_blocos",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(Moditems.FABIO_ICON.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, "aquario_items"))
                    .title(Component.translatable("creativetab.aquariomod.aquario_blocos"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ARKANICITA_ORE.get());
                        output.accept(ModBlocks.ARKANICITA_BLOCK.get());
                        output.accept(ModBlocks.ANTI_ARKANICITA_BLOCK.get());
                        output.accept(ModBlocks.ANTI_ARKANICITA_ORE.get());
                        output.accept(ModBlocks.HYDROLITH_ORE.get());
                        output.accept(ModBlocks.HYDROLITH_BLOCK.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}

