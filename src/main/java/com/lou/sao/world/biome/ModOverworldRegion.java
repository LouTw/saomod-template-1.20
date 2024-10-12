package com.lou.sao.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

// 定义一个类 ModOverworldRegion，继承自 TerraBlender 的 Region 类
public class ModOverworldRegion extends Region {

    
    /**
     * 构造方法，用于初始化 ModOverworldRegion 对象
     *
     * @param name   区域的标识符
     * @param weight 区域的权重
     */
    public ModOverworldRegion(Identifier name, int weight) {
        // 调用父类构造方法，设置区域类型为 OVERWORLD（主世界）和权重
        super(name, RegionType.OVERWORLD, weight);
    }

    /**
     * 添加生物群系的方法
     *
     * @param registry 生物群系的注册表
     * @param mapper   用于映射噪声超立方体和生物群系注册键的消费者
     */
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        // 调用父类方法，添加修改后的原版主世界生物群系
        this.addModifiedVanillaOverworldBiomes(mapper,modifiedVanillaOverworldBuilder -> {
            // 将原版的森林生物群系替换为自定义的初始城镇生物群系
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.FOREST,ModBiomes.INITIAL_TOWN);
        });
    }

}