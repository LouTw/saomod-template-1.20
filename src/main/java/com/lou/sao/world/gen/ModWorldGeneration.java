package com.lou.sao.world.gen;



import com.lou.sao.SAOMod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;


public class ModWorldGeneration {
    public static final RegistryKey<ChunkGeneratorSettings> CUSTOM_CHUNK_GENERATOR_SETTINGS = RegistryKey.of(
            RegistryKeys.CHUNK_GENERATOR_SETTINGS,
            new Identifier(SAOMod.MOD_ID, "custom_chunk_generator_settings")
    );

    public static void registerChunkGenerators() {
        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(SAOMod.MOD_ID, "custom_chunk_generator"), CustomChunkGenerator.CODEC);
    }

    public static void generateModWorldGen(){
        ModOreGeneration.generateOres();
        
        // 设置主世界边界
        ServerWorldEvents.LOAD.register((server,world) -> {
            if(world.getRegistryKey() == ServerWorld.OVERWORLD){
                WorldBorder border = world.getWorldBorder();
                border.setCenter(0, 0); // 设置边界中心为 (0, 0)
                border.setSize(600000); // 设置边界大小为 30 万格半径
                border.setDamagePerBlock(1000.0); // 确保玩家越过边界立即死亡

                // 设置自定义区块生成器
                BiomeSource biomeSource = world.getChunkManager().getChunkGenerator().getBiomeSource();
                CustomChunkGenerator customChunkGenerator = new CustomChunkGenerator(
                        biomeSource,
                        world.getSeed(),
                        CUSTOM_CHUNK_GENERATOR_SETTINGS
                );

                world.getChunkManager().setChunkGenerator(customChunkGenerator);

            }
        });

    }

}
