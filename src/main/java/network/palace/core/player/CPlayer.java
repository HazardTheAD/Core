package network.palace.core.player;

import network.palace.core.packets.AbstractPacket;
import network.palace.core.plugin.Plugin;
import network.palace.core.tracking.GameType;
import network.palace.core.tracking.StatisticType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface CPlayer.
 */
public interface CPlayer {

    /**
     * Gets player name.
     *
     * @return player name
     */
    String getName();

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    boolean isOnline();

    /**
     * Sets op.
     *
     * @param isOp the is op
     */
    void setOp(boolean isOp);

    /**
     * Is op boolean.
     *
     * @return the boolean
     */
    boolean isOp();

    /**
     * Get entity id
     *
     * @return entity id
     */
    int getEntityId();

    /**
     * Play sound.
     *
     * @param location the location
     * @param sound    the sound
     * @param volume   the volume
     * @param pitch    the pitch
     */
    void playSound(Location location, Sound sound, float volume, float pitch);

    /**
     * Sets max health.
     *
     * @param health the health
     */
    void setMaxHealth(double health);

    /**
     * Sets health.
     *
     * @param health the health
     */
    void setHealth(double health);

    /**
     * Get the player's health
     *
     * @return the current health
     */
    double getHealth();

    /**
     * Get the player's max health
     *
     * @return the current max health
     */
    double getMaxHealth();

    /**
     * Gets gamemode.
     *
     * @return the gamemode
     */
    GameMode getGamemode();

    /**
     * Sets gamemode.
     *
     * @param gamemode the gamemode
     */
    void setGamemode(GameMode gamemode);

    /**
     * Gets location.
     *
     * @return the location
     */
    Location getLocation();

    /**
     * Gets world.
     *
     * @return the world
     */
    World getWorld();

    /**
     * Teleport.
     *
     * @param location the location
     */
    void teleport(Location location);

    /**
     * Teleport to a player
     *
     * @param tp the player to teleport to
     */
    void teleport(CPlayer tp);

    /**
     * Send message.
     *
     * @param message the message
     */
    void sendMessage(String message);

    /**
     * Gets the string formatted in the player's locale
     *
     * @param key the string to format
     * @return the formatted string
     */
    String getFormattedMessage(String key);

    /**
     * Send format message.
     *
     * @param key the key
     */
    void sendFormatMessage(String key);

    /**
     * Reset player.
     */
    void resetPlayer();

    /**
     * Reset managers.
     */
    void resetManagers();

    /**
     * Sets display name.
     *
     * @param name the name
     */
    void setDisplayName(String name);

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    PlayerInventory getInventory();

    /**
     * Forces an update of the player's entire inventory.
     */
    void updateInventory();

    /**
     * Gets item in slot
     *
     * @param slot the slot
     * @return the item
     */
    ItemStack getItem(int slot);

    /**
     * Gets item in main hand.
     *
     * @return the item
     */
    ItemStack getItemInMainHand();

    /**
     * Gets item in off hand.
     *
     * @return the item
     */
    ItemStack getItemInOffHand();

    /**
     * Gets selected item slot
     *
     * @return the slot
     */
    int getHeldItemSlot();

    /**
     * Set selected item slot
     *
     * @param slot the slot
     */
    void setHeldItemSlot(int slot);

    /**
     * Open inventory.
     *
     * @param inventory the inventory
     */
    void openInventory(Inventory inventory);

    /**
     * Close inventory.
     */
    void closeInventory();

    /**
     * Check if a player has a permission node
     *
     * @param node the node
     * @return whether or not the player has a permission node
     */
    boolean hasPermission(String node);

    /**
     * Sets inventory slot.
     *
     * @param slot  the slot
     * @param stack the stack
     */
    void setInventorySlot(int slot, ItemStack stack);

    /**
     * Add to inventory.
     *
     * @param itemStacks the item stacks
     */
    void addToInventory(ItemStack... itemStacks);

    /**
     * Does inventory contain boolean.
     *
     * @param material the material
     * @return the boolean
     */
    boolean doesInventoryContain(Material material);

