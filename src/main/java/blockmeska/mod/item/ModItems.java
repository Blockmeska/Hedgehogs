package blockmeska.mod.item;

import blockmeska.mod.HedgehogMod;
import blockmeska.mod.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DESERT_HEDGEHOG_SPAWN_EGG = registerModItems("desert_hedgehog_spawn_egg",
            new SpawnEggItem(ModEntities.DESERT_HEDGEHOG, 0xa18a81, 0x6a504f, new FabricItemSettings()));
    public static final Item FOREST_HEDGEHOG_SPAWN_EGG = registerModItems("forest_hedgehog_spawn_egg",
            new SpawnEggItem(ModEntities.FOREST_HEDGEHOG, 0x3c352c, 0xa29685, new FabricItemSettings()));
    public static final Item SNOW_HEDGEHOG_SPAWN_EGG = registerModItems("snow_hedgehog_spawn_egg",
            new SpawnEggItem(ModEntities.SNOW_HEDGEHOG, 0x9f8976, 0xf3e8d3, new FabricItemSettings()));

    public static Item registerModItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HedgehogMod.MOD_ID, name), item);
    }

    private static void addItemsToSpawnEggsTab(FabricItemGroupEntries entries) {
        entries.add(ModItems.FOREST_HEDGEHOG_SPAWN_EGG);
        entries.add(ModItems.DESERT_HEDGEHOG_SPAWN_EGG);
        entries.add(ModItems.SNOW_HEDGEHOG_SPAWN_EGG);
    }

    public static void addItemsToGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggsTab);
    }
}