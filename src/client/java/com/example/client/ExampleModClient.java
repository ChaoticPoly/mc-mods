package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import com.example.ExampleMod;


public class ExampleModClient implements ClientModInitializer {

        @Override
        public void onInitializeClient() {

            ParticleProviderRegistry.getInstance().register(ExampleMod.TEST_PARTICLE, CustomParticle1.Provider::new);

        }

    }
