package com.lou.sao.world.biome;

import com.lou.sao.SAOMod;
import com.lou.sao.world.biome.surface.ModMaterialRules;

import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi{
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(new Identifier(SAOMod.MOD_ID,"overworld"),4));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,SAOMod.MOD_ID, ModMaterialRules.makeRules());
    }
}
