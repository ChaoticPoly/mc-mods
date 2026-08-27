package com.example.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

public class TestUse extends Item{
    public TestUse(Properties properties){
        super(properties);

    }
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
       	if (level.isClientSide()) {
            return InteractionResult.PASS;
        }
        if (level instanceof ServerLevel serverLevel) {
            DustParticleOptions dustColor = new DustParticleOptions(0x55FF55, 1.0f);
            double radius = 1;
            Vec3 loc = player.position();
            final double [] repeattimer = {0};
            TickManager.addTask(() -> {
                    if (repeattimer[0] > 80){
                        return true;
                    }
                    if (repeattimer[0]%15 == 0){
                        final double [] i = {1};
                        TickManager.addTask(() -> {
                            if (i[0] > 50) {
                                return true; // signal: done, remove me
                            }

                            for (int j = 1; j <= 6; j++) {
                                double angle = (2 * Math.PI * j / 6) + (Math.PI / 18) * i[0];
                                double offsetX = radius * Math.cos(angle);
                                double offsetZ = radius * Math.sin(angle);
                                Vec3 particlePos = loc.add(offsetX, i[0] / 10, offsetZ);
                                serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, particlePos.x, particlePos.y, particlePos.z, 0, 0, 1, 0, 1);
                            }

                            i[0]++;
                            return false; // not done yet
                        });
                    }
                    if (repeattimer[0] % 12 == 0){
                        final double[] totem = {1};
                        TickManager.addTask(() -> {
                            if (totem[0] > 28) {
                                return true; // signal: done, remove me
                            }
                            for (int j = 1; j <= 3; j++) {
                                double angle = (2 * Math.PI * j / 3) - (Math.PI / 12) * totem[0];
                                double offsetX = radius * Math.cos(angle);
                                double offsetZ = radius * Math.sin(angle);
                                Vec3 particlePos = loc.add(offsetX, totem[0] / 6, offsetZ);
                                serverLevel.sendParticles(ParticleTypes.COMPOSTER, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0, 1, 0);
                            }

                            totem[0]++;
                            return false; // not done yet
                        });
                    }
                    if (repeattimer[0] % 12 == 6){
                        final double[] totem = {1};
                        TickManager.addTask(() -> {
                            if (totem[0] > 28) {
                                return true; // signal: done, remove me
                            }
                            for (int j = 1; j <= 3; j++) {
                                double angle = (2 * Math.PI * j / 3) - (Math.PI / 12) * totem[0] + (Math.PI/3);
                                double offsetX = radius * Math.cos(angle);
                                double offsetZ = radius * Math.sin(angle);
                                Vec3 particlePos = loc.add(offsetX, totem[0] / 6, offsetZ);
                                serverLevel.sendParticles(ParticleTypes.COMPOSTER, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0, 1, 0);
                            }

                            totem[0]++;
                            return false; // not done yet
                        });
                    }
                    repeattimer[0]++;
                    return false;
                });
            final double [] ringTimer = {0};
            TickManager.addTask(() -> {
                    if (ringTimer[0] > 80){
                        return true;
                    }
                    if (ringTimer[0] % 20 == 0){
                        int particleCount = 60;
                        for (int k = 1; k <=particleCount; k++){
                            double angle = 2*Math.PI*k/particleCount;
                            double offsetX = radius*Math.cos(angle);
                            double offsetZ = radius*Math.sin(angle);
                            Vec3 particlePos = loc.add(offsetX,0,offsetZ);
                            serverLevel.sendParticles( ParticleTypes.END_ROD, particlePos.x, particlePos.y,particlePos.z,0,0,1.3,0, 0.35);
                        }
                    }
                    ringTimer[0]++;
                    return false;

                });
        }
        return InteractionResult.SUCCESS;
    }
}
