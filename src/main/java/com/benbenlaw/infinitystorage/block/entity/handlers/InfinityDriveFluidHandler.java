package com.benbenlaw.infinitystorage.block.entity.handlers;

import com.benbenlaw.infinitystorage.item.InfinityDrive;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class InfinityDriveFluidHandler implements IFluidHandler {

    private final InfinityDrive drive;

    public InfinityDriveFluidHandler(InfinityDrive drive) {
        this.drive = drive;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        if (drive.getContent().isFluid()) {
            return drive.getContent().getInfinityFluidStack();
        }
        return FluidStack.EMPTY;
    }

    @Override
    public int getTankCapacity(int tank) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return drive.getContent().isFluid() && stack.is(drive.getContent().getInfinityFluidStack().getFluid());
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return resource.getAmount();
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (drive.getContent().isFluid() && resource.is(drive.getContent().getInfinityFluidStack().getFluid())) {
            return new FluidStack(drive.getContent().getInfinityFluidStack().getFluid(), resource.getAmount());
        }
        return FluidStack.EMPTY;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (drive.getContent().isFluid()) {
            FluidStack copy = drive.getContent().getInfinityFluidStack().copy();
            copy.setAmount(maxDrain);
            return copy;
        }
        return FluidStack.EMPTY;
    }
}