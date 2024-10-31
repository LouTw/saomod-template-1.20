package com.lou.sao.Item.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PetReviveCrystal extends Item {

    // 记录玩家最近死亡的宠物信息
    private static final Map<UUID, EntityType<? extends TameableEntity>> lastDeadPets = new HashMap<>();

    public PetReviveCrystal(Settings settings) {
        super(settings);
        // 注册宠物死亡事件监听器
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof TameableEntity) {
                TameableEntity tameableEntity = (TameableEntity) entity;
                onPetDeath(tameableEntity);
            }
        });
    }

    // 记录宠物死亡信息的方法
    public static void recordPetDeath(PlayerEntity player, EntityType<? extends TameableEntity> petType) {
        lastDeadPets.put(player.getUuid(), petType);
    }

    // 宠物死亡事件处理方法
    @SuppressWarnings("unchecked")
    private void onPetDeath(LivingEntity entity) {
        if (entity instanceof TameableEntity) {
            TameableEntity tameableEntity = (TameableEntity) entity;
            PlayerEntity owner = (PlayerEntity) tameableEntity.getOwner();
            if (owner != null) {
                recordPetDeath(owner, (EntityType<? extends TameableEntity>) tameableEntity.getType());
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();

        if (!world.isClient() && player != null) {
            // 检查玩家是否有最近死亡的宠物
            EntityType<? extends TameableEntity> petType = lastDeadPets.get(player.getUuid());
            if (petType != null) {
                // 检查是否在特定方块上使用道具
                if (isSpecialBlock(world, pos)) {
                    // 重生宠物并驯服于玩家
                    TameableEntity pet = petType.create(world);
                    if (pet != null) {
                        pet.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                        pet.setOwner(player);
                        world.spawnEntity(pet);
                        player.sendMessage(Text.of("Pet revived and tamed!"), false);
                        // 移除记录的宠物信息
                        lastDeadPets.remove(player.getUuid());
                        return ActionResult.SUCCESS;
                    }
                } else {
                    player.sendMessage(Text.of("You need to use this on a special block!"), false);
                }
            } else {
                player.sendMessage(Text.of("No pet to revive!"), false);
            }
        }
        return ActionResult.PASS;
    }

    // 检查是否为特定方块的方法
    private boolean isSpecialBlock(World world, BlockPos pos) {
        // 检查特定方块的逻辑
        return world.getBlockState(pos).getBlock() == ModBlocks.Blade_nightfall_block;
    }
}