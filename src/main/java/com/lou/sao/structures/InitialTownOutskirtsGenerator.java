package com.lou.sao.structures;

import com.google.common.collect.ImmutableList;
import com.lou.sao.SAOMod;
import com.lou.sao.world.gen.ModStructure.ModStructurePools;
import com.lou.sao.world.gen.ModStructure.processor.ModStructureProcessorLists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;

public class InitialTownOutskirtsGenerator {
	
	// 定义一个常量，表示城市中心的结构池注册键
    public static final RegistryKey<StructurePool> CITY_Structures = registerKey("initial_town/structures");
	public static final RegistryKey<StructurePool> CITY_Sculk = registerKey("initial_town/sculk");
	public static final RegistryKey<StructurePool> CITY_Walls = registerKey("initial_town/walls");
	public static final RegistryKey<StructurePool> CITY_No_Corners = registerKey("initial_town/walls/no_corners");
	public static final RegistryKey<StructurePool> CITY_Center_Walls = registerKey("initial_town/city_center/walls");
	public static final RegistryKey<StructurePool> CITY_Entrance = registerKey("initial_town/city/entrance");

	// 注册处理列表的注册键
	public static RegistryKey<StructurePool> registerKey(String name){
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL,new Identifier(SAOMod.MOD_ID,name));
    }

	// 初始化方法，用于注册结构池
    public static void bootstrap(Registerable<StructurePool> poolRegisterable) {
		// 获取放置特征的注册表查找对象
		RegistryEntryLookup<PlacedFeature> registryEntryLookup = poolRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
		// 获取特定的放置特征注册条目
		RegistryEntry<PlacedFeature> registryEntry = registryEntryLookup.getOrThrow(UndergroundPlacedFeatures.SCULK_PATCH_ANCIENT_CITY);
		// 获取处理器列表的注册表查找对象
		RegistryEntryLookup<StructureProcessorList> registryEntryLookup2 = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);
		// 获取特定的处理列表注册条目
		RegistryEntry<StructureProcessorList> registryEntry2 = registryEntryLookup2.getOrThrow(ModStructureProcessorLists.INITIAL_TOWN_GENERIC_DEGRADATION);
		RegistryEntry<StructureProcessorList> registryEntry3 = registryEntryLookup2.getOrThrow(ModStructureProcessorLists.INITIAL_TOWN_WALLS_DEGRADATION);
		// 获取模板池的注册表查找对象
		RegistryEntryLookup<StructurePool> registryEntryLookup3 = poolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
		// 获取空的结构池注册条目
		RegistryEntry<StructurePool> registryEntry4 = registryEntryLookup3.getOrThrow(ModStructurePools.EMPTY);
		// 注册初始城镇外围的结构池
		poolRegisterable.register(
			CITY_Structures,
			new StructurePool(
				registryEntry4, // 父结构池
				ImmutableList.of(
					// 添加多个结构池元素，每个元素都有一个处理列表
					Pair.of(StructurePoolElement.ofEmpty(), 7),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/barracks", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/chamber_1", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/chamber_2", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/chamber_3", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/sauna_1", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/small_statue", registryEntry2), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/large_ruin_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/tall_ruin_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/tall_ruin_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/tall_ruin_3", registryEntry2), 2),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/tall_ruin_4", registryEntry2), 2),
					Pair.of(
						StructurePoolElement.ofList(
							ImmutableList.of(
								StructurePoolElement.ofProcessedSingle("initial_town/structures/camp_1", registryEntry2),
								StructurePoolElement.ofProcessedSingle("initial_town/structures/camp_2", registryEntry2),
								StructurePoolElement.ofProcessedSingle("initial_town/structures/camp_3", registryEntry2)
							)
						),
						1
					),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/medium_ruin_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/medium_ruin_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/small_ruin_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/small_ruin_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/large_pillar_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/structures/medium_pillar_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofList(ImmutableList.of(StructurePoolElement.ofSingle("initial_town/structures/ice_box_1"))), 1)
				),
				StructurePool.Projection.RIGID
			)
		);
		poolRegisterable.register(
			CITY_Sculk,
			new StructurePool(
				registryEntry4,
				ImmutableList.of(Pair.of(StructurePoolElement.ofFeature(registryEntry), 6), Pair.of(StructurePoolElement.ofEmpty(), 1)),
				StructurePool.Projection.RIGID
			)
		);
		poolRegisterable.register(
			CITY_Walls,
			new StructurePool(
				registryEntry4,
				ImmutableList.of(
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_corner_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_intersection_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_lshape_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_2", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_2", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_3", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_4", registryEntry3), 4),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_passage_1", registryEntry3), 3),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_corner_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_corner_wall_2", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_horizontal_wall_stairs_1", registryEntry3), 2),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_horizontal_wall_stairs_2", registryEntry3), 2),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_horizontal_wall_stairs_3", registryEntry3), 3),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/ruined_horizontal_wall_stairs_4", registryEntry3), 3)
				),
				StructurePool.Projection.RIGID
			)
		);
		poolRegisterable.register(
			CITY_No_Corners,
			new StructurePool(
				registryEntry4,
				ImmutableList.of(
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_2", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_1", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_2", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_3", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_4", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_stairs_5", registryEntry3), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/walls/intact_horizontal_wall_bridge", registryEntry3), 1)
				),
				StructurePool.Projection.RIGID
			)
		);
		poolRegisterable.register(
			CITY_Center_Walls,
			new StructurePool(
				registryEntry4,
				ImmutableList.of(
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/bottom_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/bottom_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/bottom_left_corner", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/bottom_right_corner_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/bottom_right_corner_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/left", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/right", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/top", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/top_right_corner", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/walls/top_left_corner", registryEntry2), 1)
				),
				StructurePool.Projection.RIGID
			)
		);
		poolRegisterable.register(
			CITY_Entrance,
			new StructurePool(
				registryEntry4,
				ImmutableList.of(
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_connector", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_path_1", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_path_2", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_path_3", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_path_4", registryEntry2), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city/entrance/entrance_path_5", registryEntry2), 1)
				),
				StructurePool.Projection.RIGID
			)
		);
	}
}
