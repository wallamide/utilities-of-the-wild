package net.servate.uotw.reg;


import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.servate.uotw.entity.LizalBoomerang_Entity;
import net.servate.uotw.entity.LizalForkedBoomerang_Entity;
import net.servate.uotw.entity.Lizal_Tri_Boomerang_Entity;
import net.servate.uotw.item.LizalBoomerang_Item;
import net.servate.uotw.item.LizalForkedBoomerang_Item;
import net.servate.uotw.item.Lizal_Tri_Boomerang_Item;

public class UotWEntityType {

    public static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();
    
    public static final EntityType<LizalBoomerang_Entity> LIZAL_BOOMERANG = register("lizal_boomerang", createLizalBoomerang(UotWItems.LIZAL_BOOMERANG));
    public static final EntityType<LizalForkedBoomerang_Entity> LIZAL_FORKED_BOOMERANG = register("lizal_forked_boomerang", createLizalForkedBoomerang(UotWItems.LIZAL_FORKED_BOOMERANG));
    public static final EntityType<Lizal_Tri_Boomerang_Entity> LIZAL_TRI_BOOMERANG = register("lizal_tri_boomerang", createLizalTriBoomerang(UotWItems.LIZAL_TRI_BOOMERANG));

    public static void register() {
        for (Identifier id : ENTITY_TYPES.keySet()) {
            Registry.register(Registry.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    public static <T extends EntityType<?>> T register(String name, T type) {
        Identifier id = new Identifier("uotw", name);
        ENTITY_TYPES.put(id, type);
        return type;
    }

    public static EntityType<LizalBoomerang_Entity> createLizalBoomerang(LizalBoomerang_Item item) {
        return FabricEntityTypeBuilder.<LizalBoomerang_Entity>create(SpawnGroup.MISC, (entity, world) -> new LizalBoomerang_Entity(entity, world, item)).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build();
    }
    public static EntityType<LizalForkedBoomerang_Entity> createLizalForkedBoomerang(LizalForkedBoomerang_Item item) {
        return FabricEntityTypeBuilder.<LizalForkedBoomerang_Entity>create(SpawnGroup.MISC, (entity, world) -> new LizalForkedBoomerang_Entity(entity, world, item)).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build();
    }
    public static EntityType<Lizal_Tri_Boomerang_Entity> createLizalTriBoomerang(Lizal_Tri_Boomerang_Item item) {
        return FabricEntityTypeBuilder.<Lizal_Tri_Boomerang_Entity>create(SpawnGroup.MISC, (entity, world) -> new Lizal_Tri_Boomerang_Entity(entity, world, item)).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build();
    }

}
