package com.lou.sao.world.gen.ModStructure;

import java.util.List;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.StructureSetKeys;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;

public interface ModStructureSets {
    static void bootstrap(Registerable<StructureSet> structureSetRegisterable) {
		RegistryEntryLookup<Structure> registryEntryLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);
		RegistryEntryLookup<Biome> registryEntryLookup2 = structureSetRegisterable.getRegistryLookup(RegistryKeys.BIOME);
		RegistryEntry.Reference<StructureSet> reference = structureSetRegisterable.register(
			StructureSetKeys.VILLAGES,
			new StructureSet(
				List.of(
					StructureSet.createEntry(registryEntryLookup.getOrThrow(StructureKeys.VILLAGE_PLAINS)),
					StructureSet.createEntry(registryEntryLookup.getOrThrow(StructureKeys.VILLAGE_DESERT)),
					StructureSet.createEntry(registryEntryLookup.getOrThrow(StructureKeys.VILLAGE_SAVANNA)),
					StructureSet.createEntry(registryEntryLookup.getOrThrow(StructureKeys.VILLAGE_SNOWY)),
					StructureSet.createEntry(registryEntryLookup.getOrThrow(StructureKeys.VILLAGE_TAIGA))
				),
				new RandomSpreadStructurePlacement(34, 8, SpreadType.LINEAR, 10387312)
			)
		);
		structureSetRegisterable.register(
			ModStructureSetKeys.INITIAL_TOWN_STRUCTURES,
			new StructureSet(registryEntryLookup.getOrThrow(ModStructureKeys.INITIAL_TOWN_STRUCTURE), new RandomSpreadStructurePlacement(24, 8, SpreadType.LINEAR, 20083232))
		);
	}
}
