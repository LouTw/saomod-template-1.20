package com.lou.sao.world.gen;

import com.lou.sao.world.ModPlacedFeatures;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld()
            , GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BLADE_NIGHTFALL_BLOCK_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether()
            , GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NETHER_BLADE_NIGHTFALL_BLOCK_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd()
            , GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_BLADE_NIGHTFALL_BLOCK_PLACED_KEY);
    }
}
