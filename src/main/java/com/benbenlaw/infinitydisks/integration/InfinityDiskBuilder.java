package com.benbenlaw.infinitydisks.integration;

import com.benbenlaw.infinitydisks.item.InfinityDiskItem;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InfinityDiskBuilder extends ItemBuilder {

    private ItemStack infinityItem;

    public InfinityDiskBuilder(ResourceLocation id) {
        super(id);
    }

    @Info("Itemstack for the infinity disk")
    public InfinityDiskBuilder infinityStack(ItemStack infinityItem) {
        this.infinityItem = infinityItem;
        return this;
    }

    @Override
    public Item createObject() {
        return new InfinityDiskItem(infinityItem);
    }
}
