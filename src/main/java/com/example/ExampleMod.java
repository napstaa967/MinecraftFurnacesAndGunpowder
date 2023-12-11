package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;

import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("prpr");

	public static final Item GUNPOWDERER = new Item(new FabricItemSettings());
	private static void addITITIG(FabricItemGroupEntries entries) {
		entries.add(GUNPOWDERER);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("prpr", "gunpowderer"), GUNPOWDERER);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ExampleMod::addITITIG);
		LOGGER.info("Hello Fabric world!");
	}
}