package com.lou.sao.block.entity;

import com.lou.sao.SAOMod;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    // 将自定义方块实体绑定至对应的方块上
    public static final BlockEntityType<LegendaryBladeUpgradeBlockEntity> LEGENDARY_BLADE_UPGRADE_BLOCK_ENTITY = 
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(SAOMod.MOD_ID,"legendary_blade_upgrade_block")
        , FabricBlockEntityTypeBuilder.create(LegendaryBladeUpgradeBlockEntity::new
        , ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK_ENTITY).build());

    public static void registerBlockEntities(){
        
    }
}
