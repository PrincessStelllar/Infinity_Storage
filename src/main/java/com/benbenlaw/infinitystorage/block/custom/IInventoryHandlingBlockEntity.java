package com.benbenlaw.infinitystorage.block.custom;

import net.neoforged.neoforge.items.ItemStackHandler;

public interface IInventoryHandlingBlockEntity {
    void setHandler(ItemStackHandler handler);
    ItemStackHandler getItemStackHandler();
}