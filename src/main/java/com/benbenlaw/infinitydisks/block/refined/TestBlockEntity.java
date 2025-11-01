package com.benbenlaw.infinitydisks.block.refined;

import com.benbenlaw.infinitydisks.block.InfinityDiskBlocks;
import com.benbenlaw.infinitydisks.block.Tickable;
import com.benbenlaw.infinitydisks.item.InfiniteSingleItemStorage;
import com.refinedmods.refinedstorage.api.network.Network;
import com.refinedmods.refinedstorage.api.network.impl.node.AbstractStorageContainerNetworkNode;
import com.refinedmods.refinedstorage.api.network.impl.node.SimpleNetworkNode;
import com.refinedmods.refinedstorage.api.network.impl.node.storage.StorageNetworkNode;
import com.refinedmods.refinedstorage.api.network.storage.StorageProvider;
import com.refinedmods.refinedstorage.api.storage.Storage;
import com.refinedmods.refinedstorage.common.api.RefinedStorageApi;
import com.refinedmods.refinedstorage.common.api.support.network.NetworkNodeContainerProvider;
import com.refinedmods.refinedstorage.common.storage.storageblock.StorageBlockBlockEntity;
import com.refinedmods.refinedstorage.common.support.network.AbstractBaseNetworkNodeContainerBlockEntity;
import com.refinedmods.refinedstorage.neoforge.storage.externalstorage.ForgeExternalStorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;

import java.util.Optional;
import java.util.UUID;

public class TestBlockEntity extends AbstractBaseNetworkNodeContainerBlockEntity<StorageNetworkNode> implements StorageProvider, Tickable {

    public TestBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(InfinityDiskBlocks.TEST_BLOCK_ENTITY.get(), p_155229_, p_155230_, new StorageNetworkNode(100, 1, 1));
        mainNetworkNode.setProvider(new AbstractStorageContainerNetworkNode.Provider() {
            @Override
            public Optional<Storage> resolve(int index) {
                return Optional.of(new InfItemStorage(() -> mainNetworkNode.onStorageChanged(index), Items.COAL));
            }
        });
    }


    private int ticks = 0;

    public void tick() {
        if (!mainNetworkNode.isActive())
            mainNetworkNode.setActive(true);

        ticks++;

        if (ticks % 20 == 0) {
            //mainNetworkNode.onStorageChanged(0);
        }

        doWork();
    }

    @Override
    public Storage getStorage() {
        return new InfiniteSingleItemStorage(Items.COAL.getDefaultInstance());
    }

    @Override
    public Component getName() {
        return Component.literal("Test");
    }
}

