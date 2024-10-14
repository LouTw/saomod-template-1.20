package com.lou.sao.world.gen.ModStructure;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;

public interface ModStructureSetKeys {
    RegistryKey<StructureSet> INITIAL_TOWN_STRUCTURES = of("initial_town_structures");

	private static RegistryKey<StructureSet> of(String id) {
		return RegistryKey.of(RegistryKeys.STRUCTURE_SET, new Identifier(id));
	}
}
