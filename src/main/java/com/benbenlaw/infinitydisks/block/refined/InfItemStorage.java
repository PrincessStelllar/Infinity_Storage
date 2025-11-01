package com.benbenlaw.infinitydisks.block.refined;

import com.refinedmods.refinedstorage.api.core.Action;
import com.refinedmods.refinedstorage.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage.api.resource.ResourceKey;
import com.refinedmods.refinedstorage.api.storage.Actor;
import com.refinedmods.refinedstorage.api.storage.Storage;
import com.refinedmods.refinedstorage.common.support.resource.ItemResource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collection;
import java.util.List;

// Keep this class

// Need to make it support multiple items...
public class InfItemStorage implements Storage {

    private final Runnable updater;
    private final List<ResourceAmount> list;
    private final Item item;


    public InfItemStorage(Runnable updater, Item item) {
        this.updater = updater;
        this.list = List.of(
                new ResourceAmount(
                        ItemResource.ofItemStack(item.getDefaultInstance()), Integer.MAX_VALUE
                )
        );
        this.item = item;
    }

    @Override
    public long extract(ResourceKey resourceKey, long amount, Action action, Actor actor) {
        if (resourceKey instanceof ItemResource item && ItemStack.isSameItem(item.toItemStack(), this.item.getDefaultInstance())) {
            updater.run();
            return amount;
        }
        return 0;
    }

    @Override
    public Collection<ResourceAmount> getAll() {
        return list;
    }


    @Override
    public long getStored() {
        return Integer.MAX_VALUE; // always infinite
    }

    @Override
    public long insert(ResourceKey resource, long amount, Action action, Actor actor) {
        return 0;
    }
}
