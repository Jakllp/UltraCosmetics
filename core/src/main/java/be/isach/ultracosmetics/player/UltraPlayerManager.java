package be.isach.ultracosmetics.player;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.config.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manager for UltraPlayers.
 *
 * @author iSach
 * @since 12-16-2015
 */
public class UltraPlayerManager {

    private Map<UUID, UltraPlayer> playerCache;
    private UltraCosmetics ultraCosmetics;

    public UltraPlayerManager(UltraCosmetics ultraCosmetics) {
        this.playerCache = new ConcurrentHashMap<>();
        this.ultraCosmetics = ultraCosmetics;
    }

    public UltraPlayer getUltraPlayer(Player player) {
        if (player == null) return null;

        return playerCache.get(player.getUniqueId());
    }

    public UltraPlayer create(Player player) {
        UltraPlayer customPlayer = new UltraPlayer(player.getUniqueId(), ultraCosmetics);
        playerCache.put(player.getUniqueId(), customPlayer);
        return customPlayer;
    }

    public boolean remove(Player player) {
        return playerCache.remove(player.getUniqueId()) != null;
    }

    public Collection<UltraPlayer> getUltraPlayers() {
        return playerCache.values();
    }

    /**
     * Initialize players.
     */
    public void initPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            create(p);
            if (SettingsManager.getConfig().getBoolean("Menu-Item.Give-On-Join") && SettingsManager.isAllowedWorld(p.getWorld())) {
                getUltraPlayer(p).giveMenuItem();
            }
        }
    }

    public void dispose() {
        Collection<UltraPlayer> set = playerCache.values();
        for (UltraPlayer cp : set) {
            cp.setQuitting(true);
            if (cp.getCurrentTreasureChest() != null) {
                cp.getCurrentTreasureChest().forceOpen(0);
            }
            cp.saveCosmeticsProfile();
            cp.clear();
            cp.removeMenuItem();
        }

        playerCache.clear();
        playerCache = null;
    }
}
