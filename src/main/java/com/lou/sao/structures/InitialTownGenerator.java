package com.lou.sao.structures;





import com.google.common.collect.ImmutableList;
import com.lou.sao.SAOMod;
import com.lou.sao.world.gen.ModStructure.ModStructurePools;
import com.lou.sao.world.gen.ModStructure.processor.ModStructureProcessorLists;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import com.mojang.datafixers.util.Pair;



public class InitialTownGenerator {
	// 定义一个常量，表示城市中心的结构池注册键
    public static final RegistryKey<StructurePool> CITY_CENTER = registerKey("initial_town/city_center");

	// 注册处理列表的注册键
	public static RegistryKey<StructurePool> registerKey(String name){
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL,new Identifier(SAOMod.MOD_ID,name));
    }

	// 初始化方法，用于注册结构池
	public static void bootstrap(Registerable<StructurePool> poolRegisterable) {
		// 获取处理列表的注册表查找对象
		RegistryEntryLookup<StructureProcessorList> registryEntryLookup = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);
		// 获取特定的处理列表注册条目
		RegistryEntry<StructureProcessorList> registryEntry = registryEntryLookup.getOrThrow(ModStructureProcessorLists.INITIAL_TOWN_START_DEGRADATION);
		// 获取模板池的注册表查找对象
		RegistryEntryLookup<StructurePool> registryEntryLookup2 = poolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
		// 获取空的结构池注册条目
		RegistryEntry<StructurePool> registryEntry2 = registryEntryLookup2.getOrThrow(ModStructurePools.EMPTY);
		// 注册城市中心的结构池
		poolRegisterable.register(
			CITY_CENTER,
			new StructurePool(
				registryEntry2, // 父结构池
				ImmutableList.of(
					// 添加多个结构池元素，每个元素都有一个处理列表
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/city_center_1", registryEntry), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/city_center_2", registryEntry), 1),
					Pair.of(StructurePoolElement.ofProcessedSingle("initial_town/city_center/city_center_3", registryEntry), 1)
				),
				StructurePool.Projection.RIGID // 设置结构池的投影类型为刚性
			)
		);
		InitialTownOutskirtsGenerator.bootstrap(poolRegisterable);
	}
}


