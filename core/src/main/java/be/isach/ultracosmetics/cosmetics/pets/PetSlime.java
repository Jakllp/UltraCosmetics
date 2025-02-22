package be.isach.ultracosmetics.cosmetics.pets;

import org.bukkit.entity.Slime;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.UltraCosmeticsData;
import be.isach.ultracosmetics.cosmetics.type.PetType;
import be.isach.ultracosmetics.player.UltraPlayer;
import be.isach.ultracosmetics.util.ItemFactory;
import be.isach.ultracosmetics.util.XMaterial;

/**
 * Represents an instance of a slime pet summoned by a player.
 *
 * @author datatags
 * @since 18-01-2021
 */

public class PetSlime extends Pet {
    public PetSlime(UltraPlayer owner, UltraCosmetics ultraCosmetics) {
        super(owner, ultraCosmetics, PetType.getByName("slime"), ItemFactory.create(XMaterial.SLIME_BALL, UltraCosmeticsData.get().getItemNoPickupString()));
    }

    @Override
    public void onEquip() {
        super.onEquip();
        ((Slime)entity).setSize(1);
    }
}
