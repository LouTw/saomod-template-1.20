package com.lou.sao.datagen;

import java.util.HashMap;
import java.util.Map;

import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;
import com.lou.sao.entity.ModEntities;
import com.lou.sao.entity.custom.WuboEntity;
import com.lou.sao.util.ModTags.Items;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.condition.TimeCheckLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;



public class ModLootTablesProvider extends FabricBlockLootTableProvider{

    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // 生成loot_tables/blocks，例：生成blade_nightfall_block.json并加入掉落
        addDrop(ModBlocks.Blade_nightfall_block);
		addDrop(ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK);

		// 添加掉落模式，例：对blade_nightfall_block应用铜矿类的掉落模式，第一项是方块，第二项是掉落物品
        addDrop(ModBlocks.Blade_nightfall_block,OreLikeDrops(ModBlocks.Blade_nightfall_block,Moditems.Blade_NightFall));
		
		// 添加自定义怪物的掉落战利品列表
		this.register(ModEntities.WUBO, createWUBOTableBuilder());
	}
	
	// 此处是仿写铜矿的掉落模式，如果想要别的方块类的掉落模式，请参考BlockLootTableGenerator中其他方块
    public LootTable.Builder OreLikeDrops(Block drop, Item item) {
		return dropsWithSilkTouch(
			drop,
			(LootPoolEntry.Builder<?>)this.applyExplosionDecay(
				drop,
				ItemEntry.builder(Moditems.Blade_NightFall)
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
					.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
			)
		);
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
