package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.sanity.SanityInjuryCapability;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GetSanityIndexProcedure {
	public static double execute(LivingEntity entity) {
		if (entity == null)
			return 0;
		return entity.getCapability(ModCapabilities.SANITY_INJURY)
				.filter(cap -> cap instanceof SanityInjuryCapability)
				.map(cap -> (SanityInjuryCapability) cap)
				.map(SanityInjuryCapability::getValue).orElse(0D);
	}
}
