package net.mibyon.aquariomod.entity;

import net.mibyon.aquariomod.effect.ModEffects;
import net.mibyon.aquariomod.item.Moditems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class WorkCardEntity extends ThrowableItemProjectile {

    private static final int EFFECT_DURATION_TICKS = 40;

    public WorkCardEntity(EntityType<? extends WorkCardEntity> type, Level level) {
        super(type, level);
    }

    public WorkCardEntity(Level level, LivingEntity thrower) {
        super(ModEntities.WORK_CARD.get(), thrower, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Moditems.CARTEIRA_TRABALHO.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (level().isClientSide) {
            return;
        }

        if (result.getEntity() instanceof Player hitPlayer) {
            hitPlayer.addEffect(new MobEffectInstance(ModEffects.VIROU_CLT, EFFECT_DURATION_TICKS, 0, false, false, false));
        }

        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!level().isClientSide) {
            discard();
        }
    }
}