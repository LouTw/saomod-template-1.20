package com.lou.sao.world.biome.surface;


import com.lou.sao.world.biome.ModBiomes;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    // 定义常量，表示不同的地表方块规则
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule STONE_BRICK_BLOCK = makeStateRule(Blocks.STONE_BRICKS);
    /**
     * 创建并返回自定义的地表生成规则
     *
     * @return 自定义的地表生成规则
     */
    public static MaterialRules.MaterialRule makeRules() {
        // 定义一个条件，判断是否在或高于水位线
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        // 定义草地表面规则：在或高于水位线时使用草方块，否则使用泥土方块
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        // 定义石砖表面规则：在或高于水位线时使用石砖方块，否则使用泥土方块
        MaterialRules.MaterialRule stonebricksSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, STONE_BRICK_BLOCK), DIRT);
        // 返回最终的地表生成规则
        return MaterialRules.sequence(
                // 为特定生物群系定义自定义规则
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.INITIAL_TOWN),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)))

                // 默认规则：在石头深度地板上生成草地表面
                //MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }
    /**
     * 创建一个地表方块规则
     *
     * @param block 要使用的方块
     * @return 对应的地表方块规则
     */
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
