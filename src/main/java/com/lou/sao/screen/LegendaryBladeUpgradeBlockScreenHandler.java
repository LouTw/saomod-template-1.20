package com.lou.sao.screen;

import com.lou.sao.block.entity.LegendaryBladeUpgradeBlockEntity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class LegendaryBladeUpgradeBlockScreenHandler extends ScreenHandler{
    
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public final LegendaryBladeUpgradeBlockEntity blockEntity;

    public LegendaryBladeUpgradeBlockScreenHandler(int syncId,PlayerInventory inventory,PacketByteBuf buf){
        this(syncId,inventory,inventory.player.getWorld().getBlockEntity(buf.readBlockPos())
        ,new ArrayPropertyDelegate(2));
    }

    public LegendaryBladeUpgradeBlockScreenHandler(int syncId,PlayerInventory playerInventory,BlockEntity blockEntity,PropertyDelegate arrayPropertyDelegate){
        super(ModScreenHandler.LEGENDARY_BLADE_UPGRADE_BLOCK_SCREEN_HANDLER,syncId);
        checkSize((Inventory) blockEntity, 2);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = (LegendaryBladeUpgradeBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 80, 11));
        this.addSlot(new Slot(inventory, 1, 80, 59));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }   
    
    
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()){
            ItemStack originalStack = slot2.getStack();
            newStack = originalStack.copy();
            if(slot < this.inventory.size()){
                if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }else if(!this.insertItem(originalStack, 0, this.inventory.size(), false)){
                return ItemStack.EMPTY;
            }

            if(originalStack.isEmpty()){
                slot2.setStack(ItemStack.EMPTY);
            }else{
                slot2.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory){
        for(int i=0; i<9; i++){
            this.addSlot(new Slot(playerInventory, i ,8 + i * 18, 142));
        }
    }

    public boolean isCrafting(){
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress(){
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress !=0 ? progressArrowSize / maxProgress : 0;
    }

}
