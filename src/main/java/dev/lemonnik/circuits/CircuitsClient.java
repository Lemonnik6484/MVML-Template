package dev.lemonnik.circuits;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

public final class CircuitsClient implements ClientModInitializer {
    private static final Map<BlockPos, BlockState> CHANGED_BLOCKS = new LinkedHashMap<>();
    private static Vec3 returnPosition;
    private static float returnYaw;
    private static float returnPitch;
    private static GameType returnGameType;
    private static boolean active;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("limbo").executes(context -> toggle())));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (active && (client.player == null || client.player.isDeadOrDying())) {
                leave(client, false);
            }
        });
    }

    public static boolean isActive() {
        return active;
    }

    private static int toggle() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return 0;
        if (active) {
            leave(minecraft);
        } else {
            enter(minecraft);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static void enter(Minecraft minecraft) {
        Player player = minecraft.player;
        returnPosition = player.position();
        returnYaw = player.getYRot();
        returnPitch = player.getXRot();
        returnGameType = minecraft.gameMode.getPlayerMode();

        BlockPos base = BlockPos.containing(returnPosition).offset(32, 0, 0);
        active = true;
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                for (int z = 0; z < 20; z++) {
                    if (x == 0 || x == 19 || y == 0 || y == 19 || z == 0 || z == 19) {
                        setLocal(minecraft, base.offset(x, y, z), Blocks.WHITE_CONCRETE.defaultBlockState());
                    }
                }
            }
        }
        for (int x = 1; x < 19; x++) {
            for (int y = 1; y < 19; y++) {
                for (int z = 1; z < 19; z++) {
                    if (x == 1 || x == 18 || y == 1 || y == 18 || z == 1 || z == 18) {
                        setLocal(minecraft, base.offset(x, y, z), Blocks.BARRIER.defaultBlockState());
                    }
                }
            }
        }
        for (int x = 2; x < 18; x++) {
            for (int y = 2; y < 18; y++) {
                for (int z = 2; z < 18; z++) {
                    setLocal(minecraft, base.offset(x, y, z), Blocks.AIR.defaultBlockState());
                }
            }
        }
        minecraft.gameMode.setLocalMode(GameType.CREATIVE);
        player.setPos(base.getX() + 10.5, base.getY() + 2, base.getZ() + 10.5);
        player.setDeltaMovement(Vec3.ZERO);
        player.setYRot(0);
        player.setXRot(0);
    }

    private static void leave(Minecraft minecraft) {
        leave(minecraft, true);
    }

    private static void leave(Minecraft minecraft, boolean restorePosition) {
        for (Map.Entry<BlockPos, BlockState> entry : CHANGED_BLOCKS.entrySet()) {
            if (minecraft.level != null) {
                minecraft.level.setBlock(entry.getKey(), entry.getValue(), 3);
            }
        }
        CHANGED_BLOCKS.clear();
        active = false;
        Player player = minecraft.player;
        if (minecraft.gameMode != null && returnGameType != null) {
            minecraft.gameMode.setLocalMode(returnGameType);
        }
        if (restorePosition && player != null) {
            player.setPos(returnPosition.x, returnPosition.y, returnPosition.z);
            player.setYRot(returnYaw);
            player.setXRot(returnPitch);
            player.setDeltaMovement(Vec3.ZERO);
        }
    }

    private static void setLocal(Minecraft minecraft, BlockPos pos, BlockState state) {
        CHANGED_BLOCKS.putIfAbsent(pos.immutable(), minecraft.level.getBlockState(pos));
        minecraft.level.setBlock(pos, state, 3);
    }
}
