package com.benbenlaw.infinitydisks.mixin;

import com.benbenlaw.infinitydisks.item.InfinityDiskManager;
import com.refinedmods.refinedstorage.api.resource.ResourceKey;
import com.refinedmods.refinedstorage.api.resource.repository.ResourceRepositoryImpl;
import com.refinedmods.refinedstorage.common.support.resource.ItemResource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourceRepositoryImpl.class)
public abstract class MixinResourceRepositoryImpl<T> {

    @Inject(
        method = "update",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onUpdate(ResourceKey resource, long amount, CallbackInfo ci) {
        // Replace this with your infinity check

        //ci.cancel();

        if (resource instanceof ItemResource item) {
            ItemStack stack = item.toItemStack();

            // Check against your infinite items
            if (InfinityDiskManager.isInfinite(stack)) {
                // Cancel the update
                ci.cancel();
            }
        }


    }
}
