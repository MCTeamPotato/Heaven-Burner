package com.teampotato.incinerator.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teampotato.incinerator.Incinerator;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class MixinMinecraftPlayerRenderer {
    @Inject(method = "render(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", at = @At("HEAD"))
    private void onRender(AbstractClientPlayerEntity pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, CallbackInfo ci) {
        Incinerator.renderPlayerWithIncinerator(pEntity, pMatrixStack, pBuffer, pPartialTicks);
    }
}