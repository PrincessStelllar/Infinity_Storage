package com.benbenlaw.infinitydisks.item;

import com.refinedmods.refinedstorage.api.core.Action;
import com.refinedmods.refinedstorage.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage.api.resource.ResourceKey;
import com.refinedmods.refinedstorage.api.storage.Actor;
import com.refinedmods.refinedstorage.common.storage.ItemStorageVariant;
import com.refinedmods.refinedstorage.common.storage.StorageTypes;
import com.refinedmods.refinedstorage.common.storage.StorageVariant;
import com.refinedmods.refinedstorage.common.storage.storagedisk.ItemStorageDiskItem;
import com.refinedmods.refinedstorage.common.api.storage.SerializableStorage;
import com.refinedmods.refinedstorage.common.api.storage.StorageRepository;
import com.refinedmods.refinedstorage.common.support.resource.ItemResource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class InfinityDiskItem extends ItemStorageDiskItem {

    private final ItemStack infinityStack;
    private final Component helpText;

    public InfinityDiskItem(ItemStack itemStack) {
        super(ItemStorageVariant.CREATIVE);
        this.infinityStack = itemStack.copy();
        this.helpText = Component.literal("UNLIMITED STORAGE");
    }

    @Override
    public StorageVariant getVariant() {
        return ItemStorageVariant.CREATIVE;
    }

    @Override
    protected SerializableStorage createStorage(StorageRepository storageRepository) {
        // Official creative storage disk backend
        SerializableStorage base = StorageTypes.ITEM.create(null, storageRepository::markAsChanged);

        return new SerializableStorage() {
            @Override
            public long insert(ResourceKey resourceKey, long amount, Action action, Actor actor) {
                if (resourceKey instanceof ItemResource item &&
                        ItemStack.isSameItemSameComponents(item.toItemStack(), infinityStack)) {
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
                return List.of(new ResourceAmount(ItemResource.ofItemStack(infinityStack), Long.MAX_VALUE));
            }


            @Override
            public long getStored() {
                return Long.MAX_VALUE; // always infinite
            }

            @Override
            public com.refinedmods.refinedstorage.common.api.storage.StorageType getType() {
                return base.getType();
            }
        };
    }

    public ItemStack getInfinityStack() {
        return infinityStack.copy();
    }
}