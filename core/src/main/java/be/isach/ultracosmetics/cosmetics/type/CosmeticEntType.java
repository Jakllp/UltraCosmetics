package be.isach.ultracosmetics.cosmetics.type;

import be.isach.ultracosmetics.cosmetics.Category;
import be.isach.ultracosmetics.cosmetics.Cosmetic;
import be.isach.ultracosmetics.util.ServerVersion;
import be.isach.ultracosmetics.util.XMaterial;
import org.bukkit.entity.EntityType;

/**
 * Represents a Cosmetic Type with a Material, a Data, and an Entity Type.
 *
 * @author iSach
 * @since 08-04-2016
 */
public abstract class CosmeticEntType<T extends Cosmetic<?>> extends CosmeticType<T> {

    private final EntityType entityType;

    public CosmeticEntType(Category category, String configName, String permission, String description, XMaterial material, EntityType entityType, Class<? extends T> clazz, ServerVersion baseVersion) {
        super(category, configName, permission, description, material, clazz, baseVersion);
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
