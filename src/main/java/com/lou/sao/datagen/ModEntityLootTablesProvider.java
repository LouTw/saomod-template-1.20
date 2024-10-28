package com.lou.sao.datagen;

import java.util.function.BiConsumer;

import com.lou.sao.SAOMod;
import com.lou.sao.Item.Moditems;
import com.lou.sao.entity.ModEntities;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.condition.TimeCheckLootCondition;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModEntityLootTablesProvider extends SimpleFabricLootTableProvider {

    public ModEntityLootTablesProvider(FabricDataOutput dataGenerator, LootContextTypes lootContextTypes) {
        super(dataGenerator, LootContextTypes.ENTITY);
    }

    @Override
    public void accept(BiConsumer<Identifier, Builder> exporter) {
       // 添加自定义怪物的掉落战利品列表
        EntityLootTableGenerator.register(ModEntities.WUBO, createWUBOTableBuilder());
    }

    // 此处是仿写远古守卫者的掉落战利品列表，如果想要别的实体类的掉落模式，请参考EntityLootTableGenerator中其他实体
	public LootTable.Builder createWUBOTableBuilder() {
		return LootTable.builder()
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(
						ItemEntry.builder(Moditems.Blade_NightFall)
							.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
					)
					.conditionally(TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(12000, 24000)))
					.conditionally(KilledByPlayerLootCondition.builder())
					.conditionally(RandomChanceWithLootingLootCondition.builder(1.0F, 0.01F))
			)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(
						ItemEntry.builder(Moditems.Blade_SunRise)
							.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
					)
					.conditionally(TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(0, 12000)))
					.conditionally(KilledByPlayerLootCondition.builder())
					.conditionally(RandomChanceWithLootingLootCondition.builder(1.0F, 0.01F))
			);
	}

}
