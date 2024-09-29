package com.lou.sao.block.custom;

import com.lou.sao.block.entity.LegendaryBladeUpgradeBlockEntity;
import com.lou.sao.block.entity.ModBlockEntities;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// 自定义方块实体，具体实现功能根据需求来定
public class LegendaryBladeUpgradeBlock extends BlockWithEntity{

    public static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 10, 16);

    public LegendaryBladeUpgradeBlock(Settings settings){
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,BlockView world,BlockPos pos,ShapeContext context){
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos,BlockState blockState){
        return new LegendaryBladeUpgradeBlockEntity(blockPos,blockState);
    }

    // 实现方块实体在被破坏时，将里面的物品释放出来。例如：箱子被破坏时，箱子里面的东西掉出来
    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state,World world,BlockPos pos,BlockState newState,boolean moved){
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof LegendaryBladeUpgradeBlockEntity){
                ItemScatterer.spawn(world, pos, (LegendaryBladeUpgradeBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // 实现在使用带有GUI的方块实体时，调用相应的GUI
    @Override
    public ActionResult onUse(BlockState state,World world,BlockPos pos,PlayerEntity player,Hand hand,BlockHitResult hit){
        if(!world.isClient){
            NamedScreenHandlerFactory screenHandlerFactory = ((LegendaryBladeUpgradeBlockEntity) world.getBlockEntity(pos));
            if(screenHandlerFactory != null){
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


    //自定义方块实体Tick，要让方块实体在每个Tick运行些操作
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world,BlockState state,BlockEntityType<T> type){
        return checkType(type, ModBlockEntities.LEGENDARY_BLADE_UPGRADE_BLOCK_ENTITY
        , (world1,pos,state1,blockEntity) -> blockEntity.tick(world1,pos,state1));
    }
    
}
