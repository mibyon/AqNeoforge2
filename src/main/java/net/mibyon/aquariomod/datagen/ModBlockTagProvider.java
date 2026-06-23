package net.mibyon.aquariomod.datagen;

import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.block.ModBlocks;
import net.mibyon.aquariomod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, AquarioMod.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.ARKANICITA_BLOCK.get())
                    .add(ModBlocks.ARKANICITA_ORE.get());



            tag(BlockTags.NEEDS_DIAMOND_TOOL)
                    .add(ModBlocks.ARKANICITA_ORE.get());



            tag(ModTags.Blocks.NEEDS_ARKANICITA_TOOL);



            tag(ModTags.Blocks.INCORRECT_FOR_ARKANICITA_TOOL)
                    .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                    .remove(ModTags.Blocks.NEEDS_ARKANICITA_TOOL);
        }
    }

