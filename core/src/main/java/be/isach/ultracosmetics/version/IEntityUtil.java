package be.isach.ultracosmetics.version;

import be.isach.ultracosmetics.treasurechests.TreasureChestDesign;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by Sacha on 14/03/16.
 */
public interface IEntityUtil {

    void resetWitherSize(Wither wither);

    void sendBlizzard(final Player player, Location loc, boolean affectPlayers, Vector v);

    void clearBlizzard(final Player player);

    void clearPathfinders(Entity entity);

    void makePanic(Entity entity);

    void sendDestroyPacket(Player player, Entity entity);

    void move(Creature creature, Location location);

    void moveDragon(Player player, Vector vector, Entity entity);

    void setClimb(Entity entity);

    void moveShip(Player player, Entity entity, Vector vector);

    void playChestAnimation(Block b, boolean open, TreasureChestDesign design);

    Entity spawnItem(ItemStack itemStack, Location blockLocation);

    boolean isSameInventory(Inventory first, Inventory second);

    void follow(Entity toFollow, Entity follower);

    void chickenFall(Player player);

    void sendTeleportPacket(Player player, Entity entity);

    boolean isMoving(Player entity);
}
