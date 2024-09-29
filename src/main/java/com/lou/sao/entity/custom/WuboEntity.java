package com.lou.sao.entity.custom;

import org.jetbrains.annotations.Nullable;

import com.lou.sao.entity.ModEntities;
import com.lou.sao.entity.ai.WuboAttackGoal;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class WuboEntity extends AnimalEntity{
    private static final TrackedData<Boolean> ATTACKING = 
        DataTracker.registerData(WuboEntity.class,TrackedDataHandlerRegistry.BOOLEAN);

    // 注册动画 
    public final AnimationState idlAnimationState = new AnimationState();
    private int idlAnimationTimeOut = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeOut = 0;

    // 设置动画条件
    private void SetUpAnimationState(){
        if(this.idlAnimationTimeOut <= 0){
            this.idlAnimationTimeOut = this.random.nextInt(40) + 80;
            this.idlAnimationState.start(this.age);
        }else{
            --this.idlAnimationTimeOut;
        }
        
        if(this.isAttacking() && this.attackAnimationTimeOut <= 0){
            this.attackAnimationTimeOut = 40;
            this.attackAnimationState.start(this.age);
        }else{
            --this.attackAnimationTimeOut;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
        }
    }
     
    public WuboEntity(EntityType<? extends AnimalEntity> entityType, World world){
        super(entityType,world);
    }

    @Override
    public void tick(){
        super.tick();
        if(this.getWorld().isClient()){
            SetUpAnimationState();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    // 初始化生物实体的行为优先级，数字越小，优先级越高，可以定义一些行为参数
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this,1.00));
        this.goalSelector.add(2, new TemptGoal(this,1.25D,Ingredient.ofItems(Items.DIAMOND),false));
        this.goalSelector.add(3, new FollowParentGoal(this,1.00));
        this.goalSelector.add(4, new WanderAroundFarGoal(this,1.00));
        this.goalSelector.add(5, new LookAtEntityGoal(this,PlayerEntity.class,3f));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.goalSelector.add(1, new WuboAttackGoal(this, 10, true));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    // 对生物实体添加生命值、护甲、速度等参数，记得在saomod.java中注册
    public static DefaultAttributeContainer.Builder createWuboAttributes(){
        return MobEntity.createMobAttributes()
        .add(EntityAttributes.GENERIC_MAX_HEALTH,200)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.2f)
        .add(EntityAttributes.GENERIC_ARMOR,0.5f)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,1);
    }
 
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.DIAMOND);
    }
    
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.WUBO.create(world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    @Override
    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

}
