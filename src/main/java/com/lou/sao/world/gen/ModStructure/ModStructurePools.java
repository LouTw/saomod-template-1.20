package com.lou.sao.world.gen.ModStructure;

import com.google.common.collect.ImmutableList;
import com.lou.sao.structures.InitialTownGenerator;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
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
		RegistryEntryLookup<StructurePool> registryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
		RegistryEntry<StructurePool> registryEntry = registryEntryLookup.getOrThrow(EMPTY);
		structurePoolsRegisterable.register(EMPTY, new StructurePool(registryEntry, ImmutableList.of(), StructurePool.Projection.RIGID));

		InitialTownGenerator.bootstrap(structurePoolsRegisterable);
	}
}
