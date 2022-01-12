package net.servate.uotw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.servate.uotw.reg.UotWEntityType;
import net.servate.uotw.reg.UotWItems;
import net.servate.uotw.reg.UotWSounds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UotW implements ModInitializer {

	public static String MODID = "uotw";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "uotw_group"), () -> new ItemStack(UotWItems.LIZAL_TRI_BOOMERANG));
	public static final Identifier ID(String path) {return new Identifier("uotw", path);}

	@Override
	public void onInitialize() {

		//UotW.info("Hello Fabric world!");
		UotWItems.register();
		UotWEntityType.register();
		UotWSounds.register();
		
	}
}
