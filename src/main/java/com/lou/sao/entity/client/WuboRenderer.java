package com.lou.sao.entity.client;

import com.lou.sao.SAOMod;
import com.lou.sao.entity.custom.WuboEntity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WuboRenderer extends MobEntityRenderer<WuboEntity,WuboModel<WuboEntity>>{

    private static final Identifier TEXTURE = new Identifier(SAOMod.MOD_ID,"textures/entity/wubo.png");

    public WuboRenderer(EntityRendererFactory.Context context){
        super(context, new WuboModel<>(context.getPart(ModModelLayer.WUBO)),0.5f);
    }


    
    @Override
    public Identifier getTexture(WuboEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(WuboEntity mobEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {
        // 设置自定义生物实体幼体大小
        if(mobEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1.0f, 1.0f, 1.0f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }


    

}
