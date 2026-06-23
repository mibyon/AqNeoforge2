package net.mibyon.aquariomod.util;
import net.minecraft.world.level.block.Block;
import net.mibyon.aquariomod.AquarioMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ARKANICITA_TOOL = createTag("needs_arkanicita_tool");
        public static final TagKey<Block> INCORRECT_FOR_ARKANICITA_TOOL = createTag("incorrect_for_arkanicita_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, name));

        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AquarioMod.MODID, name));
        }
    }
}