    /**
     * Remove from inventory.
     *
     * @param material the material
     */
    void removeFromInventory(Material material);

    /**
     * Gets helmet.
     *
     * @return the helmet
     */
    ItemStack getHelmet();

    /**
     * Sets helmet.
     *
     * @param itemStack the item stack
     */
    void setHelmet(ItemStack itemStack);

    /**
     * Gets chestplate.
     *
     * @return the chestplate
     */
    ItemStack getChestplate();

    /**
     * Sets chestplate.
     *
     * @param itemStack the item stack
     */
    void setChestplate(ItemStack itemStack);

    /**
     * Gets leggings.
     *
     * @return the leggings
     */
    ItemStack getLeggings();

    /**
     * Sets leggings.
     *
     * @param itemStack the item stack
     */
    void setLeggings(ItemStack itemStack);

    /**
     * Gets boots.
     *
     * @return the boots
     */
    ItemStack getBoots();

    /**
     * Sets boots.
     *
     * @param itemStack the item stack
     */
    void setBoots(ItemStack itemStack);

    /**
     * Gets main hand.
     *
     * @return the main hand
     */
    ItemStack getMainHand();

    /**
     * Sets main hand.
     *
     * @param itemStack the item stack
     */
    void setMainHand(ItemStack itemStack);

    /**
     * Gets off hand.
     *
     * @return the off hand
     */
    ItemStack getOffHand();

    /**
     * Sets off hand.
     *
     * @param itemStack the item stack
     */
    void setOffHand(ItemStack itemStack);

    /**
     * Respawn.
     */
    void respawn();

    /**
     * Show player.
     *
     * @param player the player
     */
    void showPlayer(CPlayer player);

//    /**
//     * Show player.
//     *
//     * @param player the player
//     * @param plugin the plugin making this call
//     */
//    void showPlayer(org.bukkit.plugin.Plugin plugin, CPlayer player);

    /**
     * Hide player.
     *
     * @param player the player
     */
    void hidePlayer(CPlayer player);

//    /**
//     * Hide player.
//     *
//     * @param player the player
//     * @param plugin the plugin making this call
//     */
//    void hidePlayer(org.bukkit.plugin.Plugin plugin, CPlayer player);

    /**
     * Can see boolean.
     *
     * @param player the player
     * @return if player can be seen
     */
    boolean canSee(CPlayer player);

    /**
     * Can see boolean.
     *
     * @param player the player
     * @return if player can be seen
     */
    boolean canSee(Player player);

    /**
     * Gets achievement manager.
     *
     * @return the achievement manager
     */
    CPlayerAchievementManager getAchievementManager();

    /**
     * Gets action bar.
     *
     * @return the action bar
     */
    CPlayerActionBarManager getActionBar();

    /**
     * Gets boss bar.
     *
     * @return the boss bar
     */
    CPlayerBossBarManager getBossBar();

    /**
     * Gets header footer.
     *
     * @return the header footer
     */
    CPlayerHeaderFooterManager getHeaderFooter();

    /**
     * Gets particles.
     *
     * @return the particles
     */
    CPlayerParticlesManager getParticles();

    /**
     * Gets resource pack.
     *
     * @return the resource pack
     */
    CPlayerResourcePackManager getResourcePack();

    /**
     * Gets scoreboard.
     *
     * @return the scoreboard
     */
    CPlayerScoreboardManager getScoreboard();

    /**
     * Sets the scoreboard.
     *
     * @param manager the scoreboard manager
     */
    void setScoreboard(CPlayerScoreboardManager manager);

    /**
     * Gets title.
     *
     * @return the title
     */
    CPlayerTitleManager getTitle();

    /**
     * Send packet.
     *
     * @param packet the packet
     */
    void sendPacket(AbstractPacket packet);

    /**
     * Send to server.
     *
     * @param server the server
     */
    void sendToServer(String server);

    /**
     * Sets locale.
     *
     * @param locale the locale
     */
    void setLocale(String locale);

    /**
     * Gets locale.
     *
     * @return the locale
     */
    String getLocale();

