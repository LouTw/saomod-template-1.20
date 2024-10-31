package com.lou.sao.Item;

import com.lou.sao.SAOMod;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModitemGroup {
    // 注册一个新的物品栏标签，例如原版中的：食品、工具、红石、装修等物品栏标签
    // 输入参数包括：1.物品栏名称 2.物品栏图标 3.物品栏中包括的物品（同时需要在lang\zh_cn;en_us.json中加入标签对应的显示名称）
    public static final ItemGroup SAO_Group = Registry.register(Registries.ITEM_GROUP,
    new Identifier(SAOMod.MOD_ID,"sao_group"),FabricItemGroup.builder().displayName(Text.translatable("itemGroup.sao_group"))
    // 此处icon只是物品栏的图标
    .icon(()->new ItemStack(Moditems.Blade_NightFall)).entries((displayContext, entries)->{
        // 添加物品
        entries.add(Moditems.Blade_NightFall); 
        entries.add(Moditems.Blade_SunRise); 
        entries.add(Moditems.Hearthstone);
        entries.add(Moditems.PetReviveCrystal);
        // 添加方块
        entries.add(ModBlocks.Blade_nightfall_block);
        // 添加工具
        entries.add(Moditems.Blade_NightFall_pickaxe);
        // 添加武器
        entries.add(Moditems.Blade_NightFall_Sword);
        entries.add(Moditems.Blade_Sunrise_Sword);
        // 添加盔甲
        entries.add(Moditems.Blade_SunRise_Helmet);
        entries.add(Moditems.Blade_SunRise_ChestPlate);
        entries.add(Moditems.Blade_SunRise_Leggings);
        entries.add(Moditems.Blade_SunRise_Boots);
        // 添加自定义方块
        entries.add(ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK);

    }).build());

    public static void registerModitemGroup(){

    }
}
