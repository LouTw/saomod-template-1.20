package com.lou.sao;


import com.lou.sao.entity.ModEntities;
import com.lou.sao.entity.client.ModModelLayer;
import com.lou.sao.entity.client.WuboModel;
import com.lou.sao.entity.client.WuboRenderer;
import com.lou.sao.screen.LegendaryBladeUpgradeBlockScreen;
import com.lou.sao.screen.ModScreenHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class SAOModClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        
        HandledScreens.register(ModScreenHandler.LEGENDARY_BLADE_UPGRADE_BLOCK_SCREEN_HANDLER, LegendaryBladeUpgradeBlockScreen::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.WUBO, WuboModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WUBO, WuboRenderer::new);
    }
}
