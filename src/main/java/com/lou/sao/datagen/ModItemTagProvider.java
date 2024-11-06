package com.lou.sao.datagen;

import java.util.concurrent.CompletableFuture;

import com.lou.sao.Item.Moditems;
import com.lou.sao.util.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;


public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup arg) {
        // 生成tags\items\moditem_list.json，例：生成moditem_list.json并加入blade_nightfall
        getOrCreateTagBuilder(ModTags.Items.MODITEM_LIST)
        .add(Moditems.Blade_NightFall)
        .add(Moditems.Blade_SunRise)
        .add(Moditems.Hearthstone)
        .add(Moditems.PetReviveCrystal);

        // 生成盔甲类锻造台tags
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
        .add(Moditems.Blade_SunRise_Helmet
        ,Moditems.Blade_SunRise_ChestPlate
        ,Moditems.Blade_SunRise_Leggings
        ,Moditems.Blade_SunRise_Boots
        ,Moditems.NervGear);
    }

}
