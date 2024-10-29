package com.lou.sao.Item.custom;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// 生成带有自定义功能的方块或物品，例如以下实现一个回程石（neta炉石）的物品
public class Hearthstone extends Item{

    private final List<BlockPos> recordedPositions = new ArrayList<>();

    public Hearthstone(Settings settings) {
        super(settings);
    }

    // 重写使用函数，此处重写useOnBlock
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // 首先确认是否为客户端
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        if(!world.isClient() && player != null && player.isSneaking()){
            // 清空之前记录的位置
            recordedPositions.clear();
            // 记录新的方块位置
            recordedPositions.add(blockpos);
            player.sendMessage(Text.of("Recorded block position: " + blockpos), false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // 首先确认是否为客户端
        if(!world.isClient()){
            if (!recordedPositions.isEmpty()) {
                // 传送玩家到记录的第一个方块位置
                BlockPos pos = recordedPositions.get(0);
                playerEntity.teleport(pos.getX(), pos.getY(), pos.getZ());
            } else {
                // 如果列表为空，输出提示信息
                playerEntity.sendMessage(Text.of("No recorded positions to teleport to."), false);
            }
        }
        // 对使用后的物品降低耐久度
        playerEntity.getStackInHand(hand).damage(1, playerEntity, PlayerEntity -> PlayerEntity.sendToolBreakStatus(PlayerEntity.getActiveHand()));
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    // 获取记录的方块位置列表
    public List<BlockPos> getRecordedPositions() {
        return recordedPositions;
    }
}
