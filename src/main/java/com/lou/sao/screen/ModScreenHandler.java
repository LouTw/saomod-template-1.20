package com.lou.sao.screen;



import com.lou.sao.SAOMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandler {
    public static final ScreenHandlerType<LegendaryBladeUpgradeBlockScreenHandler> LEGENDARY_BLADE_UPGRADE_BLOCK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER,new Identifier(SAOMod.MOD_ID,"legendary_blade_upgrade_block")
            , new ExtendedScreenHandlerType<>(LegendaryBladeUpgradeBlockScreenHandler::new));

    public static void registerScreenHandler(){

    }
}
