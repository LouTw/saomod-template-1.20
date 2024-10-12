package com.lou.sao.block;

import com.lou.sao.SAOMod;
import com.lou.sao.block.custom.LegendaryBladeUpgradeBlock;
import com.lou.sao.sounds.ModSounds;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //注册新方块，例：注册一个代码中变量名是"Blade_nightfall_block"的方块，主要包括两部分：
    //1.标签名：blade_nightfall_block；
    //2.方块类：STONE,后面可以附加自定义方块声音
    public static final Block Blade_nightfall_block = registerBlocks("blade_nightfall_block"
    , new Block(FabricBlockSettings.copyOf(Blocks.STONE).sounds(ModSounds.BLADE_NIGHTFALL_BLOCK_SOUND_GROUP)));

    //注册新的自定义方块实体需要绑定的方块
    public static final Block LEGENDARY_BLADE_UPGRADE_BLOCK = registerBlocks("legendary_blade_upgrade_block"
    , new LegendaryBladeUpgradeBlock(FabricBlockSettings.copyOf(Blocks.STONE)));

    private static Block registerBlocks(String name,Block block){
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SAOMod.MOD_ID,name), block);
    }

    private static Item registerBlockItems(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(SAOMod.MOD_ID,name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){

    }
}
