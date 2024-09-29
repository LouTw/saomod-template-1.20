package com.lou.sao.sounds;


import com.lou.sao.SAOMod;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    // 自定义物品声音
    public static final SoundEvent BLADE_NIGHTFALL_SOUND_EVENT = registerSoundEvents("blade_nightfall_sound_event");
    // 自定义方块声音，包括破坏，击打，行走，放置，摔落
    public static final SoundEvent BLADE_NIGHTFALL_BLOCK_BREAK = registerSoundEvents("blade_nightfall_block_break");
    public static final SoundEvent BLADE_NIGHTFALL_BLOCK_STEP = registerSoundEvents("blade_nightfall_block_step");
    public static final SoundEvent BLADE_NIGHTFALL_BLOCK_HIT = registerSoundEvents("blade_nightfall_block_hit");
    public static final SoundEvent BLADE_NIGHTFALL_BLOCK_PLACE = registerSoundEvents("blade_nightfall_block_place");
    public static final SoundEvent BLADE_NIGHTFALL_BLOCK_FALL = registerSoundEvents("blade_nightfall_block_fall");

    public static final BlockSoundGroup BLADE_NIGHTFALL_BLOCK_SOUND_GROUP = new BlockSoundGroup(1f, 1f
    , BLADE_NIGHTFALL_BLOCK_BREAK, BLADE_NIGHTFALL_BLOCK_STEP, BLADE_NIGHTFALL_BLOCK_PLACE, BLADE_NIGHTFALL_BLOCK_HIT, BLADE_NIGHTFALL_BLOCK_FALL);

    public static SoundEvent registerSoundEvents(String name){
        Identifier identifier = new Identifier(SAOMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT,identifier,SoundEvent.of(identifier));
    }

    public static void registerSounds(){

    }
}
