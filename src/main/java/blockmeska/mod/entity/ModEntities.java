package blockmeska.mod.entity;

import blockmeska.mod.HedgehogMod;
import blockmeska.mod.entity.custom.HedgehogEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class ModEntities {

    public static final EntityType<HedgehogEntity> FOREST_HEDGEHOG = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(HedgehogMod.MOD_ID, "forest_hedgehog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HedgehogEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<HedgehogEntity> DESERT_HEDGEHOG = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(HedgehogMod.MOD_ID, "desert_hedgehog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HedgehogEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<HedgehogEntity> SNOW_HEDGEHOG = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(HedgehogMod.MOD_ID, "snow_hedgehog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HedgehogEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static void registerModEntitySpawns() {
        registerHedgieSpawns(FOREST_HEDGEHOG);
        registerHedgieSpawns(DESERT_HEDGEHOG);
        registerHedgieSpawns(SNOW_HEDGEHOG);
    }

    private static void registerHedgieSpawns(EntityType<HedgehogEntity> Hedgehog) {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.CREATURE,
                Hedgehog,
                3,
                1,
                3
        );
        SpawnRestriction.register(Hedgehog, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }

}