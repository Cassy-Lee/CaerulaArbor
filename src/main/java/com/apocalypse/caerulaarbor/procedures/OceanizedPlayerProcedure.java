package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class OceanizedPlayerProcedure {
    public static boolean execute(LevelAccessor world, double x, double y, double z) {
        final Vec3 center = new Vec3(x, y, z);
        return world.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(32 / 2d), e -> true)
                .stream()
                .noneMatch(player -> player.getCapability(ModCapabilities.PLAYER_VARIABLE)
                        .map(cap -> cap.player_oceanization == 3)
                        .orElse(false)
                );
    }
}
