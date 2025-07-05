package com.apocalypse.caerulaarbor.capability.sanity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;
import java.util.Optional;

public class SanityInjuryCapability implements ISanityInjuryCapability {
    public static final ResourceLocation ID = CaerulaArborMod.loc("sanity_injury");

    private final LivingEntity owner;
    private double value;

    public SanityInjuryCapability(LivingEntity owner) {
        this(owner, 1000);
    }

    public SanityInjuryCapability(LivingEntity owner, double value) {
        this.owner = owner;
        this.value = Mth.clamp(value, 0, 1000);
    }

    @Override
    public boolean hurt(double damage) {
        if (owner.hasEffect(ModMobEffects.SANITY_IMMUNE.get())) return false;
        if (damage <= 0) return false;
        if (value < damage) sanityBreak();
        this.value -= damage;
        return true;
    }

    // TODO 元素爆发
    private void sanityBreak() {}

    public void tick() {
        double regenerateRate = Objects.requireNonNull(owner.getAttribute(ModAttributes.SANITY_REGENERATE.get())).getValue();
        if (regenerateRate > 0) this.heal(regenerateRate);
    }

    public double getValue() {
        return value;
    }

    @Override
    public void heal(double value) {
        this.value = Math.min(this.value + value, 1000);
    }

    @Override
    public DoubleTag serializeNBT() {
        return DoubleTag.valueOf(this.value);
    }

    @Override
    public void deserializeNBT(DoubleTag nbt) {
        this.value = nbt.getAsDouble();
    }
}
