package be.isach.ultracosmetics.cosmetics.pets;

import be.isach.ultracosmetics.UltraCosmetics;
import be.isach.ultracosmetics.UltraCosmeticsData;
import be.isach.ultracosmetics.cosmetics.type.PetType;
import be.isach.ultracosmetics.player.UltraPlayer;
import be.isach.ultracosmetics.util.ItemFactory;
import be.isach.ultracosmetics.util.XMaterial;

/**
 * Represents an instance of a llama pet summoned by a player.
 *
 * @author RadBuilder
 * @since 07-02-2017
 */
public class PetLlama extends Pet {
    public PetLlama(UltraPlayer owner, UltraCosmetics ultraCosmetics) {
        super(owner, ultraCosmetics, PetType.getByName("llama"), ItemFactory.rename(XMaterial.WHITE_WOOL.parseItem(), UltraCosmeticsData.get().getItemNoPickupString()));
    }
}
