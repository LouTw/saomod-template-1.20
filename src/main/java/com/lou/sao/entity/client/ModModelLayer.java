package com.lou.sao.entity.client;

import com.lou.sao.SAOMod;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayer {
    public static final EntityModelLayer WUBO = 
        new EntityModelLayer(new Identifier(SAOMod.MOD_ID,"wubo"), "main");
}
