package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.EndRodParticle;



public class ExampleModClient implements ClientModInitializer {

        @Override
        public void onInitializeClient() {

            ParticleProviderRegistry.getInstance().register(ExampleMod.TEST_PARTICLE, EndRodParticle.Provider::new);

        }

    }
