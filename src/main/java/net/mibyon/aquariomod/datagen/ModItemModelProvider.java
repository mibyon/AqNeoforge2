package net.mibyon.aquariomod.datagen;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {


        public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, AquarioMod.MODID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            basicItem(Moditems.ARKANICITA.get());

            handheldItem(Moditems.ARKANICITA_SWORD);
            handheldItem(Moditems.ARKANICITA_PICKAXE);
            handheldItem(Moditems.ARKANICITA_SHOVEL);
            handheldItem(Moditems.ARKANICITA_AXE);
            handheldItem(Moditems.ARKANICITA_HOE);;
        }

        public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                    .texture("texture",  ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID,
                            "block/" + baseBlock.getId().getPath()));
        }

        public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                    .texture("texture",  ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID,
                            "block/" + baseBlock.getId().getPath()));
        }

        public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                    .texture("wall",  ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID,
                            "block/" + baseBlock.getId().getPath()));
        }

        private ItemModelBuilder handheldItem(DeferredItem<?> item) {
            return withExistingParent(item.getId().getPath(),
                    ResourceLocation.parse("item/handheld")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID,"item/" + item.getId().getPath()));
        }
    }

