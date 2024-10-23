package com.lou.sao.world.biome;

import com.lou.sao.SAOMod;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {

    // 定义一个生物群系的注册键，用于标识初始城镇生物群系
    public static final RegistryKey<Biome> INITIAL_TOWN = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(SAOMod.MOD_ID,"initial_town"));

    // 注册生物群系的方法        
    public static void bootstrap(Registerable<Biome> context){
        context.register(INITIAL_TOWN,initialtownBiome(context));
    }

    // 配置主世界生成设置的方法
    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        // 添加地形雕刻器（如洞穴和峡谷）
        //DefaultBiomeFeatures.addLandCarvers(builder);
        // 添加紫水晶晶洞
        //DefaultBiomeFeatures.addAmethystGeodes(builder);
        // 添加地牢
        //DefaultBiomeFeatures.addDungeons(builder);
        // 添加可开采资源（如煤矿等）
        DefaultBiomeFeatures.addMineables(builder);
        // 添加泉水
        DefaultBiomeFeatures.addSprings(builder);
        // 添加冻结顶层（如冰雪覆盖的地表）
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
        // 添加苔藓岩石
        //DefaultBiomeFeatures.addMossyRocks(builder);
        // 添加默认的矿石
        DefaultBiomeFeatures.addDefaultOres(builder);
        // 添加植被装饰（如平原树木）
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        // 添加森林花朵
        DefaultBiomeFeatures.addForestFlowers(builder);
        // 添加大型蕨类植物
        DefaultBiomeFeatures.addLargeFerns(builder);
        // 添加默认的蘑菇
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        // 添加默认的植被
        DefaultBiomeFeatures.addDefaultVegetation(builder);
    }

    // 定义初始城镇生物群系的方法
    public static Biome initialtownBiome(Registerable<Biome> context) {
        // 配置生物生成设置
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        // 添加生物生成规则（如狼和村民）
        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.WUBO, 2, 3, 5));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.VILLAGER, 5, 8, 8));

        // 添加默认的农场动物
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        // 添加默认的怪物
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        // 添加默认平原生物
        DefaultBiomeFeatures.addPlainsMobs(spawnBuilder);

        // 配置生成设置
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));


        // 调用全局主世界生成设置
        globalOverworldGeneration(biomeBuilder);     


        // 构建并返回生物群系
        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)     // 设置降水
                .temperature(0.7f)      // 设置降水量
                .generationSettings(biomeBuilder.build())       // 设置温度
                .spawnSettings(spawnBuilder.build())        // 设置生成设置
                .effects((new BiomeEffects.Builder())       // 设置生物生成设置
                        .waterColor(0x3F76E4)       // 设置水的颜色
                        .waterFogColor(0x050533)        // 设置水雾的颜色
                        .skyColor(0x77ADFF)     // 设置天空的颜色
                        .grassColor(0x91BD59)       // 设置草的颜色
                        .foliageColor(0x77AB2F)     // 设置树叶的颜色
                        .fogColor(0xC0D8FF)     // 设置雾的颜色
                        .moodSound(BiomeMoodSound.CAVE)     // 设置生物群系的环境音效
                        .music(MusicType.createIngameMusic(RegistryEntry.of(SoundEvents.MUSIC_DISC_11))).build()) // 设置背景音乐
                .build();
    }
}