package com.lou.sao.datagen;

import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelsProvider extends FabricModelProvider{

    public ModModelsProvider(FabricDataOutput output) {
        super(output);
        
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // 生成blockstates\blade_nightfall_block.json，例：父类继承cubeall
        // 生成models\block\blade_nightfall_block.json
        // 生成models\item\blade_nightfall_block.json
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.Blade_nightfall_block);

        blockStateModelGenerator.registerSimpleState(ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // 生成models\item\blade_nightfall.json，例：父类继承generated，此处generated指一般手持模式
        itemModelGenerator.register(Moditems.Blade_NightFall,Models.GENERATED);
        itemModelGenerator.register(Moditems.Blade_SunRise,Models.GENERATED);

        // 生成models\item\blade_nightfall_pickaxe.json，例父类继承手持物品，此处handheld指手持工具模式
        itemModelGenerator.register(Moditems.Blade_NightFall_pickaxe,Models.HANDHELD);

        // 生成models\armor\blade_sunrise_helmet等  
        itemModelGenerator.registerArmor((ArmorItem) Moditems.Blade_SunRise_Helmet);
        itemModelGenerator.registerArmor((ArmorItem) Moditems.Blade_SunRise_ChestPlate);
        itemModelGenerator.registerArmor((ArmorItem) Moditems.Blade_SunRise_Leggings);
        itemModelGenerator.registerArmor((ArmorItem) Moditems.Blade_SunRise_Boots);
    }

}
