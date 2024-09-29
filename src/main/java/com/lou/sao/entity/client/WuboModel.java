package com.lou.sao.entity.client;

import com.lou.sao.entity.animation.WuboAnimation;
import com.lou.sao.entity.custom.WuboEntity;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class WuboModel<T extends WuboEntity> extends SinglePartEntityModel<T> {
	private final ModelPart wubo;
	private final ModelPart head;

	public WuboModel(ModelPart root) {
		this.wubo = root.getChild("wubo");
		this.head = wubo.getChild("head");
	}
    
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData wubo = modelPartData.addChild("wubo", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_back_leg = wubo.addChild("right_back_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -4.0F, 4.0F));

		ModelPartData tail = wubo.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -7.2914F, 4.9639F));

		ModelPartData cube_r1 = tail.addChild("cube_r1", ModelPartBuilder.create().uv(8, 0).cuboid(-0.5F, -4.0F, -3.9F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.2343F, 3.9312F, -0.7418F, 0.0F, 0.0F));

		ModelPartData left_front_leg = wubo.addChild("left_front_leg", ModelPartBuilder.create().uv(22, 21).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -4.0F, -4.0F));

		ModelPartData body = wubo.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.5F, -6.0F, 6.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.5F, 0.0F));

		ModelPartData left_back_leg = wubo.addChild("left_back_leg", ModelPartBuilder.create().uv(16, 17).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -4.0F, 4.0F));

		ModelPartData right_front_leg = wubo.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -4.0F, -4.0F));

		ModelPartData head = wubo.addChild("head", ModelPartBuilder.create().uv(0, 17).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.0F, -7.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(WuboEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw,headPitch);

		this.animateMovement(WuboAnimation.WALK,limbSwing,limbSwingAmount,2f,2.5f);
		this.updateAnimation(entity.idlAnimationState,WuboAnimation.IDLE,ageInTicks,1f);
	}

	private void setHeadAngles(float headAngles, float headPitch) {
		headAngles = MathHelper.clamp(headAngles,-30.0F,30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);
		this.head.yaw = headAngles * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		wubo.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.wubo;
	}
}
