package com.lou.sao.world.gen.ModStructure;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lou.sao.SAOMod;
import com.lou.sao.structures.InitialTownGenerator;
import com.lou.sao.world.biome.ModBiomes;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
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
	public static final RegistryKey<Structure> INITIAL_TOWN_STRUCTURE = registerKey("initial_town_structure");

	// 注册处理列表的注册键
	public static RegistryKey<Structure> registerKey(String name){
        return RegistryKey.of(RegistryKeys.STRUCTURE,new Identifier(SAOMod.MOD_ID,name));
    }

	// 创建结构配置的方法，包含生物群系、生成步骤和地形适应性
    private static Structure.Config createConfig(
		RegistryEntryList<Biome> biomes, Map<SpawnGroup, StructureSpawns> spawns, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation
	) {
		return new Structure.Config(biomes, spawns, featureStep, terrainAdaptation);
	}
	// 创建结构配置的方法，包含生物群系、生成步骤和地形适应性，不包含生成生物
	private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
		return createConfig(biomes, Map.of(), featureStep, terrainAdaptation);
	}
	// 创建结构配置的方法，包含生物群系和地形适应性，默认生成步骤为地表结构
	private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, StructureTerrainAdaptation terrainAdaptation) {
		return createConfig(biomes, Map.of(), GenerationStep.Feature.SURFACE_STRUCTURES, terrainAdaptation);
	}

	// 初始化方法，用于注册结构
	public static void bootstrap(Registerable<Structure> structureRegisterable) {
		// 获取生物群系的注册表查找对象
		RegistryEntryLookup<Biome> registryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
		// 获取模板池的注册表查找对象
        RegistryEntryLookup<StructurePool> registryEntryLookup2 = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
		// 注册初始城镇结构
        structureRegisterable.register(
			INITIAL_TOWN_STRUCTURE,
			new JigsawStructure(
				createConfig(
					RegistryEntryList.of(registryEntryLookup.getOrThrow(ModBiomes.INITIAL_TOWN)), // 使用INITIAL_TOWN生物群系
					(Map<SpawnGroup, StructureSpawns>)Arrays.stream(SpawnGroup.values())
						.collect(Collectors.toMap(spawnGroup -> spawnGroup, spawnGroup -> new StructureSpawns(StructureSpawns.BoundingBox.STRUCTURE, Pool.empty()))),
					GenerationStep.Feature.SURFACE_STRUCTURES, // 生成步骤为地表结构
					StructureTerrainAdaptation.BEARD_BOX 
					/*NONE:不进行任何地形适应。结构将直接生成在指定位置，不考虑地形高度或形状。
					  BEARD_THIN:进行轻微的地形适应。结构会稍微调整以适应地形，但不会进行大规模的地形修改。这种适应方式通常用于小型结构。
                      BEARD_BOX:进行中等程度的地形适应。结构会进行更多的调整以适应地形，包括填充和挖掘地形。这种适应方式通常用于中型结构。
                      BURY:结构会被部分埋入地下。适用于需要部分隐藏在地形中的结构。 */
				),
				registryEntryLookup2.getOrThrow(InitialTownGenerator.CITY_CENTER), // 使用初始城镇中心的模板池
				Optional.of(new Identifier("city_center")),
				7,
				ConstantHeightProvider.create(YOffset.aboveBottom(0)), // 结构高度为地面上0格
				false,
				Optional.empty(),
				116
			)
		);
    }
}
