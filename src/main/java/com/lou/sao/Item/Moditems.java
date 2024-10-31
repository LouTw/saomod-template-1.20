package com.lou.sao.Item;

import com.lou.sao.SAOMod;
import com.lou.sao.Item.custom.ModArmorItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Moditems {

    // 注册一个新物品，名称为blade_nightfall
    public static final Item Blade_NightFall = registerItems("blade_nightfall",new Item(new FabricItemSettings()));
    public static final Item Blade_SunRise = registerItems("blade_sunrise",new Item(new FabricItemSettings()));

    public static final Item Hearthstone = registerItems("hearthstone",new Item(new FabricItemSettings()));
    public static final Item PetReviveCrystal = registerItems("pet_revive_crystal",new Item(new FabricItemSettings()));
    
    // 注册一个新工具（稿子），名称是Blade_NightFall_pickaxe
    public static final Item Blade_NightFall_pickaxe = registerItems("blade_nightfall_pickaxe"
    ,new PickaxeItem(ModToolMaterial.BLADE_NIGHTFALL,2,1f,new FabricItemSettings()));
    // 注册一个新剑，名称是Blade_NightFall_Sword
    public static final Item Blade_NightFall_Sword = registerItems("blade_nightfall_sword"
    ,new SwordItem(ModToolMaterial.BLADE_NIGHTFALL, 20, 3f, new FabricItemSettings()));
    public static final Item Blade_Sunrise_Sword = registerItems("blade_sunrise_sword"
    ,new SwordItem(ModToolMaterial.BLADE_SUNRISE, 20, 3f, new FabricItemSettings()));

    // 注册一个新防具（头盔），名称是Blade_SunRise_Helmet
    public static final Item Blade_SunRise_Helmet = registerItems("blade_sunrise_helmet"
    ,new ModArmorItem(ModArmorMaterial.BLADE_SUNRISE,ArmorItem.Type.HELMET,new FabricItemSettings()));
    // 注册一个新防具（胸甲），名称是Blade_SunRise_ChestPlate
    public static final Item Blade_SunRise_ChestPlate = registerItems("blade_sunrise_chestplate"
    ,new ModArmorItem(ModArmorMaterial.BLADE_SUNRISE,ArmorItem.Type.CHESTPLATE,new FabricItemSettings()));
    // 注册一个新防具（腿甲），名称是Blade_SunRise_Leggings
    public static final Item Blade_SunRise_Leggings = registerItems("blade_sunrise_leggings"
    ,new ModArmorItem(ModArmorMaterial.BLADE_SUNRISE,ArmorItem.Type.LEGGINGS,new FabricItemSettings()));
    // 注册一个新防具（鞋子），名称是Blade_SunRise_Boots
    public static final Item Blade_SunRise_Boots = registerItems("blade_sunrise_boots"
    ,new ModArmorItem(ModArmorMaterial.BLADE_SUNRISE,ArmorItem.Type.BOOTS,new FabricItemSettings()));


    // 将新物品加入itemgroup
    private static void addItemsToItemGroup(FabricItemGroupEntries fabriciItemGroupEntries){
        fabriciItemGroupEntries.add(Blade_NightFall);
        fabriciItemGroupEntries.add(Blade_SunRise);
    }

    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(SAOMod.MOD_ID,name),item);
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(Moditems::addItemsToItemGroup);
    }
}
