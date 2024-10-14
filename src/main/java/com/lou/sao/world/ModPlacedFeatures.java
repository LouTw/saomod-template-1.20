package com.lou.sao.world;

import java.util.List;

import com.lou.sao.SAOMod;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> BLADE_NIGHTFALL_BLOCK_PLACED_KEY = registerKey("blade_nightfall_block");

    public static final RegistryKey<PlacedFeature> NETHER_BLADE_NIGHTFALL_BLOCK_PLACED_KEY = registerKey("nether_blade_nightfall_block");

    public static final RegistryKey<PlacedFeature> END_BLADE_NIGHTFALL_BLOCK_PLACED_KEY = registerKey("end_blade_nightfall_block");

    public static void bootstrap(Registerable<PlacedFeature> context){
        var ConfiguredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, BLADE_NIGHTFALL_BLOCK_PLACED_KEY
        , ConfiguredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLADE_NIGHTFALL_BLOCK_KEY)
        // 第一次参数“10”表示一个chunk中有多少个矿脉，具体一个矿脉中有多少矿石由ModConfiguredFeatures中定义；
        // 第二个参数表示矿脉在高度-50~80范围内以uniform的方式分布，当然也有其他分布方式
        , ModOrePlacement.modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(80))));

        register(context, NETHER_BLADE_NIGHTFALL_BLOCK_PLACED_KEY
        , ConfiguredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_BLADE_NIGHTFALL_BLOCK_KEY)
        , ModOrePlacement.modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(80))));

        register(context, END_BLADE_NIGHTFALL_BLOCK_PLACED_KEY
        , ConfiguredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.END_BLADE_NIGHTFALL_BLOCK_KEY)
        , ModOrePlacement.modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(80))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE,new Identifier(SAOMod.MOD_ID,name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key
    , RegistryEntry<ConfiguredFeature<?,?>> configuration, List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
