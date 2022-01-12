package net.servate.uotw.client.render.entity;

import java.util.HashMap;
import java.util.Map;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;
import net.servate.uotw.client.render.entity.model.LizalForkedBoomerangEntityModel;
import net.servate.uotw.entity.LizalForkedBoomerang_Entity;

@Environment(EnvType.CLIENT)
public class LizalForkedBoomerangEntityRenderer extends EntityRenderer<LizalForkedBoomerang_Entity> {
    private static final Map<EntityType<?>, Identifier> TEXTURES = new HashMap<>();
    private final LizalForkedBoomerangEntityModel model = new LizalForkedBoomerangEntityModel(LizalForkedBoomerangEntityModel.getTexturedModelData().createModel());

    public LizalForkedBoomerangEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(LizalForkedBoomerang_Entity lizalForkedBoomerangEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        //matrixStack.multiply(Vec3f.NEGATIVE_X.getDegreesQuaternion(MathHelper.lerp(g, boomerangEntity.prevX, boomerangEntity.prevX - 86.0F)));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, lizalForkedBoomerangEntity.prevYaw, lizalForkedBoomerangEntity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, lizalForkedBoomerangEntity.prevPitch, lizalForkedBoomerangEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, model.getLayer(this.getTexture(lizalForkedBoomerangEntity)), false, lizalForkedBoomerangEntity.enchantingGlint());
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        //matrixStack.scale(0.0f, -6.0f, 0.0f);;
        matrixStack.pop();
        super.render(lizalForkedBoomerangEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LizalForkedBoomerang_Entity lizalForkedBoomerangEntity) {
        return getTexture(lizalForkedBoomerangEntity.getType());
    }

    public static Identifier getTexture(EntityType<?> type) {
        if (!TEXTURES.containsKey(type)) {
            TEXTURES.put(type, new Identifier("uotw", "textures/entity/boomerang/" + Registry.ENTITY_TYPE.getId(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}