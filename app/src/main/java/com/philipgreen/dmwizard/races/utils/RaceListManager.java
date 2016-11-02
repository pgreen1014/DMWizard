package com.philipgreen.dmwizard.races.utils;

import java.util.HashMap;

/**
 * Created by pgreen on 11/1/16.
 */

public class RaceListManager {

    private static HashMap<RaceListEnum, SubRaceListEnum[]> RACE_LIST_MAP = new HashMap<>();
    static {
        RACE_LIST_MAP.put(RaceListEnum.DWARF,
                new SubRaceListEnum[] {SubRaceListEnum.HILL_DWARF, SubRaceListEnum.MOUNTAIN_DWARF});

        RACE_LIST_MAP.put(RaceListEnum.ELF,
                new SubRaceListEnum[] {SubRaceListEnum.HIGH_ELF, SubRaceListEnum.DARK_ELF, SubRaceListEnum.WOOD_ELF});

        RACE_LIST_MAP.put(RaceListEnum.HALFLING, null);

        RACE_LIST_MAP.put(RaceListEnum.HUMAN, null);

        RACE_LIST_MAP.put(RaceListEnum.GNOME,
                new SubRaceListEnum[] {SubRaceListEnum.FOREST_GNOME, SubRaceListEnum.ROCK_GNOME});

        RACE_LIST_MAP.put(RaceListEnum.HALF_ELF, null);

        RACE_LIST_MAP.put(RaceListEnum.HALF_ORC, null);

        RACE_LIST_MAP.put(RaceListEnum.TIEFLING, null);

        RACE_LIST_MAP.put(RaceListEnum.DRAGONBORN,
                new SubRaceListEnum[] {SubRaceListEnum.BLACK_DRAGONBORN, SubRaceListEnum.BLUE_DRAGONBORN,
                        SubRaceListEnum.BRASS_DRAGONBORN, SubRaceListEnum.BRONZE_DRAGONBORN, SubRaceListEnum.COPPER_DRAGONBORN,
                        SubRaceListEnum.GOLD_DRAGONBORN, SubRaceListEnum.GREEN_DRAGONBORN, SubRaceListEnum.RED_DRAGONBORN,
                        SubRaceListEnum.SILVER_DRAGONBORN, SubRaceListEnum.WHITE_DRAGONBORN});
    }

    public static SubRaceListEnum[] getSubraceList(RaceListEnum race) {
        return RACE_LIST_MAP.get(race);
    }

}
