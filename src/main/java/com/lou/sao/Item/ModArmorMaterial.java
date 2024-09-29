package com.lou.sao.Item;

import java.util.function.Supplier;

import com.lou.sao.SAOMod;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

// 自定义盔甲，具体参考ArmorMaterials
public enum ModArmorMaterial implements ArmorMaterial{
    
    // 注册一个新盔甲材质，参数包括材料名称，耐久度乘区（与后面定义的每个部位的基础耐久相乘），基础耐久乘区，附魔等级，
    // 声音，硬度（减伤），韧性（击退抗性），修复材料
    BLADE_SUNRISE("blade_sunrise",25,new int[]{3,8,6,3},20
    ,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,2f,0.1f
    ,() -> Ingredient.ofItems(Moditems.Blade_SunRise));

    // 此处填充内容含义是头、身、腿、鞋的基础耐久
    private static final int[] BASE_DURABILITY = {11,16,15,13}; 

    private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredientSupplier;
    

    ModArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
            SoundEvent equipSound, float toughness, float knockbackResistance,
            Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = repairIngredientSupplier;
    }

    @Override
    public int getDurability(Type type) {
        return BASE_DURABILITY[type.ordinal()*this.durabilityMultiplier];
    }

    @Override
    public int getProtection(Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return SAOMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
