package com.lou.sao.datagen;

import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;



public class ModLootTablesProvider extends FabricBlockLootTableProvider{

    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // 生成loot_tables/blocks，例：生成blade_nightfall_block.json并加入掉落
        addDrop(ModBlocks.Blade_nightfall_block);

		// 添加掉落模式，例：对blade_nightfall_block应用铜矿类的掉落模式，第一项是方块，第二项是掉落物品
        addDrop(ModBlocks.Blade_nightfall_block,OreLikeDrops(ModBlocks.Blade_nightfall_block,Moditems.Blade_NightFall));
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


}
