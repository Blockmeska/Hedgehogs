package blockmeska.mod.entity.client;

import blockmeska.mod.entity.animation.ModAnimations;
import blockmeska.mod.entity.custom.HedgehogEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class HedgehogModel<T extends HedgehogEntity> extends SinglePartEntityModel<T> {
    private final ModelPart Hedgehog;

    public HedgehogModel(ModelPart root) {
        this.Hedgehog = root.getChild("MainBody");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData MainBody = modelPartData.addChild("MainBody", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Body = MainBody.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(5.0F, -3.0F, -5.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(4.0F, -7.0F, -4.0F, 5.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 0.0F, 0.0F));

        ModelPartData Legs = Body.addChild("Legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData LegPair2 = Legs.addChild("LegPair2", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -2.0F, 3.0F));

        ModelPartData Leg3 = LegPair2.addChild("Leg3", ModelPartBuilder.create().uv(4, 4).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, -6.0F));

        ModelPartData Leg4 = LegPair2.addChild("Leg4", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData LegPair1 = Legs.addChild("LegPair1", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, 0.0F, 0.0F));

        ModelPartData Leg1 = LegPair1.addChild("Leg1", ModelPartBuilder.create().uv(2, 4).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -2.0F, 3.0F));

        ModelPartData Leg2 = LegPair1.addChild("Leg2", ModelPartBuilder.create().uv(4, 2).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, -3.0F));

        ModelPartData Ears = Body.addChild("Ears", ModelPartBuilder.create().uv(2, 2).cuboid(-4.0F, -3.0F, 1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(-10.0F, -3.0F, 1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(13.0F, -4.0F, -4.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(HedgehogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(ModAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EARWIGGLE, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        Hedgehog.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return Hedgehog;
    }
}
