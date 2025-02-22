package be.isach.ultracosmetics.cosmetics.pets;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.UltraCosmeticsData;
import be.isach.ultracosmetics.cosmetics.type.PetType;
import be.isach.ultracosmetics.player.UltraPlayer;
import be.isach.ultracosmetics.util.ItemFactory;
import be.isach.ultracosmetics.util.XMaterial;

/**
 * Represents an instance of a silverfish pet summoned by a player.
 *
 * @author Chris6ix
 * @since 19-01-2021
 */
public class PetSilverfish extends Pet {
    public PetSilverfish(UltraPlayer owner, UltraCosmetics ultraCosmetics) {
        super(owner, ultraCosmetics, PetType.getByName("silverfish"), ItemFactory.create(XMaterial.GRAY_DYE, UltraCosmeticsData.get().getItemNoPickupString()));
    }
}
