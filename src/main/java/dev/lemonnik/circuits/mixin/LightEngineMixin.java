package dev.lemonnik.circuits.mixin;

import dev.lemonnik.circuits.Circuits;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.lighting.LightEngine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public abstract class LightEngineMixin {
    @Unique
    private static final ResourceKey<Level> DESIGN_DIM_KEY = ResourceKey.create(
            Registries.DIMENSION, Circuits.id("design"));

    @Shadow @Final protected LightChunkGetter chunkSource;

    @Inject(method = "getLightValue", at = @At("HEAD"), cancellable = true)
    private void circuits$DesignSkylight(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (chunkSource.getLevel() instanceof Level level && level.dimension().equals(DESIGN_DIM_KEY)) {
            cir.setReturnValue(15);
        }
    }
}