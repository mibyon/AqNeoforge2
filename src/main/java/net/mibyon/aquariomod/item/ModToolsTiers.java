package net.mibyon.aquariomod.item;

import net.mibyon.aquariomod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolsTiers {
    public static final Tier ARKANICITA = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ARKANICITA_TOOL,
            1400,4f,3f,28,() ->Ingredient.of(Moditems.ARKANICITA));


    {
    }



}
