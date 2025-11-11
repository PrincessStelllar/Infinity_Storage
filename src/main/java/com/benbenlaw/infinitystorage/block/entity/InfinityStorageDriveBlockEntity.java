package com.benbenlaw.infinitystorage.block.entity;

import com.benbenlaw.infinitystorage.block.ISBlockEntities;
import com.benbenlaw.infinitystorage.block.custom.IInventoryHandlingBlockEntity;
import com.benbenlaw.infinitystorage.block.custom.InputOutputItemHandler;
import com.benbenlaw.infinitystorage.item.InfinityContent;
import com.benbenlaw.infinitystorage.item.InfinityDrive;
import com.benbenlaw.infinitystorage.screen.InfinityStorageDriveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class InfinityStorageDriveBlockEntity extends BlockEntity implements MenuProvider, IInventoryHandlingBlockEntity {

    private final ItemStackHandler itemHandler = new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            sync();
        }
    };

    private FakePlayer fakePlayer;

    public InfinityStorageDriveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ISBlockEntities.INFINITY_STORAGE_DRIVE_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public void sync() {
        if (level instanceof ServerLevel serverLevel) {
            LevelChunk chunk = serverLevel.getChunkAt(getBlockPos());
            if (Objects.requireNonNull(chunk.getLevel()).getChunkSource() instanceof ServerChunkCache chunkCache) {
                chunkCache.chunkMap.getPlayers(chunk.getPos(), false).forEach(this::syncContents);
            }
        }
    }

    public void syncContents(ServerPlayer player) {
        player.connection.send(Objects.requireNonNull(getUpdatePacket()));
    }

    // =========================
    // Item Handler
    // =========================
    private final IItemHandler driveItemHandler = new InputOutputItemHandler(itemHandler,
            (i, stack) -> stack.getItem() instanceof InfinityDrive, // allow insertion
            i -> true // allow extraction
    ) {
        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            ItemStack driveStack = super.getStackInSlot(slot);

            if (driveStack.getItem() instanceof InfinityDrive drive) {
                InfinityContent content = drive.getContent();

                if (content.isItem()) {
                    ItemStack copy = content.getInfinityStack().copy();
                    copy.setCount(Integer.MAX_VALUE); // show infinite
                    return copy;
                }

                // If it's a fluid, hide the drive from item automation
                return ItemStack.EMPTY;
            }
            return driveStack;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack driveStack = itemHandler.getStackInSlot(slot);

            if (driveStack.getItem() instanceof InfinityDrive drive) {
                InfinityContent content = drive.getContent();

                if (content.isItem()) {
                    return new ItemStack(content.getInfinityStack().getItem(), amount);
                }

                // Hide the drive itself from extraction if it contains a fluid
                return ItemStack.EMPTY;
            }

            return super.extractItem(slot, amount, simulate);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            ItemStack driveStack = itemHandler.getStackInSlot(slot);

            if (driveStack.getItem() instanceof InfinityDrive drive) {
                InfinityContent content = drive.getContent();

                if (content.isItem() && stack.is(content.getInfinityStack().getItem())) {
                    return ItemStack.EMPTY; // consume inserted stack but keep the drive
                }
            }

            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return Integer.MAX_VALUE;
        }
    };


    // =========================
    // Fluid Handler
    // =========================
    private final IFluidHandler driveFluidHandler = new IFluidHandler() {
        @Override
        public int getTanks() {
            return itemHandler.getSlots();
        }

        @Override
        public FluidStack getFluidInTank(int tank) {
            ItemStack stack = itemHandler.getStackInSlot(tank);
            if (stack.getItem() instanceof InfinityDrive drive) {
                InfinityContent content = drive.getContent();
                if (content.isFluid()) {
                    FluidStack copy = content.getInfinityFluidStack().copy();
                    copy.setAmount(Integer.MAX_VALUE); // display as infinite
                    return copy;
                }
            }
            return FluidStack.EMPTY;
        }

        @Override
        public int getTankCapacity(int tank) {
            ItemStack stack = itemHandler.getStackInSlot(tank);
            if (stack.getItem() instanceof InfinityDrive drive && drive.getContent().isFluid()) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isFluidValid(int tank, FluidStack stack) {
            ItemStack driveStack = itemHandler.getStackInSlot(tank);
            if (driveStack.getItem() instanceof InfinityDrive drive) {
                InfinityContent content = drive.getContent();
                return content.isFluid() && stack.is(content.getInfinityFluidStack().getFluid());
            }
            return false;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0;
        }

        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (stack.getItem() instanceof InfinityDrive drive) {
                    InfinityContent content = drive.getContent();
                    if (content.isFluid() && resource.is(content.getInfinityFluidStack().getFluid())) {
                        FluidStack copy = content.getInfinityFluidStack().copy();
                        copy.setAmount(resource.getAmount());
                        return copy;
                    }
                }
            }
            return FluidStack.EMPTY;
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (stack.getItem() instanceof InfinityDrive drive) {
                    InfinityContent content = drive.getContent();
                    if (content.isFluid()) {
                        FluidStack copy = content.getInfinityFluidStack().copy();
                        copy.setAmount(maxDrain);
                        return copy;
                    }
                }
            }
            return FluidStack.EMPTY;
        }

    };


    // =========================
    // Capability getters
    // =========================
    public @Nullable IItemHandler getItemHandlerCapability(@Nullable Direction side) {
        return driveItemHandler;
    }

    public @Nullable IFluidHandler getFluidHandlerCapability(@Nullable Direction side) {
        return driveFluidHandler;
    }

    // =========================
    // Standard BE logic
    // =========================
    public void setHandler(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            this.itemHandler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }

    public void tick() {}

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.infinitystorage.infinity_storage_drive");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int container, @NotNull Inventory inventory, @NotNull Player player) {
        return new InfinityStorageDriveMenu(container, inventory, this.getBlockPos());
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.setChanged();
    }

    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(compoundTag, provider);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag, provider);
        return compoundTag;
    }

    @Override
    public void onDataPacket(@NotNull Connection connection, @NotNull ClientboundBlockEntityDataPacket clientboundBlockEntityDataPacket,
                             HolderLookup.@NotNull Provider provider) {
        super.onDataPacket(connection, clientboundBlockEntityDataPacket, provider);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.put("inventory", this.itemHandler.serializeNBT(provider));
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        this.itemHandler.deserializeNBT(provider, compoundTag.getCompound("inventory"));
        super.loadAdditional(compoundTag, provider);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
