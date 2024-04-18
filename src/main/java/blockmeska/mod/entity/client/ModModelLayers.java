package blockmeska.mod.entity.client;

import blockmeska.mod.HedgehogMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer HEDGEHOG =
            new EntityModelLayer(new Identifier(HedgehogMod.MOD_ID, "hedgehog"), "main");
}
