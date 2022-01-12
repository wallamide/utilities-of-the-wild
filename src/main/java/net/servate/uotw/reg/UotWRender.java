package net.servate.uotw.reg;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.servate.uotw.client.EntitySpawnPacket;
import net.servate.uotw.client.render.entity.LizalBoomerangEntityRenderer;
import net.servate.uotw.client.render.entity.LizalForkedBoomerangEntityRenderer;
import net.servate.uotw.client.render.entity.Lizal_Tri_Boomerang_Entity_Renderer;
import net.servate.uotw.client.render.entity.model.LizalBoomerangEntityModel;
import net.servate.uotw.client.render.entity.model.LizalForkedBoomerangEntityModel;
import net.servate.uotw.client.render.entity.model.Lizal_Tri_Boomerang_Entity_Model;

public class UotWRender {
    
    public static final EntityModelLayer LIZAL_BOOMERANG_LAYER = new EntityModelLayer(new Identifier("uotw:lizal_boomerang_render_layer"), "lizal_boomerang_render_layer");
    public static final EntityModelLayer LIZAL_FORKED_BOOMERANG_LAYER = new EntityModelLayer(new Identifier("uotw:lizal_forked_boomerang_render_layer"), "lizal_forked_boomerang_render_layer");
    public static final EntityModelLayer LIZAL_TRI_BOOMERANG_LAYER = new EntityModelLayer(new Identifier("uotw:lizal_tri_boomerang_render_layer"), "lizal_tri_boomerang_render_layer");


    public static void register() {
    //packet
    ClientPlayNetworking.registerGlobalReceiver(EntitySpawnPacket.ID, EntitySpawnPacket::onPacket);

    //boomerangs
    EntityRendererRegistry.register(UotWEntityType.LIZAL_BOOMERANG, LizalBoomerangEntityRenderer::new);
    EntityRendererRegistry.register(UotWEntityType.LIZAL_FORKED_BOOMERANG, LizalForkedBoomerangEntityRenderer::new);
    EntityRendererRegistry.register(UotWEntityType.LIZAL_TRI_BOOMERANG, Lizal_Tri_Boomerang_Entity_Renderer::new);

    EntityModelLayerRegistry.registerModelLayer(LIZAL_BOOMERANG_LAYER, LizalBoomerangEntityModel::getTexturedModelData);
    EntityModelLayerRegistry.registerModelLayer(LIZAL_FORKED_BOOMERANG_LAYER, LizalForkedBoomerangEntityModel::getTexturedModelData);
    EntityModelLayerRegistry.registerModelLayer(LIZAL_TRI_BOOMERANG_LAYER, Lizal_Tri_Boomerang_Entity_Model::getTexturedModelData);


    }
}
