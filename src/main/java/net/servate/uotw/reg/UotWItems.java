package net.servate.uotw.reg;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.servate.uotw.UotW;
import net.servate.uotw.item.LizalBoomerang_Item;
import net.servate.uotw.item.LizalForkedBoomerang_Item;
import net.servate.uotw.item.Lizal_Tri_Boomerang_Item;
/*import net.servate.uotw.item.tools.SPMBroadsword;
import net.servate.uotw.item.tools.SPMSword;*/
import net.servate.uotw.material.LizalMaterial;

public class UotWItems {

    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final ToolMaterial LIZAL_TOOL_MATERIAL = new LizalMaterial();

    //public static final Set<Boomerang_Item> ALL_STAVES = new ReferenceOpenHashSet<>();
    
    //Boomerang
    public static final LizalBoomerang_Item LIZAL_BOOMERANG = register("lizal_boomerang", 
        new LizalBoomerang_Item(UotWItems.LIZAL_TOOL_MATERIAL, 4.0f, -2.0f, () -> UotWEntityType.LIZAL_BOOMERANG, new Item.Settings().group(UotW.GROUP)));
    public static final LizalForkedBoomerang_Item LIZAL_FORKED_BOOMERANG = register("lizal_forked_boomerang",
        new LizalForkedBoomerang_Item(UotWItems.LIZAL_TOOL_MATERIAL, 5.0f, -2.0f, () -> UotWEntityType.LIZAL_FORKED_BOOMERANG, new Item.Settings().group(UotW.GROUP)));
    public static final Lizal_Tri_Boomerang_Item LIZAL_TRI_BOOMERANG = register("lizal_tri_boomerang",
        new Lizal_Tri_Boomerang_Item(UotWItems.LIZAL_TOOL_MATERIAL, 6.0f, -2.0f, () -> UotWEntityType.LIZAL_TRI_BOOMERANG, new Item.Settings().group(UotW.GROUP)));

    //Sword
   
    public static <I extends Item> I register(String name, I item) {
        ITEMS.put(new Identifier("uotw", name), item);
        return item;
    }

    public static void register() {
        for (Identifier id : ITEMS.keySet()){Registry.register(Registry.ITEM , id, ITEMS.get(id));}
    }

}
