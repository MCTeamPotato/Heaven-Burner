package com.teampotato.incinerator;

import L_Ender.cataclysm.client.render.CMRenderTypes;
import L_Ender.cataclysm.init.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.common.Mod;

@Mod(Incinerator.ID)
public class Incinerator {
    public static final String ID = "incinerator";
    private static final ResourceLocation SIGIL = new ResourceLocation("cataclysm:textures/entity/soul_flame_strike_sigil.png");

    private static void drawVertex(Matrix4f matrix4f, Matrix3f matrix3f, IVertexBuilder builder, int pX, int pZ, float pU, float pV) {
        builder
                .vertex(matrix4f, pX, 0, pZ)
                .color(255, 255, 255, 255)
                .uv(pU, pV)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(240)
                .normal(matrix3f, 1, 1, 0)
                .endVertex();
    }

    public static void renderPlayerWithIncinerator(AbstractClientPlayerEntity pEntity, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, float pPartialTicks) {
        ResourceLocation item = pEntity.getMainHandItem().getItem().getRegistryName();
        if (pEntity.isUsingItem() && item != null && item.equals(ModItems.THE_INCINERATOR.get().getRegistryName())) {
            int i = pEntity.getTicksUsingItem();
            float f2 = pEntity.tickCount + pPartialTicks;
            float f3 = MathHelper.clamp(i, 1, 60);
            pMatrixStack.pushPose();
            IVertexBuilder builder = ItemRenderer.getArmorFoilBuffer(pBuffer, CMRenderTypes.getGlowingEffect(SIGIL), false, true);
            pMatrixStack.translate(0.0D, 0.001D, 0.0D);
            pMatrixStack.scale(f3 * 0.05F, f3 * 0.05F, f3 * 0.05F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F + f2));
            MatrixStack.Entry entry = pMatrixStack.last();
            Matrix4f matrix4f = entry.pose();
            Matrix3f matrix3f = entry.normal();
            drawVertex(matrix4f, matrix3f, builder, -1, -1, 0.0F, 0.0F);
            drawVertex(matrix4f, matrix3f, builder, -1, 1, 0.0F, 1.0F);
            drawVertex(matrix4f, matrix3f, builder, 1, 1, 1.0F, 1.0F);
            drawVertex(matrix4f, matrix3f, builder, 1, -1, 1.0F, 0.0F);
            pMatrixStack.popPose();
        }
    }
}