    /**
     * Gets bukkit player.
     *
     * @return the bukkit player
     */
    Player getBukkitPlayer();

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    UUID getUniqueId();

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    UUID getUuid();

    /**
     * Sets status.
     *
     * @param status the status
     */
    void setStatus(PlayerStatus status);

    /**
     * Gets status.
     *
     * @return the status
     */
    PlayerStatus getStatus();

    /**
     * Gets rank.
     *
     * @return the rank
     */
    Rank getRank();

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    void setRank(Rank rank);

    /**
     * Sets texture value.
     *
     * @param textureValue the texture value
     */
    void setTextureValue(String textureValue);

    /**
     * Gets texture value.
     *
     * @return the texture value
     */
    String getTextureValue();

    /**
     * Sets texture signature.
     *
     * @param textureSignature the texture signature
     */
    void setTextureSignature(String textureSignature);

    /**
     * Gets texture signature.
     *
     * @return the texture signature
     */
    String getTextureSignature();

    /**
     * Sets pack.
     *
     * @param pack the pack
     */
    void setPack(String pack);

    /**
     * Gets pack.
     *
     * @return the pack
     */
    String getPack();

    /**
     * Check if player has an achievement
     *
     * @param i achievement ID
     * @return if player has achievement
     */
    boolean hasAchievement(int i);

    /**
     * Give player an achievement
     *
     * @param i ID of achievement
     */
    void giveAchievement(int i);

    /**
     * Get the selected block within a range
     *
     * @param range the max distance
     * @return the selected block
     */
    Block getTargetBlock(int range);

    /**
     * Get the player's ping to the server
     *
     * @return player's ping
     */
    int getPing();

//    /**
//     * Set the player's ping to the server
//     *
//     * @param ping the current ping
//     */
//    void setPing(int ping);

    /**
     * Get whether a player can fly
     *
     * @return flight value
     */
    boolean getAllowFlight();

    /**
     * Set whether a player can fly
     *
     * @param fly flight value
     */
    void setAllowFlight(boolean fly);

    /**
     * Get whether a player is flying
     *
     * @return flying value
     */
    boolean isFlying();

    /**
     * Set whether a player is flying
     *
     * @param fly flying value
     */
    void setFlying(boolean fly);

    /**
     * Kick a player from the server
     *
     * @param reason kick reason
     */
    void kick(String reason);

    /**
     * Force player to perform a command
     *
     * @param cmd Command
     */
    void performCommand(String cmd);

    /**
     * Get the tokens the player currently has
     *
     * @return the tokens the player has
     */
    int getTokens();

    /**
     * Get the balance the player currently has
     *
     * @return the balance the player has
     */
    int getBalance();

    /**
     * Add tokens to the player
     *
     * @param amount the amount to give to the player
     */
    void addTokens(int amount);

    /**
     * Add balance to the player
     *
     * @param amount the amount to add
     */
    void addBalance(int amount);

    /**
     * Set the players tokens
     *
     * @param amount the amount to set it to
     */
    void setTokens(int amount);

    /**
     * Set the balance of the player
     *
     * @param amount the amount to set it to
     */
    void setBalance(int amount);

    /**
     * Remove tokens from a player
     *
     * @param amount the amount to remove
     */
    void removeTokens(int amount);

    /**
     * Remove balance from a player
     *
     * @param amount the amount to remove
     */
    void removeBalance(int amount);

    /**
     * Add a game statistic to a player
     *
     * @param gameType      the game type that the statistic was earned in
     * @param statisticType the statistic that was earned
     * @param amount        the amount that was earned
     */
    void addStatistic(GameType gameType, StatisticType statisticType, int amount);

    /**
     * Get a statistic of a player
     *
     * @param gameType      the game
     * @param statisticType the type of statistic
     * @return the amount
     */
    int getStatistic(GameType gameType, StatisticType statisticType);

    /**
     * <<<<<<< HEAD
     * Get the currently open inventory of a player
     *
     * @return the currently open inventory
     */
    Optional<InventoryView> getOpenInventory();

    /**
     * Get the entity that the player is riding
     *
     * @return the entity that the player is riding
     */
    Optional<Entity> getVehicle();

