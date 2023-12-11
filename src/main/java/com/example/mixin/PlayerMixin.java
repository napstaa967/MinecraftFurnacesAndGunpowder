package com.example.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerMixin {
    @Inject(at=@At("HEAD"), method="sendMessage")
    public void test(Text message, boolean overlay, CallbackInfo ci) {
        System.out.println(message.getString());
    }
}
