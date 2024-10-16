package com.lou.sao.world.gen.ModStructure;



import com.lou.sao.SAOMod;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;

public interface ModStructureSets {

	public static final RegistryKey<StructureSet> INITIAL_TOWN_STRUCTURES = registerKey("initial_town_structures");

	// 注册处理列表的注册键
	public static RegistryKey<StructureSet> registerKey(String name){
        return RegistryKey.of(RegistryKeys.STRUCTURE_SET,new Identifier(SAOMod.MOD_ID,name));
    }

    static void bootstrap(Registerable<StructureSet> structureSetRegisterable) {
		RegistryEntryLookup<Structure> registryEntryLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);
		
		structureSetRegisterable.register(
			INITIAL_TOWN_STRUCTURES,
			new StructureSet(registryEntryLookup.getOrThrow(ModStructures.INITIAL_TOWN_STRUCTURE), new RandomSpreadStructurePlacement(24, 8, SpreadType.LINEAR, 20083232))
		);
	}
}