    /**
     * Is the player currently in a vehicle?
     *
     * @return if the player is in a vehicle
     */
    boolean isInVehicle();

    /**
     * Set metadata of a player
     *
     * @param name     the name of the metadata
     * @param metadata the metadata to set
     */
    void setMetadata(String name, MetadataValue metadata);

    /**
     * Get the metadata from a player
     *
     * @param name the name of the metadata
     * @return the metadata matching the name
     */
    List<MetadataValue> getMetadata(String name);

    /**
     * Remove metadata from a player
     *
     * @param name the name of the metadata to remove
     */
    void removeMetadata(String name, Plugin plugin);

    /**
     * Get time player connected to server
     *
     * @return login time
     */
    long getJoinTime();

    /**
     * Get online time
     *
     * @return time spent online
     */
    long getOnlineTime();

    /**
     * Load achievements after join, set them here
     *
     * @param manager player's achievement manager
     */
    void setAchievementManager(CPlayerAchievementManager manager);

    /**
     * Set the player's level
     *
     * @param level the level to set to
     */
    void setLevel(int level);

    /**
     * Get the player's current level
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Get the player's current velocity vector
     *
     * @return the player's velocity as a vector
     */
    Vector getVelocity();

    /**
     * Set the player's current velocity
     *
     * @param vector the velocity as a vector
     */
    void setVelocity(Vector vector);

    /**
     * Set player's exp amount
     *
     * @param exp amount
     */
    void setExp(float exp);

    /**
     * Get player's exp amount
     *
     * @return exp
     */
    float getExp();

    /**
     * Get the current honor the player has
     *
     * @return the total honor the player has
     */
    int getHonor();

    /**
     * Give honor to a player
     *
     * @param amount the amount of honor to add
     */
    void giveHonor(int amount);

    /**
     * Remove honor from the player
     *
     * @param amount the amount to remove
     */
    void removeHonor(int amount);

    /**
     * Set the player's honor
     *
     * @param amount the amount to set to
     */
    void setHonor(int amount);

    /**
     * Get stored honor level to keep track of level changes
     *
     * @return previously stored honor level
     */
    int getPreviousHonorLevel();

    /**
     * Update stored honor level for level changes
     *
     * @param level the level to store
     */
    void setPreviousHonorLevel(int level);

    /**
     * Send player a map
     *
     * @param view the map view
     */
    void sendMap(MapView view);

    /**
     * Remove any potion effects of the type from the player
     *
     * @param type the potion type to remove
     */
    void removePotionEffect(PotionEffectType type);

    /**
     * Adds the given potion effect to the player
     *
     * @param effect the effect to add
     * @return whether the effect could be added
     */
    boolean addPotionEffect(PotionEffect effect);

    /**
     * Adds the given potion effect to the player
     *
     * @param effect the effect to add
     * @param force  whether conflicting effects should be removed
     * @return whether the effect could be added
     */
    boolean addPotionEffect(PotionEffect effect, boolean force);

    /**
     * Returns whether the player already has a potion effect of the given type
     *
     * @param type the type to check
     * @return whether the player has this potion effect type active on them
     */
    boolean hasPotionEffect(PotionEffectType type);

    /**
     * Attempts to add all of the given effects to the player
     *
     * @param effects the array of effects
     * @return whether all of the effects could be added
     */
    boolean addPotionEffects(Collection<PotionEffect> effects);

    /**
     * Returns all currently active potion effects on the player
     *
     * @return a collection of potion effects
     */
    Collection<PotionEffect> getActivePotionEffects();

    /**
     * Returns the active potion effect of the specified type.
     * If the effect is not present on the player then null will be returned.
     *
     * @param type the potion type to check
     * @return the effect active on this player, or null if not active.
     */
    PotionEffect getPotionEffect(PotionEffectType type);

    /**
     * Returns whether or not the player is inside a vehicle
     *
     * @return true if the player is inside a vehicle
     */
    boolean isInsideVehicle();

    /**
     * Get the player's current windowId, the id of the inventory they're viewing
     *
     * @return the windowId
     */
    int getWindowId();
}
