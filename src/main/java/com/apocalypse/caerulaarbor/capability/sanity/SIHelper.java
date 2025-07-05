package com.apocalypse.caerulaarbor.capability.sanity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;

public class SIHelper {

    public static boolean causeSanityInjury(LivingEntity living, double value) {
        if (living.hasEffect(ModMobEffects.SANITY_IMMUNE.get())) return false;

        var sanRate = living.getAttribute(ModAttributes.SANITY_RATE.get());
        var resistance = living.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get());

        double sanRateValue = sanRate == null ? 0 : sanRate.getValue();
        double sanModifier = resistance == null ? 0 : resistance.getValue();

        double damage = value * (1 + sanRateValue / 100D) * (1 - sanModifier / 100D);
        living.getCapability(ModCapabilities.SANITY_INJURY).ifPresent(
                cap -> cap.hurt(damage)
        );
        return true;
    }

    public static void causeSanityInjuryWithParticles(LivingEntity living, double value) {
        if (!causeSanityInjury(living, value)) return;
        for (int i = 1; i < 4; i++) {
            CaerulaArborMod.queueServerWork(i * 3, () -> {
                if (living.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            living.getX(), (living.getY() + 0.5 * living.getBbHeight()), living.getZ(),
                            24, 0.86, 1.2, 0.86, 0.1);
                }
            });
        }
    }
}
