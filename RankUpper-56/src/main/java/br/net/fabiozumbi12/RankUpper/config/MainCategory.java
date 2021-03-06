package br.net.fabiozumbi12.RankUpper.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class MainCategory {

    public MainCategory(){
        defaultGroups();
    }

    @Setting
    public Database database = new Database();

    @ConfigSerializable
    public static class Database{

        @Setting(comment="The default uri is: \"jdbc:h2:%s/playerstats.db\"\n" +
                "%s will be replaced by rankupper path.")
        public String uri = "jdbc:h2:%s/playerstats.db";

        @Setting
        public String prefix = "ru_";
    }

    @Setting(value="afk-support", comment = "Stop counting time when a player is AFK? (Using Nucleus' API only!)\n" +
            "Setting this true without Nucleus (0.23.1+) installed will result in player's time not being counted.")
    public boolean afk_support = false;

    @Setting(comment="Available languages: EN-US, PT-BR")
    public String language = "EN-US";

    @Setting(value = "date-format", comment="Date format to save data info of players.")
    public String date_format = "dd/MM/yyyy";

    @Setting(value = "debug-messages", comment="Enable debug messages?")
    public boolean debug_messages = false;

    @Setting(value = "database-save-interval", comment="Save to database every X minutes.")
    public int database_save_interval = 5;

    @Setting(value = "update-player-time-minutes", comment="Interval to add online player times.")
    public int update_player_time_minutes = 5;

    @Setting(value = "use-uuids-instead-names", comment="Use uuids to store players stats on playerstats.conf?")
    public boolean use_uuids_instead_names = true;

    @Setting(value = "exclude-groups")
    public List<String> exclude_groups = Arrays.asList("staff_group", "donor_group");

    @Setting(value = "ranked-groups", comment ="IMPORTANT: Change from \"default\" to exact group name the player need to is in to be promoted to next group")
    public Map<String, RankedGroupsCategory> ranked_groups = new HashMap<>();

    private void defaultGroups(){
        RankedGroupsCategory rgc = new RankedGroupsCategory(Arrays.asList(
                "lp user {player} parent unset {oldgroup}",
                "lp user {player} parent set {newgroup}",
                "xp 50L {player}"),
                50,
                "&a>> The player &6{player} &ahas played for &6{time} &aand now is rank {newgroup} of server.",
                120 ,
                1000,
                "member");
        rgc.minecraft_statistic.put("MOB_KILLS", 100L);
        ranked_groups.put("group-example", rgc);
    }
}
