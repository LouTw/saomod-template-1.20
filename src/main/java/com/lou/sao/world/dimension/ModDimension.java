package com.lou.sao.world.dimension;

import java.util.OptionalLong;

import com.lou.sao.SAOMod;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class ModDimension {

    public static final RegistryKey<DimensionOptions> SAO_WORLD = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(SAOMod.MOD_ID, "sao_world"));
    public static final RegistryKey<World> SAO_WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(SAOMod.MOD_ID, "sao_world"));
    public static final RegistryKey<DimensionType> SAO_WORLD_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(SAOMod.MOD_ID, "sao_world_type"));
            
    public static void bootstrap(Registerable<DimensionType> context) {
        context.register(SAO_WORLD_TYPE_KEY, new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                true,
                1.0,
                true,
                false,
                0,256,256,
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.OVERWORLD_ID,
                1.0f,
                new DimensionType.MonsterSettings(false,false, UniformIntProvider.create(0,0),0)));
    }
}
