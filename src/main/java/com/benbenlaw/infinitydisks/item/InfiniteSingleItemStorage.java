package com.benbenlaw.infinitydisks.item;

import com.refinedmods.refinedstorage.api.core.Action;
import com.refinedmods.refinedstorage.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage.api.resource.ResourceKey;
import com.refinedmods.refinedstorage.api.storage.Actor;
import com.refinedmods.refinedstorage.api.storage.Storage;
import com.refinedmods.refinedstorage.common.api.storage.SerializableStorage;
import com.refinedmods.refinedstorage.common.api.storage.StorageType;
import com.refinedmods.refinedstorage.common.storage.StorageTypes;
import com.refinedmods.refinedstorage.common.support.resource.ItemResource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class InfiniteSingleItemStorage implements SerializableStorage {

    private final ItemStack infinityStack;

    public InfiniteSingleItemStorage(ItemStack itemStack) {
        this.infinityStack = itemStack.copy();
    }

    @Override
    public long insert(ResourceKey resourceKey, long amount, Action action, Actor actor) {
        if (resourceKey instanceof ItemResource item && ItemStack.isSameItemSameComponents(item.toItemStack(), infinityStack)) {
            return amount;
        }
        return 0;
    }

    @Override
    public long extract(ResourceKey resourceKey, long amount, Action action, Actor actor) {
        if (resourceKey instanceof ItemResource item &&
                ItemStack.isSameItemSameComponents(item.toItemStack(), infinityStack)) {
            return amount;
        }
        return 0;
    }

    @Override
    public Collection<ResourceAmount> getAll() {
        return List.of(
                new ResourceAmount(
                        ItemResource.ofItemStack(
                                new ItemStack(
                                        Items.COPPER_INGOT
                                )
                        ),
                        Integer.MAX_VALUE
                )
        );
    }


    @Override
    public long getStored() {
        return Integer.MAX_VALUE; // always infinite
    }

    @Override
    public com.refinedmods.refinedstorage.common.api.storage.StorageType getType() {
        return StorageTypes.ITEM;
    }
}
