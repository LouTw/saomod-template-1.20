package com.lou.sao.entity.player;

import javax.annotation.Nullable;

import com.lou.sao.Item.Moditems;
import com.lou.sao.world.dimension.ModDimension;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class CustomPortalPlayerEntity extends PlayerEntity {
    
    private PlayerListEntry playerListEntry;
    private int sleepTimer;
    private boolean isSleeping;
    private boolean isPlayingVideo;

    public CustomPortalPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isSleeping && ((ArmorItem)this.getInventory().getArmorStack(3).getItem()) == Moditems.NervGear) {
            this.sleepTimer++;
            if (this.sleepTimer >= 60) { // 3 seconds (60 ticks)
                this.playVideo();
            }
        }
    }

    private void playVideo() {
        this.isPlayingVideo = true;
        // 播放视频的逻辑


        // 视频播放完毕后调用传送逻辑
        this.isPlayingVideo = false;
        this.teleportToSAODimension();
    }

    private void teleportToSAODimension() {
        if (this.getWorld() instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld)this.getWorld();
            MinecraftServer minecraftServer = serverWorld.getServer();
            RegistryKey<World> registryKey = this.getWorld().getRegistryKey() == ModDimension.SAO_WORLD_KEY ? World.OVERWORLD : ModDimension.SAO_WORLD_KEY;
            ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
            if (serverWorld2 != null && !this.hasVehicle()) {
                this.getWorld().getProfiler().push("portal");
                this.resetPortalCooldown();
                this.moveToWorld(serverWorld2);
                this.getWorld().getProfiler().pop();
            }
        } 
        this.tickPortalCooldown();
    }    

    @Override
    public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers) {
        super.wakeUp(skipSleepTimer, updateSleepingPlayers);
        this.isSleeping = false;
        this.isPlayingVideo = false;
        this.sleepTimer = 0;
    }

    @Override
    public Either<SleepFailureReason, Unit> trySleep(BlockPos pos) {
        this.isSleeping = true;
        this.sleepTimer = 0;
        return Either.right(Unit.INSTANCE);
    }

    @Override
    public boolean isCreative() {
        PlayerListEntry playerListEntry = this.getPlayerListEntry();
		return playerListEntry != null && playerListEntry.getGameMode() == GameMode.CREATIVE;
    }

    @Override
    public boolean isSpectator() {
        PlayerListEntry playerListEntry = this.getPlayerListEntry();
		return playerListEntry != null && playerListEntry.getGameMode() == GameMode.SPECTATOR;
    }

    @Nullable
	protected PlayerListEntry getPlayerListEntry() {
		if (this.playerListEntry == null) {
			this.playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(this.getUuid());
		}

		return this.playerListEntry;
	}
}