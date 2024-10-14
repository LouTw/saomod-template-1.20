package com.lou.sao.Item.custom;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.lou.sao.Item.ModArmorMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArmorItem extends ArmorItem{

    // 注册一个盔甲套装效果（可以叠加多个效果），判断当穿戴整套的BLADE_SUNRISE材质时，获得：1.效果种类（此处为幸运）2.持续时间TICK（SECONDS = TICK/20）
    public static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_STATUS_EFFECT_INSTANCE_MAP =
        (new ImmutableMap.Builder<ArmorMaterial,List<StatusEffectInstance>>()
        .put(ModArmorMaterial.BLADE_SUNRISE,
        Arrays.asList(new StatusEffectInstance(StatusEffects.LUCK,1000,1,false,false,true)
        ,new StatusEffectInstance(StatusEffects.HEALTH_BOOST,1000,1,false,false,true) //如需叠加多个效果，像此处一样继续添加
        ))).build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
        
    }

    // 实时监控玩家装备栏中是否为全套有套装效果的装备，如果符合则给予套装效果状态
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()){
            if(entity instanceof PlayerEntity player && hasFullSuitOfArmor(player)){
                evaluateArmorEffects(player);
            }
        }    
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for(Map.Entry<ArmorMaterial,List<StatusEffectInstance>> entry : MATERIAL_STATUS_EFFECT_INSTANCE_MAP.entrySet()){
            ArmorMaterial armorMaterial = entry.getKey();
            List<StatusEffectInstance> statusEffectInstance = entry.getValue();

            if(hasCorrectArmorOn(armorMaterial,player)){
                for(StatusEffectInstance effectInstance:statusEffectInstance){
                    addStatusEffectForMaterial(player,armorMaterial,effectInstance);
                }
            }
        }
    }

    // 向对应盔甲材质添加套装效果
    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial armorMaterial,
            StatusEffectInstance statusEffectInstance) {
        // 判断玩家是否已经有此种效果状态
        boolean hasEffects = player.hasStatusEffect(statusEffectInstance.getEffectType());
        // 判断玩家穿了全套对应装备且无此状态效果时，添加效果
        if(hasCorrectArmorOn(armorMaterial, player) && !hasEffects){
            player.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
        }
    }

    // 检测玩家身上是否穿了有对应套装效果的装备
    private boolean hasCorrectArmorOn(ArmorMaterial armorMaterial, PlayerEntity player) {
        // 此循环为了避免游戏崩溃，比如装备了鞘翅
        for(ItemStack armorStack:player.getInventory().armor){
            if(!(armorStack.getItem() instanceof ArmorItem)){
                return false;
            }
        }    
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem chestplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && chestplate.getMaterial() == material
        && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    // 检测玩家身上是否穿了全套装备
    private boolean hasFullSuitOfArmor(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }
    
}
