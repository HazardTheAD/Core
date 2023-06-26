package network.palace.core.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import network.palace.core.Core;
import org.bukkit.ChatColor;

import java.util.Map;

@AllArgsConstructor
public enum Rank {
    OWNER("Owner", ChatColor.RED + "Owner ", ChatColor.RED, ChatColor.YELLOW, true, 13),
    MANAGER("Manager", ChatColor.GOLD + "Manager ", ChatColor.GOLD, ChatColor.YELLOW, true, 13),
    LEAD("Lead", ChatColor.GREEN + "Lead ", ChatColor.DARK_GREEN, ChatColor.GREEN, true, 13),
    DEVELOPER("Developer", ChatColor.BLUE + "Developer ", ChatColor.BLUE, ChatColor.AQUA, true, 13),
    COORDINATOR("Coordinator", ChatColor.BLUE + "Coordinator ", ChatColor.BLUE, ChatColor.AQUA, true, 12),
    BUILDER("Imagineer", ChatColor.AQUA + "Imagineer ", ChatColor.AQUA, ChatColor.WHITE, true, 11),
    IMAGINEER("Imagineer", ChatColor.AQUA + "Imagineer ", ChatColor.AQUA, ChatColor.WHITE, true, 11),
    MEDIA("Cast Member", ChatColor.AQUA + "CM ", ChatColor.AQUA, ChatColor.WHITE, true, 11),
    CM("Cast Member", ChatColor.GOLD + "CM ", ChatColor.GREEN, ChatColor.GREEN, true, 11),
    TRAINEETECH("Trainee", ChatColor.DARK_GREEN + "Trainee ", ChatColor.AQUA, ChatColor.WHITE, false, 10),
    TRAINEEBUILD("Trainee", ChatColor.DARK_GREEN + "Trainee ", ChatColor.AQUA, ChatColor.WHITE, false, 10),
    TRAINEE("Trainee", ChatColor.DARK_GREEN + "Trainee ", ChatColor.AQUA, ChatColor.WHITE, false, 9),
    CHARACTER("Character", ChatColor.BLUE + "Character ", ChatColor.BLUE, ChatColor.BLUE, false, 8),
    VIP("VIP", ChatColor.DARK_PURPLE + "VIP ", ChatColor.DARK_PURPLE, ChatColor.WHITE, false, 7),
    CLUB("Club 33", ChatColor.DARK_RED + "C33 ", ChatColor.DARK_RED, ChatColor.WHITE, false, 5),
    DVC("DVC", ChatColor.AQUA + "DVC ", ChatColor.GOLD, ChatColor.WHITE, false, 4),
    GUEST("Guest", ChatColor.GRAY + "", ChatColor.GRAY, ChatColor.GRAY, false, 1);

    private static final char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Getter private String name;
    @Getter private String scoreboardName;
    @Getter private ChatColor tagColor;
    @Getter private ChatColor chatColor;
    @Getter private boolean isOp;
    @Getter private int rankId;

    public static Rank fromString(String name) {
        if (name == null) return GUEST;
        if (name.equalsIgnoreCase("admin")) return MANAGER;
        String rankName = name.replaceAll(" ", "");

        for (Rank rank : Rank.values()) {
            if (rank.getDBName().equalsIgnoreCase(rankName)) return rank;
        }
        return GUEST;
    }

    @Deprecated
    public String getSqlName() {
        return getDBName();
    }

    public String getDBName() {
        String s;
        switch (this) {
            case TRAINEEBUILD:
            case TRAINEETECH:
                s = name().toLowerCase();
                break;
            default:
                s = name.toLowerCase().replaceAll(" ", "");
        }
        return s;
    }

    @Deprecated
    public String getNameWithBrackets() {
        return getFormattedName();
    }

    /**
     * Get the formatted name of a rank
     *
     * @return the rank name with any additional formatting that should exist
     */
    public String getFormattedName() {
        return getTagColor() + getName();
    }

    public String getFormattedScoreboardName() {
        return getTagColor() + getScoreboardName();
    }

    /**
     * Get the permissions that belong to the rank
     *
     * @return the permissions, and the status of the permission
     */
    public Map<String, Boolean> getPermissions() {
        return Core.getPermissionManager().getPermissions(this);
    }

    public String getScoreboardName() {
        int pos = ordinal();
        if (pos < 0 || pos >= alphabet.length) return "";
        return String.valueOf(alphabet[pos] + getName());
    }

    public String getScoreboardTeamName() {
        return getScoreboardPrefix() + getDBName().substring(0, Math.min(getDBName().length(), 10));
    }

    public String getScoreboardPrefix() {
        int pos = ordinal();
        if (pos < 0 || pos >= alphabet.length) return "";
        return String.valueOf(alphabet[pos]);
    }

    public String getShortName() {
        return getName().substring(0, 3);
    }
}
