package com.lou.sao.util;

import com.lou.sao.Item.Moditems;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;


public class ModLootTableModifier {
    // 修改原版战利品箱列表，例：修改丛林神殿的战利品列表
    // 同理，可以修改生物的战利品列表，path->entities/xxx
    private static final Identifier JUNGLE_TEMPLE_ID = 
            new Identifier("minecraft","chests/jungle_temple");
    private static final Identifier SUS_SAND_ID = 
            new Identifier("minecraft","archaeology/desert_pyramid");

    public static void modifierLootTables(){
        // MODIFY：仅修改 REPLACE：直接替换 此处为修改写法 
        // 具体参考 VanillaChestLootTableGenerator类中的原版写法
        LootTableEvents.MODIFY.register((resourceManager,lootManager,id,tableBuilder,source) -> {
            if (JUNGLE_TEMPLE_ID.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(1.0f))
                .with(ItemEntry.builder(Moditems.Blade_NightFall)) // 需要添加到战利品列表的物品
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            }
        });
        // 此处为直接替换写法
        LootTableEvents.REPLACE.register((resourceManager,lootManager,id,original,source) -> {
            if (SUS_SAND_ID.equals(id)){
                LootPool.Builder pool = LootPool.builder()
                .rolls(UniformLootNumberProvider.create(1.0f, 5.0f))
                .with(ItemEntry.builder(Moditems.Blade_NightFall).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)))) // 需要添加到战利品列表的物品
                .with(ItemEntry.builder(Moditems.Blade_SunRise).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)))); // 需要添加到战利品列表的物品
                return LootTable.builder().pool(pool).build();
            }
            return null;
        });
    }
}
