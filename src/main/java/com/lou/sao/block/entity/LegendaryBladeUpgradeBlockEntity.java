package com.lou.sao.block.entity;

import com.lou.sao.Item.Moditems;
import com.lou.sao.screen.LegendaryBladeUpgradeBlockScreenHandler;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LegendaryBladeUpgradeBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,ImplementedInventory{

    // 创建自定义方块实体中的格子：数量，是否为空
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2,ItemStack.EMPTY);

    // 定义GUI中的格子，一般情况是数组
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    // 用于客户端和服务端之间屏幕显示相关的同步
    protected final PropertyDelegate propertyDelegate;

    // 定义的进度条 单位tick
    private int progress = 0;
    private int maxProgress = 36;

    public LegendaryBladeUpgradeBlockEntity(BlockPos pos,BlockState state){
        super(ModBlockEntities.LEGENDARY_BLADE_UPGRADE_BLOCK_ENTITY, pos, state);
        // 这里的写法主要参考原版AbstractFurnaceBlockEntity中的写法
        this.propertyDelegate = new PropertyDelegate() {
            
            @Override
            public int get(int index){
                return switch (index){
                    case 0 -> LegendaryBladeUpgradeBlockEntity.this.progress;
                    case 1 -> LegendaryBladeUpgradeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value){
                switch (index) {
                    case 0 -> LegendaryBladeUpgradeBlockEntity.this.progress = value;
                    case 1 -> LegendaryBladeUpgradeBlockEntity.this.maxProgress = value;
                }
            }

            // 有多少个参数需要传，就return多少，例如这里指的是上面定义的进度条
            @Override
            public int size(){return 2;}
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems(){
        return inventory;
    }

    // 用于显示GUI上的名称
    @Override
    public Text getDisplayName() {
        return Text.literal("Legendary Blade Upgrade Block");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new LegendaryBladeUpgradeBlockScreenHandler(syncId,playerInventory,this,this.propertyDelegate);
    }

    // 用于客户端和服务端之间同步
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    
    // 写nbt标签，用于保存世界时，实体方块里面的数据
    @Override
    protected void writeNbt(NbtCompound nbt){
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("legendary_blade_upgrade_block",progress);
    }

    // 读nbt标签，用于加载世界时，实体方块里面的数据
    @Override
    public void readNbt(NbtCompound nbt){
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("legendary_blade_upgrade_block");
    }

    public void tick(World world1,BlockPos pos,BlockState state1){
        if(world1.isClient()){
            return;
        }

        if(isOutputSlotAvailable()){
            if(this.hasRecipe()){
                this.increaseCraftProgress();
                markDirty(world1,pos,state1);
                
                if(hasCraftingFinished()){
                    this.craftItem();
                    this.resetProgress();
                }
            }else{
                this.resetProgress();
            }
        }else{
            this.resetProgress(); 
            markDirty(world1,pos,state1);
        }
    }

    // 重置实体方块内进度
    private void resetProgress(){ this.progress = 0;}

    // 实体方块内制作配方，input_slot对应什么物品，output_slot对应什么物品，之后修改为switch-case
    private void craftItem(){
        ItemStack result = new ItemStack(Moditems.Blade_NightFall_Sword);
        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(),getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        this.removeStack(INPUT_SLOT,1);
    }
    
    // 判断是否制作完成
    private boolean hasCraftingFinished(){ return progress >= maxProgress;}

    // 自增制作进度
    private void increaseCraftProgress(){ progress++;}

    // 判断是否有配方表
    private boolean hasRecipe(){
        ItemStack result = new ItemStack(Moditems.Blade_NightFall_Sword);
        // 判断输入的物品是否为所需物品，之后修改为switch-case
        boolean hasInput = getStack(INPUT_SLOT).getItem() == Moditems.Blade_NightFall_pickaxe;
        
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    // 判断输出slot是否有堆叠空位输出
    private boolean canInsertAmountIntoOutputSlot(ItemStack result){
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    // 判断输出slot是否有空位输出或者已有相同物品
    private boolean canInsertItemIntoOutputSlot(Item item){
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    // 检查输出槽位是否满了，用于后续停止制作操作
    private boolean isOutputSlotAvailable(){
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
