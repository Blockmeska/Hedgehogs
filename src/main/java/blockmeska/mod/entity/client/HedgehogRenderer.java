package blockmeska.mod.entity.client;

import blockmeska.mod.HedgehogMod;
import blockmeska.mod.entity.ModEntities;
import blockmeska.mod.entity.custom.HedgehogEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HedgehogRenderer extends MobEntityRenderer<HedgehogEntity, HedgehogModel<HedgehogEntity>> {
    private static final Identifier FOREST_TEXTURE = new Identifier(HedgehogMod.MOD_ID, "textures/entity/forest_hedgehog.png");
    private static final Identifier DESERT_TEXTURE = new Identifier(HedgehogMod.MOD_ID, "textures/entity/desert_hedgehog.png");
    private static final Identifier SNOW_TEXTURE = new Identifier(HedgehogMod.MOD_ID, "textures/entity/snow_hedgehog.png");

    public HedgehogRenderer(EntityRendererFactory.Context context) {
        super(context, new HedgehogModel<>(context.getPart(ModModelLayers.HEDGEHOG)), 0.3f);
    }

    @Override
    public Identifier getTexture(HedgehogEntity entity) {
        Identifier texture;
        if (entity.getType() == ModEntities.FOREST_HEDGEHOG) {
            texture = FOREST_TEXTURE;
        } else if (entity.getType() == ModEntities.DESERT_HEDGEHOG) {
            texture = DESERT_TEXTURE;
        } else if (entity.getType() == ModEntities.SNOW_HEDGEHOG) {
            texture = SNOW_TEXTURE;
        } else {
            texture = DESERT_TEXTURE;
        }
        return texture;
    }

    @Override
    public void render(HedgehogEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}