package com.lou.sao.world.gen.ModStructure;


import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;

public interface ModStructureSets {
    static void bootstrap(Registerable<StructureSet> structureSetRegisterable) {
		RegistryEntryLookup<Structure> registryEntryLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);
		
		structureSetRegisterable.register(
			ModStructureSetKeys.INITIAL_TOWN_STRUCTURES,
			new StructureSet(registryEntryLookup.getOrThrow(ModStructureKeys.INITIAL_TOWN_STRUCTURE), new RandomSpreadStructurePlacement(24, 8, SpreadType.LINEAR, 20083232))
		);
	}
}
