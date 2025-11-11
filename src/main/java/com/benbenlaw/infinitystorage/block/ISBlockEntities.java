package com.benbenlaw.infinitystorage.block;

import com.benbenlaw.infinitystorage.InfinityStorage;
import com.benbenlaw.infinitystorage.block.custom.InfinityStorageDriveBlock;
import com.benbenlaw.infinitystorage.block.entity.InfinityStorageDriveBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ISBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, InfinityStorage.MOD_ID);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfinityStorageDriveBlockEntity>> INFINITY_STORAGE_DRIVE_BLOCK_ENTITY =
            register("crafter_block_entity", () ->
                    BlockEntityType.Builder.of(InfinityStorageDriveBlockEntity::new, ISBlocks.INFINITY_STORAGE_DRIVE.get()));


    public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(@Nonnull String name, @Nonnull Supplier<BlockEntityType.Builder<T>> initializer) {
        return BLOCK_ENTITIES.register(name, () -> initializer.get().build(null));
    }

    public static void registerCapabilities(@Nonnull RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,
                ISBlockEntities.INFINITY_STORAGE_DRIVE_BLOCK_ENTITY.get(), InfinityStorageDriveBlockEntity::getItemHandlerCapability);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK,
                ISBlockEntities.INFINITY_STORAGE_DRIVE_BLOCK_ENTITY.get(), InfinityStorageDriveBlockEntity::getFluidHandlerCapability);

    }

}