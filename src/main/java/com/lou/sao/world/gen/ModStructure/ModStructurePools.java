package com.lou.sao.world.gen.ModStructure;

import com.google.common.collect.ImmutableList;
import com.lou.sao.SAOMod;
import com.lou.sao.structures.InitialTownGenerator;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.util.Identifier;

public class ModStructurePools {
    public static final RegistryKey<StructurePool> EMPTY = registerKey("empty");

    // 注册处理列表的注册键
	public static RegistryKey<StructurePool> registerKey(String name){
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL,new Identifier(SAOMod.MOD_ID,name));
    }

   public static void bootstrap(Registerable<StructurePool> structurePoolsRegisterable) {
      RegistryEntryLookup<StructurePool> registryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
      RegistryEntry<StructurePool> registryEntry = registryEntryLookup.getOrThrow(EMPTY);
      structurePoolsRegisterable.register(EMPTY, new StructurePool(registryEntry, ImmutableList.of(), Projection.RIGID));

      InitialTownGenerator.bootstrap(structurePoolsRegisterable);
   }
}
