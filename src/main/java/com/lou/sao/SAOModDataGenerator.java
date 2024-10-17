package com.lou.sao;

import com.lou.sao.datagen.ModBlockTagProvider;
import com.lou.sao.datagen.ModItemTagProvider;
import com.lou.sao.datagen.ModLootTablesProvider;
import com.lou.sao.datagen.ModModelsProvider;
import com.lou.sao.datagen.ModPoiTagsProvider;
import com.lou.sao.datagen.ModRecipesProvider;
import com.lou.sao.datagen.ModWorldGenerator;
import com.lou.sao.world.ModConfiguredFeatures;
import com.lou.sao.world.ModPlacedFeatures;
import com.lou.sao.world.biome.ModBiomes;


import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class SAOModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// 初始化
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModRecipesProvider::new);
		pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModLootTablesProvider::new);
		pack.addProvider(ModPoiTagsProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
	}
}
