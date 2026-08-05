package net.mibyon.aquariomod.datagen;
import net.minecraft.world.level.block.Block;
import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.item.Moditems;
import net.mibyon.aquariomod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModitemTagProvider extends ItemTagsProvider {
    public ModitemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AquarioMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(Moditems.ARKANICITA.get());


        tag(ItemTags.SWORDS)
                .add(Moditems.ARKANICITA_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(Moditems.ARKANICITA_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(Moditems.ARKANICITA_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(Moditems.ARKANICITA_AXE.get());
        tag(ItemTags.HOES)
                .add(Moditems.ARKANICITA_HOE.get());
        tag(ItemTags.SWORDS)
                .add(Moditems.BAHAMUT_HAMMER.get());
        tag(ItemTags.SWORDS)
                .add(Moditems.DAEMYSH_SPEAR.get());



        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(Moditems.ANTI_ARKANICITA.get());

        tag(ItemTags.SWORDS)
                .add(Moditems.ANTI_ARKANICITA_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(Moditems.ANTI_ARKANICITA_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(Moditems.ANTI_ARKANICITA_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(Moditems.ANTI_ARKANICITA_AXE.get());
        tag(ItemTags.HOES)
                .add(Moditems.ANTI_ARKANICITA_HOE.get());




        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(Moditems.HYDROLITH.get());


        tag(ItemTags.SWORDS)
                .add(Moditems.HYDROLITH_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(Moditems.HYDROLITH_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(Moditems.HYDROLITH_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(Moditems.HYDROLITH_AXE.get());
        tag(ItemTags.HOES)
                .add(Moditems.HYDROLITH_HOE.get());
        tag(ItemTags.SWORDS)
                .add(Moditems.KATANA_TURQUESA.get());
    }
}

