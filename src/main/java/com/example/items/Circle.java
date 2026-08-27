package com.example.items;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.server.level.ServerLevel;
import com.example.ExampleMod;

public class Circle extends Item {
    public Circle(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()){
            return InteractionResult.PASS;
        }
        if (level instanceof ServerLevel serverLevel) {
            double radius = 1;
            Vec3 loc = player.position();
            final double [] timer = {0};
            TickManager.addTask(() -> {
                if(timer[0] > 80) return true;
                int particleCount = 40;

                double angle = 2 * Math.PI * timer[0] / particleCount;
                Vec3 offset = new Vec3(radius*Math.cos(angle), 0, radius*Math.sin(angle));
                Vec3 particleLoc = loc.add(offset);
                serverLevel.sendParticles(ExampleMod.TEST_PARTICLE, particleLoc.x, particleLoc.y, particleLoc.z, 0, 0, 1.3, 0, 0.35);
                timer[0]++;
                return false;
            });

        }
        return InteractionResult.SUCCESS;
    }
}
