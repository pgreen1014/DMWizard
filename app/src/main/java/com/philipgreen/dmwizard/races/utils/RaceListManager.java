package com.philipgreen.dmwizard.races.utils;

import java.util.ArrayList;
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

    private static SubRaceListEnum[] getSubraceList(String race) {

        RaceListEnum convertedRace = convertReadableStringtoEnum(race);

        return RACE_LIST_MAP.get(convertedRace);
    }

    public static ArrayList<String> getSubRaceListAsString(String race) {
        ArrayList<String> subRaceList = new ArrayList<>();

        SubRaceListEnum[] subRaceListValues = getSubraceList(race);
        if (subRaceListValues != null) {

            for (SubRaceListEnum subRace : subRaceListValues) {
                String subRaceItem = convertEnumToReadableString(subRace);
                subRaceList.add(subRaceItem);
            }

        }

        return subRaceList;
    }

    public static ArrayList<String> getRaceListForUIPresentation() {
        ArrayList<String> raceList = new ArrayList<>();

        for (RaceListEnum race : RaceListEnum.values()) {
            String raceItem = convertEnumToReadableString(race);
            raceList.add(raceItem);
        }

        return raceList;
    }

    private static String replaceUnderscoresWithSpaces(String item) {
        String[] splitString = item.split("_");

        String stringWithSpaces = "";
        for (int i=0; i < splitString.length; i++) {

            // The first item in the array should not have a space before it
            if (i == 0) {
                stringWithSpaces += splitString[i];
            } else {
                stringWithSpaces += " " + splitString[i];
            }
        }

        return stringWithSpaces;
    }

    private static String capitalizeFirstLetters(String item) {
        String[] wordsToCapitalize = item.split(" ");

        String capitalizedWords = "";
        for (int i=0; i < wordsToCapitalize.length; i++) {
            String lowercaseWord = wordsToCapitalize[i].toLowerCase();

            String firstLetter = lowercaseWord.substring(0,1);
            String remainingLetters = lowercaseWord.substring(1);

            String capitalizedWord = firstLetter.toUpperCase() + remainingLetters;

            // The first item in the array should not have a space before it
            if (i == 0) {
                capitalizedWords += capitalizedWord;
            } else {
                capitalizedWords += " " + capitalizedWord;
            }
        }

        return capitalizedWords;
    }

    private static String convertEnumToReadableString(RaceListEnum race) {
        String raceToConvert = race.toString();

        raceToConvert = replaceUnderscoresWithSpaces(raceToConvert);
        raceToConvert = capitalizeFirstLetters(raceToConvert);

        return raceToConvert;
    }

    private static String convertEnumToReadableString(SubRaceListEnum race) {
        String raceToConvert = race.toString();

        raceToConvert = replaceUnderscoresWithSpaces(raceToConvert);
        raceToConvert = capitalizeFirstLetters(raceToConvert);

        return raceToConvert;
    }

    private static RaceListEnum convertReadableStringtoEnum(String race) throws IllegalArgumentException {

        for(RaceListEnum raceItem : RaceListEnum.values()) {

            if (convertEnumToReadableString(raceItem).equals(race)) {
                return raceItem;
            }

        }

        throw new IllegalArgumentException(race + " does not correspond to a predefined RaceListEnum. " +
                "Make sure string arg was generated using RaceListManager.convertEnumToReadableString");
    }

}
