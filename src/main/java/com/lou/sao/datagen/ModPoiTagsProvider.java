package com.lou.sao.datagen;

import java.util.concurrent.CompletableFuture;

import com.lou.sao.SAOMod;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.PointOfInterestTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPoiTagsProvider extends TagProvider<PointOfInterestType>{

    public ModPoiTagsProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookUpFuture){
        super(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registryLookUpFuture); 
    } 

    // 生成村民poi tag文件
    @Override
    protected void configure(WrapperLookup lookup) {
        getOrCreateTagBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
            .addOptional(new Identifier(SAOMod.MOD_ID,"blade_nightfall_poi"));  
    }



}
