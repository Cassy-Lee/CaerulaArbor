package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.GuideAbyssalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuideAbyssalModel extends GeoModel<GuideAbyssalEntity> {
	@Override
	public ResourceLocation getAnimationResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/tracerabyssal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/tracerabyssal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
