package com.lou.sao.world.gen.ModStructure;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lou.sao.registry.tag.ModBiomeTags;
import com.lou.sao.structures.InitialTownGenerator;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.StructureSpawns;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;

public class ModStructures {
    private static Structure.Config createConfig(
		RegistryEntryList<Biome> biomes, Map<SpawnGroup, StructureSpawns> spawns, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation
	) {
		return new Structure.Config(biomes, spawns, featureStep, terrainAdaptation);
	}

	private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
		return createConfig(biomes, Map.of(), featureStep, terrainAdaptation);
	}

	private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, StructureTerrainAdaptation terrainAdaptation) {
		return createConfig(biomes, Map.of(), GenerationStep.Feature.SURFACE_STRUCTURES, terrainAdaptation);
	}

	public static void bootstrap(Registerable<Structure> structureRegisterable) {
		RegistryEntryLookup<Biome> registryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
        RegistryEntryLookup<StructurePool> registryEntryLookup2 = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
	
        structureRegisterable.register(
			ModStructureKeys.INITIAL_TOWN_STRUCTURE,
			new JigsawStructure(
				createConfig(
					registryEntryLookup.getOrThrow(ModBiomeTags.INITIAL_TOWN_HAS_STRUCTURE),
					(Map<SpawnGroup, StructureSpawns>)Arrays.stream(SpawnGroup.values())
						.collect(Collectors.toMap(spawnGroup -> spawnGroup, spawnGroup -> new StructureSpawns(StructureSpawns.BoundingBox.STRUCTURE, Pool.empty()))),
					GenerationStep.Feature.UNDERGROUND_DECORATION,
					StructureTerrainAdaptation.BEARD_BOX
				),
				registryEntryLookup2.getOrThrow(InitialTownGenerator.CITY_CENTER),
				Optional.of(new Identifier("city_anchor")),
				7,
				ConstantHeightProvider.create(YOffset.fixed(-27)),
				false,
				Optional.empty(),
				116
			)
		);
    }
}
