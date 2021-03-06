/*
 * Copyright (c) 2019 RoccoDev
 * All rights reserved.
 */

package dev.rocco.bukkit.practice.arena;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class ArenaKit {

    private String name, description;

    private boolean noHitDelay;
    private ItemStack icon;

    private int hitDelay;

    private String fileName;

    private ItemStack[] inventory;
    private ItemStack[] armor;

    private boolean hungerEnabled = true;

    private int maxHealth = 20;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public ArenaKit(String name, String description, boolean noHitDelay, ItemStack icon, int hitDelay, ItemStack[] inventory,
                    ItemStack[] armor, String fileName, boolean hunger, int maxHealth) {
        this.name = name;
        this.description = description;
        this.noHitDelay = noHitDelay;
        this.icon = icon;
        this.hitDelay = hitDelay;
        this.inventory = inventory;
        this.fileName = fileName;
        this.armor = armor;

        this.hungerEnabled = hunger;
        this.maxHealth = maxHealth;

        initIcon();
    }

    public ArenaKit(String name, ItemStack[] inventory, ItemStack[] armor) {
        this.fileName = name + ".yml";
        this.name = name;
        this.description = "Replace in the kit config.";
        this.inventory = inventory;
        this.armor = armor;

        this.noHitDelay = false;
        this.icon = new ItemStack(Material.PAPER);

        initIcon();
    }

    private void initIcon() {
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(description.split("\n")));
        icon.setItemMeta(meta);
    }

    public void apply(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.setContents(inventory);
        inv.setArmorContents(armor);

        player.updateInventory();
    }

    public ItemStack getIcon() {
        return icon;
    }

    public boolean hasNoHitDelay() {
        return noHitDelay;
    }

    public int getHitDelay() {
        return !noHitDelay ? 20 : hitDelay;
    }

    public boolean isPots() {
        return Arrays.stream(inventory).anyMatch(item ->
                item.getType() == Material.POTION && ((PotionMeta)item.getItemMeta()).hasCustomEffect(PotionEffectType.HEAL));
    }

    public boolean isSoups() {
        return Arrays.stream(inventory).anyMatch(item ->
                item.getType() == Material.MUSHROOM_SOUP);
    }

    public boolean isHungerEnabled() {
        return hungerEnabled;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public ItemStack[] getArmor() {
        return armor;
    }
}
