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
import net.servate.uotw.client.render.entity.model.LizalBoomerangEntityModel;
import net.servate.uotw.entity.LizalBoomerang_Entity;

@Environment(EnvType.CLIENT)
public class LizalBoomerangEntityRenderer extends EntityRenderer<LizalBoomerang_Entity> {
    private static final Map<EntityType<?>, Identifier> TEXTURES = new HashMap<>();
    private final LizalBoomerangEntityModel model = new LizalBoomerangEntityModel(LizalBoomerangEntityModel.getTexturedModelData().createModel());

    public LizalBoomerangEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(LizalBoomerang_Entity lizalBoomerangEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        //matrixStack.multiply(Vec3f.NEGATIVE_X.getDegreesQuaternion(MathHelper.lerp(g, boomerangEntity.prevX, boomerangEntity.prevX - 86.0F)));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, lizalBoomerangEntity.prevYaw, lizalBoomerangEntity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, lizalBoomerangEntity.prevPitch,lizalBoomerangEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, model.getLayer(this.getTexture(lizalBoomerangEntity)), false, lizalBoomerangEntity.enchantingGlint());
        
        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(0.0f, 0.0f, 0.0f);
        matrixStack.pop();
        super.render(lizalBoomerangEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LizalBoomerang_Entity lizalBoomerangEntity) {
        return getTexture(lizalBoomerangEntity.getType());
    }

    public static Identifier getTexture(EntityType<?> type) {
        if (!TEXTURES.containsKey(type)) {
            TEXTURES.put(type, new Identifier("uotw", "textures/entity/boomerang/" + Registry.ENTITY_TYPE.getId(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}