package com.lou.sao.world.gen.ModStructure;

import com.lou.sao.structures.InitialTownGenerator;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;

public class ModStructurePools {
    public static final RegistryKey<StructurePool> EMPTY = of("empty");

	public static RegistryKey<StructurePool> of(String id) {
		return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, new Identifier(id));
	}

	public static void register(Registerable<StructurePool> structurePoolsRegisterable, String id, StructurePool pool) {
		structurePoolsRegisterable.register(of(id), pool);
	}

	public static void bootstrap(Registerable<StructurePool> structurePoolsRegisterable) {

		InitialTownGenerator.bootstrap(structurePoolsRegisterable);
	}
}
