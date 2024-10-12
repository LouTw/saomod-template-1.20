package com.lou.sao.world;

import java.util.List;

import com.lou.sao.SAOMod;
import com.lou.sao.block.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> BLADE_NIGHTFALL_BLOCK_KEY = registerKey("blade_nightfall_block");

    public static final RegistryKey<ConfiguredFeature<?,?>> NETHER_BLADE_NIGHTFALL_BLOCK_KEY = registerKey("nether_blade_nightfall_block");

    public static final RegistryKey<ConfiguredFeature<?,?>> END_BLADE_NIGHTFALL_BLOCK_KEY = registerKey("end_blade_nightfall_block");

    public static void boostrap(Registerable<ConfiguredFeature<?,?>> context){
        RuleTest stonePlace = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES); // 浅层生成
        RuleTest deepslatePlace = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES); // 深层生成
        RuleTest netherPlace = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER); // 地狱生成
        RuleTest endPlace = new BlockMatchRuleTest(Blocks.END_STONE); // 末地生成

        List<OreFeatureConfig.Target> overWorld = 
            List.of(OreFeatureConfig.createTarget(stonePlace, ModBlocks.Blade_nightfall_block.getDefaultState())
            ,OreFeatureConfig.createTarget(deepslatePlace, ModBlocks.Blade_nightfall_block.getDefaultState()));

        List<OreFeatureConfig.Target> nether = 
            List.of(OreFeatureConfig.createTarget(netherPlace, ModBlocks.Blade_nightfall_block.getDefaultState()));  

        List<OreFeatureConfig.Target> end = 
            List.of(OreFeatureConfig.createTarget(endPlace, ModBlocks.Blade_nightfall_block.getDefaultState()));   

        // 定义一个矿脉中最多有多少个矿石
        register(context, BLADE_NIGHTFALL_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(overWorld, 1));
        register(context, NETHER_BLADE_NIGHTFALL_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(nether, 1));
        register(context, END_BLADE_NIGHTFALL_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(end, 1));
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,new Identifier(SAOMod.MOD_ID,name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>>context
    , RegistryKey<ConfiguredFeature<?,?>>key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature,configuration));
    }
}
