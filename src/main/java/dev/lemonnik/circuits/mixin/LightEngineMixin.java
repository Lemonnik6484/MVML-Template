package dev.lemonnik.circuits.mixin;

import dev.lemonnik.circuits.CircuitsClient;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.lighting.SkyLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public abstract class LightEngineMixin {
    @Inject(method = "getLightValue", at = @At("HEAD"), cancellable = true)
    private void circuits$limboSkyLayer(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (CircuitsClient.isActive()) {
            cir.setReturnValue(15);
        }
    }
}
