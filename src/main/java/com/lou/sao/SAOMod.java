package com.lou.sao;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lou.sao.Item.ModitemGroup;
import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;
import com.lou.sao.block.entity.ModBlockEntities;
import com.lou.sao.entity.ModEntities;
import com.lou.sao.entity.custom.WuboEntity;
import com.lou.sao.screen.ModScreenHandler;
import com.lou.sao.sounds.ModSounds;
import com.lou.sao.util.ModLootTableModifier;
import com.lou.sao.util.ModTrades;
import com.lou.sao.villager.ModVillagers;
import com.lou.sao.world.gen.ModWorldGeneration;


public class SAOMod implements ModInitializer {
	public static final String MOD_ID = "saomod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		//初始化
		Moditems.registerModItems();
		ModitemGroup.registerModitemGroup();
		ModBlocks.registerModBlocks();
		ModLootTableModifier.modifierLootTables();
		ModTrades.registerTrades();
		ModVillagers.registerVillagers();
		ModSounds.registerSounds();
		ModScreenHandler.registerScreenHandler();
		ModBlockEntities.registerBlockEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.WUBO, WuboEntity.createWuboAttributes());

		ModWorldGeneration.generateModWorldGen();	
	}
}