package com.lou.sao.entity;


import com.lou.sao.SAOMod;
import com.lou.sao.entity.custom.WuboEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    // 注册新的生物实体
    public static final EntityType<WuboEntity> WUBO = Registry.register(Registries.ENTITY_TYPE
    ,new Identifier(SAOMod.MOD_ID,"wubo")
    ,FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,WuboEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());

    
}
