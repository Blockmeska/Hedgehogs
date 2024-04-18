package blockmeska.mod;

import blockmeska.mod.entity.ModEntities;
import blockmeska.mod.entity.custom.HedgehogEntity;
import blockmeska.mod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class HedgehogMod implements ModInitializer {
    public static final String MOD_ID = "hedgehog-mod";

	@Override
	public void onInitialize() {
		ModItems.addItemsToGroups();

		FabricDefaultAttributeRegistry.register(ModEntities.FOREST_HEDGEHOG, HedgehogEntity.createHedgehogAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DESERT_HEDGEHOG, HedgehogEntity.createHedgehogAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SNOW_HEDGEHOG, HedgehogEntity.createHedgehogAttributes());
		ModEntities.registerModEntitySpawns();
	}
}