package net.mibyon.aquariomod.block;

import net.minecraft.world.level.block.Block;
import net.mibyon.aquariomod.AquarioMod;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AquarioMod.MODID);

    public static final DeferredBlock<Block>ARKANICITA_ORE =registerBlock("arkanicita_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));
    public static final DeferredBlock<Block>ARKANICITA_BLOCK =registerBlock("arkanicita_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block>ANTI_ARKANICITA_BLOCK =registerBlock("anti_arkanicita_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));
    public static final DeferredBlock<Block>ANTI_ARKANICITA_ORE =registerBlock("anti_arkanicita_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));
    public static final DeferredBlock<Block>HYDROLITH_BLOCK =registerBlock("hydrolith_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));
    public static final DeferredBlock<Block>HYDROLITH_ORE =registerBlock("hydrolith_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }






    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block ) {
        Moditems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));



    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }






}

