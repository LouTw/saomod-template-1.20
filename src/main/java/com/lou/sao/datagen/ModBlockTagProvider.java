package com.lou.sao.datagen;

import java.util.concurrent.CompletableFuture;

import com.lou.sao.block.ModBlocks;
import com.lou.sao.util.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider{

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        
    }

    @Override
    protected void configure(WrapperLookup arg) {
        // 生成tags\blocks\modblock_list.json，例：生成modblock.json并加入blade_nightfall_block;diamond_ores
        getOrCreateTagBuilder(ModTags.Blocks.MODBLOCK_LIST)
        .add(ModBlocks.Blade_nightfall_block)
        .add(ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK)
        .forceAddTag(BlockTags.DIAMOND_ORES);

        // 生成tags\blocks\minable\(pickaxe/axe/hoe/shovel).json，例：生成pickaxe.json并加入blade_nightfall_block;diamond_ores
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
        .add(ModBlocks.Blade_nightfall_block)
        .add(ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK)
        .forceAddTag(BlockTags.DIAMOND_ORES);

        // 生成tags\blocks\(needs_(iron/stone/diamond)_tool).json，例：生成needs_diamond_tool.json并加入blade_nightfall_block;diamond_ores
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        .add(ModBlocks.Blade_nightfall_block)
        .forceAddTag(BlockTags.DIAMOND_ORES);

        //生成fabric文件tags，例如生成需要4级挖掘等级的blade_nightfall_block
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK,new Identifier("fabric","needs_tool_level_4")))
        .add(ModBlocks.Blade_nightfall_block);
    }
}
