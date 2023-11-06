package gui.library;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Gui {

    private final static List<Gui> guis = new ArrayList<>();

    private final String name;
    private final String title;
    private final int rows;
    private final Inventory inventory;

    public Gui(String name, String title, int rows){
        if (name == null || title == null || rows < 1){
            throw new IllegalStateException("An error occurred!");
        }
        this.name = name;
        this.title = title;
        this.rows = rows;
        int finalRows = (9 * rows);
        this.inventory = Bukkit.createInventory(null, finalRows, ChatColor.translateAlternateColorCodes('&', title));
        Gui.guis.add(this);
    }

    public abstract void setContent();

    public String getTitle() {
        return title;
    }

    public void setItem(ItemStack itemStack, int index){
        this.inventory.setItem(index, itemStack);
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected void open(Player player){
        player.openInventory(inventory);
    }

    public static Gui getGuiByName(String var){
        for (Gui gui : Gui.guis){
            if (gui.getName().equalsIgnoreCase(var)){
                return gui;
            }
        }
        return null;
    }
}
