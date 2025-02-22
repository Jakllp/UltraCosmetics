package be.isach.ultracosmetics.manager;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.UltraCosmeticsData;
import be.isach.ultracosmetics.config.MessageManager;
import be.isach.ultracosmetics.config.SettingsManager;
import be.isach.ultracosmetics.player.UltraPlayer;
import be.isach.ultracosmetics.treasurechests.TreasureChest;
import be.isach.ultracosmetics.treasurechests.TreasureChestDesign;
import be.isach.ultracosmetics.util.Area;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Sacha on 11/11/15.
 */
public class TreasureChestManager implements Listener {

    private static Random random = new Random();

    private TreasureChestManager() {}

    private static String getRandomDesign() {
        Set<String> set = UltraCosmeticsData.get().getPlugin().getConfig().getConfigurationSection("TreasureChests.Designs").getKeys(false);
        List<String> list = new ArrayList<>();
        list.addAll(set);
        return list.get(random.nextInt(set.size()));
    }

    public static void tryOpenChest(Player player) {
        if (!SettingsManager.getConfig().getBoolean("TreasureChests.Location.Enabled")) {
            tryOpenChest(player, null);
            return;
        }
        ConfigurationSection location = SettingsManager.getConfig().getConfigurationSection("TreasureChests.Location");
        // just modify a copy of the player's original location so we preserve yaw and pitch
        Location treasureChestLocation = player.getLocation().clone();
        String worldName = location.getString("World", "none");
        if (!worldName.equals("none")) {
            World world = Bukkit.getWorld(location.getString("World"));
            if (world == null) {
                player.sendMessage(ChatColor.RED + "Invalid world set in config.yml!");
            } else {
                treasureChestLocation.setWorld(world);
            }
        }
        treasureChestLocation.setX(location.getInt("X", 0) + 0.5);
        // add 0.5 to the Y too so it's less likely the player gets stuck in the ground
        treasureChestLocation.setY(location.getInt("Y", 63) + 0.5);
        treasureChestLocation.setZ(location.getInt("Z", 0) + 0.5);
        tryOpenChest(player, treasureChestLocation);
    }

    public static void tryOpenChest(Player player, Location tpTo) {
        UltraCosmetics plugin = UltraCosmeticsData.get().getPlugin();

        if (!plugin.areChestsAllowedInRegion(player)) {
            player.closeInventory();
            player.sendMessage(MessageManager.getMessage("Chest-Location.Region-Disabled"));
            return;
        }

        UltraPlayer ultraPlayer = plugin.getPlayerManager().getUltraPlayer(player);
        if (ultraPlayer.getKeys() < 1) {
            player.closeInventory();
            ultraPlayer.openKeyPurchaseMenu();
            return;
        }

        Area area = new Area(player.getLocation().add(-2, 0, -2), player.getLocation().add(2, 1, 2));

        if (!area.isEmptyExcept(player.getLocation().getBlock().getLocation())) {
            player.sendMessage(MessageManager.getMessage("Chest-Location.Not-Enough-Space"));
            return;
        }

        for (Entity ent : player.getNearbyEntities(5, 5, 5)) {
            if (!(ent instanceof Player)) continue;
            Player loopPlayer = (Player) ent;
            // check Bukkit.getPlayer(UUID) in case loopPlayer is really a player NPC
            if (Bukkit.getPlayer(loopPlayer.getUniqueId()) != null 
                    && plugin.getPlayerManager().getUltraPlayer(loopPlayer).getCurrentTreasureChest() != null) {
                player.closeInventory();
                player.sendMessage(MessageManager.getMessage("Chest-Location.Too-Close"));
                return;
            }
        }

        Block block = player.getLocation().getBlock();
        if (block.getRelative(BlockFace.UP).getType() != Material.AIR
                || block.getRelative(BlockFace.DOWN).getType() == Material.AIR) {
            player.sendMessage(MessageManager.getMessage("Gadgets.Rocket.Not-On-Ground"));
            return;
        }
        ultraPlayer.removeKey();
        String designPath = getRandomDesign();
        player.closeInventory();
        Location preLoc = null;
        if (tpTo != null) {
            preLoc = player.getLocation();
            player.teleport(tpTo);
        }
        new TreasureChest(player.getUniqueId(), new TreasureChestDesign(designPath), preLoc);
    }

}