package net.servate.uotw.client.render.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import net.servate.uotw.client.render.entity.LizalForkedBoomerangEntityRenderer;
import net.servate.uotw.client.render.entity.model.LizalForkedBoomerangEntityModel;
import net.servate.uotw.item.LizalForkedBoomerang_Item;

@Environment(EnvType.CLIENT)
public enum LizalForkedBoomerangItemRenderer {
    INSTANCE;

    private final LizalForkedBoomerangEntityModel lizalForkedBoomerangEntityModel = new LizalForkedBoomerangEntityModel(LizalForkedBoomerangEntityModel.getTexturedModelData().createModel());

    public boolean render(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
            int overlay, BakedModel model) {
        if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND || renderMode == ModelTransformation.Mode.FIXED) {
            return true;
        }

        matrices.push();

        model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);

        if (entity != null && entity.isUsingItem() && entity.getActiveItem() == stack
                && (renderMode == ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND || renderMode == ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND)) {
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(120F));
            matrices.translate(-0.5D, -0.5D, -0.5D);
        } else if (renderMode != ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND && renderMode != ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND) {
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-45F));
            matrices.translate(-0.5D, -0.5D, -0.5D);
        } else {
            matrices.translate(-0.5D, -0.5D, -0.5D);
        }

        matrices.scale(1.0F, 1.0F, 1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers,
                this.lizalForkedBoomerangEntityModel.getLayer(LizalForkedBoomerangEntityRenderer.getTexture(((LizalForkedBoomerang_Item) stack.getItem()).getEntityType())), false, stack.hasGlint());
        this.lizalForkedBoomerangEntityModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
        return true;
    }
}
