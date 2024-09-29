package com.lou.sao.util;

import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;
import com.lou.sao.villager.ModVillagers;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

// 自定义村民交易
public class ModTrades {
    public static void registerTrades(){
        // 选择村民职业和村民好感度等级
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 1
        , factories -> {
            // 添加新的交易列表，第一个是拿来交易的物品和个数，第二个是交易获得的物品和个数
            factories.add((entity,random) -> new TradeOffer(
                new ItemStack(Items.EMERALD,20),
                new ItemStack(Moditems.Blade_NightFall,1),
                6,5,0.05f
            ));
            factories.add((entity,random) -> new TradeOffer(
                new ItemStack(Items.EMERALD,10),
                new ItemStack(Moditems.Blade_SunRise,1),
                5,7,0.01f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLADE_NIGHTFALL_MASTER, 1
        ,factories ->{
            factories.add((entity,random) -> new TradeOffer(
                new ItemStack(ModBlocks.Blade_nightfall_block,999),
                new ItemStack(Moditems.Blade_NightFall,1),
                1,5,0.05f
            ));
        });
    }
}
