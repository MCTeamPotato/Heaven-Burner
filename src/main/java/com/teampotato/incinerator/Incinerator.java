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

    private static void drawVertex(Matrix4f matrix4f, Matrix3f matrix3f, IVertexBuilder builder, int p_229039_4_, int p_229039_6_, float p_229039_7_, float p_229039_8_) {
        builder
                .vertex(matrix4f, p_229039_4_, 0, p_229039_6_)
                .color(255, 255, 255, 255)
                .uv(p_229039_7_, p_229039_8_)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(240)
                .normal(matrix3f, 1, 1, 0)
                .endVertex();
    }

    public static void renderPlayerWithIncinerator(AbstractClientPlayerEntity player, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, float p_225623_3_) {
        ResourceLocation item = player.getMainHandItem().getItem().getRegistryName();
        if (player.isUsingItem() && item != null && item.equals(ModItems.THE_INCINERATOR.get().getRegistryName())) {
            int i = player.getTicksUsingItem();
            float f2 = player.tickCount + p_225623_3_;
            float f3 = MathHelper.clamp(i, 1, 60);
            matrixStackIn.pushPose();
            IVertexBuilder builder = ItemRenderer.getArmorFoilBuffer(buffer, CMRenderTypes.getGlowingEffect(SIGIL), false, true);
            matrixStackIn.translate(0.0D, 0.001D, 0.0D);
            matrixStackIn.scale(f3 * 0.05F, f3 * 0.05F, f3 * 0.05F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F + f2));
            MatrixStack.Entry entry = matrixStackIn.last();
            Matrix4f matrix4f = entry.pose();
            Matrix3f matrix3f = entry.normal();
            drawVertex(matrix4f, matrix3f, builder, -1, -1, 0.0F, 0.0F);
            drawVertex(matrix4f, matrix3f, builder, -1, 1, 0.0F, 1.0F);
            drawVertex(matrix4f, matrix3f, builder, 1, 1, 1.0F, 1.0F);
            drawVertex(matrix4f, matrix3f, builder, 1, -1, 1.0F, 0.0F);
            matrixStackIn.popPose();
        }
    }
}
