package dev.lemonnik.circuits.mixin;

import dev.lemonnik.circuits.CircuitsClient;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public abstract class ConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void circuits$keepLimboLocal(Packet<?> packet, CallbackInfo ci) {
        if (CircuitsClient.isActive() && (packet instanceof ServerboundMovePlayerPacket
                || packet instanceof ServerboundPlayerActionPacket
                || packet instanceof ServerboundSetCreativeModeSlotPacket
                || packet instanceof ServerboundUseItemOnPacket
                || packet instanceof ServerboundUseItemPacket)) {
            ci.cancel();
        }
    }
}
