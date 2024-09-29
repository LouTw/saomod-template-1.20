package com.lou.sao.Item.custom;


import com.lou.sao.sounds.ModSounds;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// 生成带有自定义功能的方块或物品，例如以下实现一个回程石（neta炉石）的物品
public class Hearthstone extends Item{

    public Hearthstone(Settings settings) {
        super(settings);
    }

    // 重写使用函数，此处重写useOnBlock
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // 首先确认是否为客户端
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        if(!world.isClient()){
            //还未实现下面的回程功能，之后再看
            context.getWorld().playSound(null, blockpos, ModSounds.BLADE_NIGHTFALL_SOUND_EVENT, SoundCategory.PLAYERS,1f,1f);
        }
        // 对使用后的物品降低耐久度
        context.getStack().damage(1, context.getPlayer(), PlayerEntity -> PlayerEntity.sendToolBreakStatus(PlayerEntity.getActiveHand()));;
        return ActionResult.SUCCESS;
    }
}
