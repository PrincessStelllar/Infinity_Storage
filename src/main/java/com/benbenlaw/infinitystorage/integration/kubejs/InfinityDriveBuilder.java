package com.benbenlaw.infinitystorage.integration.kubejs;

import com.benbenlaw.infinitystorage.item.InfinityContent;
import com.benbenlaw.infinitystorage.item.InfinityDrive;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class InfinityDriveBuilder extends ItemBuilder {

    private ItemStack infinityStack;
    private FluidStack infinityFluidStack;

    private InfinityContent infinityContent;

    InfinityDriveBuilder(ResourceLocation id) {
        super(id);
    }

    public InfinityDriveBuilder infinityStack(ItemStack infinityStack) {
        this.infinityStack = infinityStack;
        this.infinityContent = InfinityContent.of(infinityStack);
        return this;
    }

    public InfinityDriveBuilder infinityFluidStack(FluidStack infinityFluidStack) {
        this.infinityFluidStack = infinityFluidStack;
        this.infinityContent = InfinityContent.of(infinityFluidStack);
        return this;
    }

    @Override
    public Item createObject() {
        return new InfinityDrive(createItemProperties(), infinityContent);
    }
}
