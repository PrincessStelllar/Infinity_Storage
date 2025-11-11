package com.benbenlaw.infinitystorage.item;

import com.benbenlaw.infinitystorage.InfinityStorage;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ISItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfinityStorage.MOD_ID);

    public static final DeferredItem<Item> EMPTY_INFINITY_DRIVE = ITEMS.register("empty_infinity_drive",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INFINITY_COBBLESTONE_DRIVE = ITEMS.register("infinity_cobblestone_drive",
            () -> new InfinityDrive(new Item.Properties(), InfinityContent.of(Items.COBBLESTONE.getDefaultInstance())));

    public static final DeferredItem<Item> INFINITY_WATER_DRIVE = ITEMS.register("infinity_water_drive",
            () -> new InfinityDrive(new Item.Properties(), InfinityContent.of(new FluidStack(Fluids.WATER, 1000))));

    public static final DeferredItem<Item> INFINITY_LAVA_DRIVE = ITEMS.register("infinity_lava_drive",
            () -> new InfinityDrive(new Item.Properties(), InfinityContent.of(new FluidStack(Fluids.LAVA, 1000))));

}
