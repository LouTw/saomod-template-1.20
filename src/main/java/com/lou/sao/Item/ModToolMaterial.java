package com.lou.sao.Item;

import com.google.common.base.Supplier;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

// 自定义工具，具体参考ToolMaterials
public enum ModToolMaterial implements ToolMaterial{
    // 注册一个新工具材质，参数包括挖掘等级、耐久度、挖掘速度、攻击伤害、附魔等级、修复材料
    BLADE_NIGHTFALL(5,1600,3.5f,10.0f,25,()->Ingredient.ofItems(Moditems.Blade_NightFall)),
    BLADE_SUNRISE(5,1600,3.5f,10.0f,25,()->Ingredient.ofItems(Moditems.Blade_SunRise));

    private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient){
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }
    

    @Override
    public int getDurability() {
        return  this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
