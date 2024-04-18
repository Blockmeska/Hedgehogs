package blockmeska.mod;

import blockmeska.mod.entity.ModEntities;
import blockmeska.mod.entity.client.HedgehogModel;
import blockmeska.mod.entity.client.HedgehogRenderer;
import blockmeska.mod.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HedgehogModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.FOREST_HEDGEHOG, HedgehogRenderer::new);
        EntityRendererRegistry.register(ModEntities.DESERT_HEDGEHOG, HedgehogRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNOW_HEDGEHOG, HedgehogRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.HEDGEHOG, HedgehogModel::getTexturedModelData);
    }
}
