package com.lou.sao.registry.tag;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomeTags {
    public static final TagKey<Biome> INITIAL_TOWN_HAS_STRUCTURE = of("initial_town/has_structure");

    private ModBiomeTags() {
	}

	private static TagKey<Biome> of(String id) {
		return TagKey.of(RegistryKeys.BIOME, new Identifier(id));
	}
}
