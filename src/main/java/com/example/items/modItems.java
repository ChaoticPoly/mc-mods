package com.example.items;

import net.minecraft.resources.ResourceKey;

import net.minecraft.world.item.Item;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class modItems {
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static final Item PLACEHOLDER = register(ModItemIds.PLACEHOLDER, Circle::new, new Item.Properties());

    public static void initialize(){

    }

}
