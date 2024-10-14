package com.lou.sao.world.gen.ModStructure;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public interface ModStructureKeys {
    RegistryKey<Structure> INITIAL_TOWN_STRUCTURE = of("initial_town_structure");

	private static RegistryKey<Structure> of(String id) {
		return RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(id));
	}
}
