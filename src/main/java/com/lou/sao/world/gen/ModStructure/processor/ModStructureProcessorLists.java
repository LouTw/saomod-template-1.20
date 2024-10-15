package com.lou.sao.world.gen.ModStructure.processor;

import java.util.List;
import com.lou.sao.SAOMod;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.structure.processor.ProtectedBlocksStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;

public class ModStructureProcessorLists {
	// 定义处理列表的注册键
    public static final RegistryKey<StructureProcessorList> INITIAL_TOWN_START_DEGRADATION = registerKey("initial_town_start_degradation");
    public static final RegistryKey<StructureProcessorList> INITIAL_TOWN_GENERIC_DEGRADATION = registerKey("initial_town_generic_degradation");
    public static final RegistryKey<StructureProcessorList> INITIAL_TOWN_WALLS_DEGRADATION = registerKey("initial_town_walls_degradation");
	
	// 注册处理列表的注册键
	public static RegistryKey<StructureProcessorList> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PROCESSOR_LIST,new Identifier(SAOMod.MOD_ID,name));
    }
	
	// 注册处理列表
	private static void register(
		Registerable<StructureProcessorList> processorListRegisterable, RegistryKey<StructureProcessorList> key, List<StructureProcessor> processors
	) {
		processorListRegisterable.register(key, new StructureProcessorList(processors));
	}

	// 初始化方法，用于注册所有处理列表
	public static void bootstrap(Registerable<StructureProcessorList> processorListRegisterable) {
		// 注册初始初始城镇处理列表
		RegistryEntryLookup<Block> registryEntryLookup = processorListRegisterable.getRegistryLookup(RegistryKeys.BLOCK);
		register(
            processorListRegisterable,
			INITIAL_TOWN_START_DEGRADATION,
			ImmutableList.of(
				new RuleStructureProcessor(
					ImmutableList.of(
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState()
						),
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILES, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState()
						),
						new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_LANTERN, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())
					)
				),
				new ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
			)
		);
        register(
			processorListRegisterable,
			INITIAL_TOWN_GENERIC_DEGRADATION,
			ImmutableList.of(
				new BlockRotStructureProcessor(registryEntryLookup.getOrThrow(BlockTags.ANCIENT_CITY_REPLACEABLE), 0.95F),
				new RuleStructureProcessor(
					ImmutableList.of(
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState()
						),
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILES, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState()
						),
						new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_LANTERN, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())
					)
				),
				new ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
			)
		);
		register(
			processorListRegisterable,
			INITIAL_TOWN_WALLS_DEGRADATION,
			ImmutableList.of(
				new BlockRotStructureProcessor(registryEntryLookup.getOrThrow(BlockTags.ANCIENT_CITY_REPLACEABLE), 0.95F),
				new RuleStructureProcessor(
					ImmutableList.of(
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState()
						),
						new StructureProcessorRule(
							new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILES, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState()
						),
						new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILE_SLAB, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
						new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_LANTERN, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())
					)
				),
				new ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
			)
		);
    }
}
