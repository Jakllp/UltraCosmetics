package be.isach.ultracosmetics.cosmetics.suits;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.cosmetics.type.SuitType;
import be.isach.ultracosmetics.player.UltraPlayer;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Represents an instance of a rave suit summoned by a player.
 *
 * @author iSach
 * @since 12-20-2015
 */
public class SuitRave extends Suit {
    private int[] colors = new int[]{255, 0, 0};

    public SuitRave(UltraPlayer owner, SuitType suitType, UltraCosmetics ultraCosmetics) {
        super(owner, suitType, ultraCosmetics);
    }

    @Override
    public void onUpdate() {
        if (colors[0] == 255 && colors[1] < 255 && colors[2] == 0)
            colors[1] += 15;
        if (colors[1] == 255 && colors[0] > 0 && colors[2] == 0)
            colors[0] -= 15;
        if (colors[1] == 255 && colors[2] < 255 && colors[0] == 0)
            colors[2] += 15;
        if (colors[2] == 255 && colors[1] > 0 && colors[0] == 0)
            colors[1] -= 15;
        if (colors[2] == 255 && colors[0] < 255 && colors[1] == 0)
            colors[0] += 15;
        if (colors[0] == 255 && colors[2] > 0 && colors[1] == 0)
            colors[2] -= 15;

        refresh();
    }

    private void refresh() {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        itemMeta.setColor(Color.fromRGB(colors[0], colors[1], colors[2]));
        itemStack.setItemMeta(itemMeta);
        setArmorItem(itemStack);
    }
}
