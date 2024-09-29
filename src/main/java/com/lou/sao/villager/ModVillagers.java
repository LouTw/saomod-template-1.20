package com.lou.sao.villager;

import com.google.common.collect.ImmutableSet;
import com.lou.sao.SAOMod;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    // 注册自定义村民键词
    public static final RegistryKey<PointOfInterestType> BLADE_NIGHTFALL_POI_KEY = point("blade_nightfall_poi");
    // 注册自定义村民兴趣点（方块）
    public static final PointOfInterestType BLADE_NIGHTFALL_POI = registerPointOfInterestType("blade_nightfall_poi", ModBlocks.Blade_nightfall_block);
    // 注册自定义村民职业
    public static final VillagerProfession BLADE_NIGHTFALL_MASTER = registerVillagerProfession("blade_nightfall_master", BLADE_NIGHTFALL_POI_KEY);

    private static VillagerProfession registerVillagerProfession(String name,RegistryKey<PointOfInterestType> type){
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(SAOMod.MOD_ID,name)
        , new VillagerProfession(name , entry -> entry.matchesKey(type) 
        ,entry -> entry.matchesKey(type), ImmutableSet.of(),ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_YES));
    }
    
    private static PointOfInterestType registerPointOfInterestType(String name,Block block){
        return PointOfInterestHelper.register(new Identifier(SAOMod.MOD_ID,name),1,1,block);
    }

    private static RegistryKey<PointOfInterestType> point(String name){
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE,new Identifier(SAOMod.MOD_ID,name));
    }

    public static void registerVillagers(){

    }
}
