package com.lou.sao.util;

import com.lou.sao.SAOMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> MODBLOCK_LIST = createTag("modblock_list");
        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK,new Identifier(SAOMod.MOD_ID,name));
        }
        
    }

    public static class Items{
        public static final TagKey<Item> MODITEM_LIST = createTag("moditem_list");
        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM,new Identifier(SAOMod.MOD_ID,name));
        }
    }
}
