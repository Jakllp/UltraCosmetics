package be.isach.ultracosmetics.cosmetics.type;

import be.isach.ultracosmetics.UltraCosmeticsData;
import be.isach.ultracosmetics.config.MessageManager;
import be.isach.ultracosmetics.cosmetics.Category;
import be.isach.ultracosmetics.cosmetics.morphs.*;
import be.isach.ultracosmetics.util.ServerVersion;
import be.isach.ultracosmetics.util.XMaterial;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Morph types.
 *
 * @author iSach
 * @since 12-19-2015
 */
public class MorphType extends CosmeticType<Morph> {

    private final static List<MorphType> ENABLED = new ArrayList<>();
    private final static List<MorphType> VALUES = new ArrayList<>();

    public static List<MorphType> enabled() {
        return ENABLED;
    }

    public static List<MorphType> values() {
        return VALUES;
    }

    public static MorphType valueOf(String s) {
        for (MorphType morphType : VALUES) {
            if (morphType.getConfigName().equalsIgnoreCase(s)) return morphType;
        }
        return null;
    }

    public static void checkEnabled() {
        ENABLED.addAll(values().stream().filter(CosmeticType::isEnabled).collect(Collectors.toList()));
    }

    /**
     * Disguise Type of the morph.
     */
    private final DisguiseType disguiseType;

    private MorphType(String permission, String configName, XMaterial material, DisguiseType disguiseType, String defaultDesc, Class<? extends Morph> clazz, ServerVersion baseVersion) {
        super(Category.MORPHS, configName, permission, defaultDesc, material, clazz, baseVersion);
        this.disguiseType = disguiseType;

        VALUES.add(this);
    }

    /**
     * Get the skill message.
     *
     * @return The skill message of the morph.
     */
    public String getSkill() {
        return MessageManager.getMessage("Morphs." + getConfigName() + ".skill");
    }

    /**
     * Get the morph Disguise Type.
     *
     * @return
     */
    public DisguiseType getDisguiseType() {
        return disguiseType;
    }

    public static void register() {
        ServerVersion serverVersion = UltraCosmeticsData.get().getServerVersion();

        new MorphType("ultracosmetics.morphs.bat", "Bat", XMaterial.COAL, DisguiseType.BAT, "&7&oBecome a bat!", MorphBat.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.blaze", "Blaze", XMaterial.BLAZE_POWDER, DisguiseType.BLAZE, "&7&oIt might be hot on there..", MorphBlaze.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.chicken", "Chicken", XMaterial.EGG, DisguiseType.CHICKEN, "&7&oBecome a chicken!", MorphChicken.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.pig", "Pig", XMaterial.PORKCHOP, DisguiseType.PIG, "&7&oMust hold your carrot!", MorphPig.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.enderman", "Enderman", XMaterial.ENDER_PEARL, DisguiseType.ENDERMAN, "&7&oI go from there to\n" + "&7&othere, then you lost me..", MorphEnderman.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.slime", "Slime", XMaterial.SLIME_BALL, DisguiseType.SLIME, "&7&oSplat splat!", MorphSlime.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.creeper", "Creeper", XMaterial.GUNPOWDER, DisguiseType.CREEPER, "&7&oHey What'sssssss up?", MorphCreeper.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.snowman", "Snowman", XMaterial.SNOWBALL, DisguiseType.SNOWMAN, "&7&oBecome Olaf!", MorphSnowman.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.elderguardian", "ElderGuardian", XMaterial.PRISMARINE_CRYSTALS, DisguiseType.ELDER_GUARDIAN, "&7&oBecome an Elder Guardian!!", UltraCosmeticsData.get().getVersionManager().getMorphs().getElderGuardianClass(), ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.cow", "Cow", XMaterial.MILK_BUCKET, DisguiseType.COW, "&7&oMoooo!", MorphCow.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.mooshroom", "Mooshroom", XMaterial.RED_MUSHROOM, DisguiseType.MUSHROOM_COW, "&7&oMoooo!", MorphMooshroom.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.villager", "Villager", XMaterial.EMERALD, DisguiseType.VILLAGER, "&7&oHmmmmmmmmm", MorphVillager.class, ServerVersion.earliest());

        if (serverVersion.isAtLeast(ServerVersion.v1_12_R1)) {
            new MorphType("ultracosmetics.morphs.witch", "Witch", XMaterial.POISONOUS_POTATO, getDisguiseType("WITCH"), "&7&oMuahahahahaha", MorphWitch.class, ServerVersion.v1_12_R1);
            new MorphType("ultracosmetics.morphs.polarbear", "PolarBear", XMaterial.SNOW_BLOCK, getDisguiseType("POLAR_BEAR"), "&7&oI prefer cold areas", MorphPolarBear.class, ServerVersion.v1_12_R1);
            new MorphType("ultracosmetics.morphs.llama", "Llama", XMaterial.RED_WOOL, getDisguiseType("LLAMA"), "&7&oNeed me to carry anything?", MorphLlama.class, ServerVersion.v1_12_R1);
            new MorphType("ultracosmetics.morphs.parrot", "Parrot", XMaterial.COOKIE, getDisguiseType("PARROT"), "&7&oPolly want a cracker?", MorphParrot.class, ServerVersion.v1_12_R1);
        }

        new MorphType("ultracosmetics.morphs.sheep", "Sheep", XMaterial.WHITE_WOOL, DisguiseType.SHEEP, "&7&oBaaaa, baa", MorphSheep.class, ServerVersion.earliest());
        new MorphType("ultracosmetics.morphs.witherskeleton", "WitherSkeleton", XMaterial.WITHER_SKELETON_SKULL, DisguiseType.WITHER_SKELETON, "&7&oJust a regular skeleton..\n&7&obut from the Nether!", MorphWitherSkeleton.class, ServerVersion.earliest());
    }

    private static DisguiseType getDisguiseType(String type) {
        return DisguiseType.valueOf(type);
    }
}
