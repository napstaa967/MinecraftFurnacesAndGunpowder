package com.example.mixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {


    @Inject(at = @At("HEAD"), method="tick")
    private static void explode(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo info) {
        ItemStack inputItem = blockEntity.getStack(0);
        ItemStack fuelItem = blockEntity.getStack(1);
        if (inputItem == ItemStack.EMPTY) { return; }
        int burnTime = ((AbstractFurnaceBlockEntityInvoker) blockEntity).getBurnTime();
        if (burnTime <= 0 && !blockEntity.canUseAsFuel(fuelItem)) { return; }
        float power = 0f;
        if (inputItem.getItem() == Items.GUNPOWDER) {
            power = 3.5f;
        } else if (inputItem.getItem() == Items.TNT) {
            power = 15.5f;
        }
        if (power <= 0f) { return; }
        float power_mul = (float) inputItem.getCount() * 0.2f;
        blockEntity.setStack(0, ItemStack.EMPTY);
        Entity tnt = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 2000000, false));
        world.createExplosion(tnt, pos.getX(), pos.getY(), pos.getZ(), power + ((power_mul + 1) * power_mul) / 2, true, World.ExplosionSourceType.BLOCK);
        tnt.discard();
    }
}
