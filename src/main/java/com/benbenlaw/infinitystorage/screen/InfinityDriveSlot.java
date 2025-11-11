package com.benbenlaw.infinitystorage.screen;

import com.benbenlaw.infinitystorage.item.InfinityDrive;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class InfinityDriveSlot extends SlotItemHandler {

    public InfinityDriveSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof InfinityDrive;
    }
}
