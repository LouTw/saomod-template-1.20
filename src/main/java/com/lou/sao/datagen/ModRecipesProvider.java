package com.lou.sao.datagen;

import java.util.List;
import java.util.function.Consumer;

import com.lou.sao.Item.Moditems;
import com.lou.sao.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipesProvider extends FabricRecipeProvider{

    //
    private static final List<ItemConvertible> BLADE_NIGHTFALL_BLOCK_LIST = List.of(ModBlocks.Blade_nightfall_block);

    public ModRecipesProvider(FabricDataOutput output) {
        
        super(output);
        
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
    
        // 生成能够正向合成和反向分解的配方表，类似于9个铁锭合成一个铁块，一个铁块分解成9个铁锭
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModBlocks.Blade_nightfall_block, 
        RecipeCategory.COMBAT, Moditems.Blade_NightFall);

        // 生成熔炉配方
        offerSmelting(exporter, BLADE_NIGHTFALL_BLOCK_LIST, RecipeCategory.MISC, ModBlocks.Blade_nightfall_block, 
        0.7f, 200, "blade_nightfall_block");
        // 生成高炉配方
        offerBlasting(exporter, BLADE_NIGHTFALL_BLOCK_LIST, RecipeCategory.MISC, ModBlocks.Blade_nightfall_block, 
        0.7f, 100, "blade_nightfall_block");

        // 生成有图案的3×3配方
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Moditems.Blade_NightFall,3)
        .pattern(" # ")
        .pattern(" # ")
        .pattern(" # ")
        .input('#', ModBlocks.Blade_nightfall_block)
        .criterion(hasItem(ModBlocks.Blade_nightfall_block), conditionsFromItem(ModBlocks.Blade_nightfall_block))
        .offerTo(exporter,new Identifier("blade_nightfall_block_to_blade_nightfall"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEGENDARY_BLADE_UPGRADE_BLOCK,3)
        .pattern("!@#")
        .pattern("$%^")
        .input('!', Items.NETHERITE_BLOCK)
        .input('@', Items.DIAMOND_BLOCK)
        .input('#', Items.IRON_BLOCK)
        .input('$', Items.COPPER_BLOCK)
        .input('%', Items.EMERALD_BLOCK)
        .input('^', Items.REDSTONE_BLOCK)
        .criterion(hasItem(Items.NETHERITE_BLOCK), conditionsFromItem(Items.NETHERITE_BLOCK))
        .offerTo(exporter,new Identifier("legendary_blade_upgrade_block"));
    }

}
