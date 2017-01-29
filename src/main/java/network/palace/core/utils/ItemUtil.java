package network.palace.core.utils;

import com.comphenix.protocol.reflect.MethodUtils;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Item util.
 */
public class ItemUtil implements Listener {

    private static final String UNABLE_TO_MOVE = "unableToMove";
    private static final String UNABLE_TO_DROP = "unableToDrop";

    /**
     * Make unable to move item stack.
     *
     * @param stack the stack
     * @return the item stack
     */
    public static ItemStack makeUnableToMove(ItemStack stack) {
        return setNBTForItemstack(stack, UNABLE_TO_MOVE);
    }

    /**
     * Make unable to drop item stack.
     *
     * @param stack the stack
     * @return the item stack
     */
    public static ItemStack makeUnableToDrop(ItemStack stack) {
        return setNBTForItemstack(stack, UNABLE_TO_DROP);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    protected void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (hasNBT(event.getCurrentItem(), UNABLE_TO_MOVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    protected void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop() == null) return;
        if (event.getItemDrop().getItemStack() == null) return;
        if (hasNBT(event.getItemDrop().getItemStack(), UNABLE_TO_DROP)) {
            event.setCancelled(true);
        }
    }

    /**
     * Has nbt boolean.
     *
     * @param stack the stack
     * @param tag   the tag
     * @return the boolean
     */
    public static boolean hasNBT(ItemStack stack, String tag) {
        if (stack.getType() == Material.AIR) return false;
        ItemStack craftStack = stack;
        if (!MinecraftReflection.isCraftItemStack(stack)) {
            craftStack = MinecraftReflection.getBukkitItemStack(stack);
        }
        NbtCompound nbt = (NbtCompound) NbtFactory.fromItemTag(craftStack);
        return nbt.containsKey(tag) && nbt.getInteger(tag) == 1;
    }

    /**
     * Sets nbt for itemstack.
     *
     * @param stack the stack
     * @param tag   the tag
     * @return the nbt for itemstack
     */
    public static ItemStack setNBTForItemstack(ItemStack stack, String tag) {
        ItemStack craftStack = stack;
        if (!MinecraftReflection.isCraftItemStack(stack)) {
            craftStack = MinecraftReflection.getBukkitItemStack(stack);
        }
        NbtCompound nbt = (NbtCompound) NbtFactory.fromItemTag(craftStack);
        nbt.put(tag, 1);
        NbtFactory.setItemTag(craftStack, nbt);
        return craftStack;
    }

    /**
     * Get friendly nbt for itemstack.
     *
     * @param stack the stack
     * @return the nbt for itemstack
     */
    public static String getFriendlyNBT(ItemStack stack) {
        Object minecraftItemstack = MinecraftReflection.getMinecraftItemStack(stack);
        Class nbtCompoundClass = MinecraftReflection.getNBTCompoundClass();
        String nbt = "";
        try {
            nbt = MethodUtils.invokeMethod(minecraftItemstack, "save", nbtCompoundClass.newInstance()).toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return nbt;
    }

    /**
     * Create item stack.
     *
     * @param type the type
     * @return the item stack
     */
    public static ItemStack create(Material type) {
        return create(type, 1);
    }

    /**
     * Create item stack.
     *
     * @param type   the type
     * @param amount the amount
     * @return the item stack
     */
    public static ItemStack create(Material type, int amount) {
        return create(type, amount, (byte) 0);
    }

    /**
     * Create item stack.
     *
     * @param type   the type
     * @param amount the amount
     * @param data   the data
     * @return the item stack
     */
    public static ItemStack create(Material type, int amount, byte data) {
        return new ItemStack(type, amount, data);
    }

    /**
     * Create item stack.
     *
     * @param type the type
     * @param name the name
     * @return the item stack
     */
    public static ItemStack create(Material type, String name) {
        return create(type, name, new ArrayList<>());
    }

    /**
     * Create item stack.
     *
     * @param type the type
     * @param name the name
     * @param lore the lore
     * @return the item stack
     */
    public static ItemStack create(Material type, String name, List<String> lore) {
        return create(type, 1, name, lore);
    }

    /**
     * Create item stack.
     *
     * @param type   the type
     * @param amount the amount
     * @param name   the name
     * @param lore   the lore
     * @return the item stack
     */
    public static ItemStack create(Material type, int amount, String name, List<String> lore) {
        return create(type, amount, (byte) 0, name, lore);
    }

    /**
     * Create item stack.
     *
     * @param type   the type
     * @param amount the amount
     * @param data   the data
     * @param name   the name
     * @param lore   the lore
     * @return the item stack
     */
    public static ItemStack create(Material type, int amount, byte data, String name, List<String> lore) {
        ItemStack item = create(type, amount, data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Create item stack.
     *
     * @param owner the owner
     * @return the item stack
     */
    public static ItemStack create(String owner) {
        return create(owner, owner, new ArrayList<>());
    }

    /**
     * Create item stack.
     *
     * @param owner       the owner
     * @param displayName the display name
     * @return the item stack
     */
    public static ItemStack create(String owner, String displayName) {
        return create(owner, displayName, new ArrayList<>());
    }

    /**
     * Create skull item stack.
     *
     * @param owner       the owner
     * @param displayName the display name
     * @param lore        the lore
     * @return the item stack
     */
    public static ItemStack create(String owner, String displayName, List<String> lore) {
        ItemStack item = create(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta sm = (SkullMeta) item.getItemMeta();
        sm.setOwner(owner);
        sm.setDisplayName(displayName);
        sm.setLore(lore);
        item.setItemMeta(sm);
        return item;
    }
}
