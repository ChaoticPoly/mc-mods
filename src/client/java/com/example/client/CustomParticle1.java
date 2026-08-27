package com.example.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;

public class CustomParticle1 extends SimpleAnimatedParticle {
   protected Particle particle;
   private CustomParticle1(final ClientLevel level, final double x, final double y, final double z, final double xa, final double ya, final double za, final SpriteSet sprites) {
      super(level, x, y, z, sprites, 0.0F);
      this.xd = xa;
      this.yd = ya;
      this.zd = za;
      this.quadSize *= 0.75F;
      this.lifetime = 80;
      this.friction = 1.05F;
      this.setFadeColor(15916745);
      this.setSpriteFromAge(sprites);
   }
   public void move(final double xa, final double ya, final double za) {
         this.setBoundingBox(this.getBoundingBox().move(xa, ya, za));
         this.setLocationFromBoundingbox();
      }

      public static class Provider implements ParticleProvider<SimpleParticleType> {
         private final SpriteSet sprites;

         public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
         }

         public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            return new CustomParticle1(level, x, y, z, xAux, yAux, zAux, this.sprites);
         }
      }
   }
