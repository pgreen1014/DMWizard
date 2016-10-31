package com.philipgreen.dmwizard.player.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pgreen on 10/29/16.
 *
 * A static utility that holds on to the mapping between ability stats and their corresponding modifier.
 * Access an ability modifier by calling getModifier(int abilityScore).
 */

public class AbilityModifierManager {
    private static final Map<Integer, Integer> ABILITY_MODIFIER_MAP;
    static {
        Map<Integer, Integer>tempMap = new HashMap<Integer, Integer>();
        tempMap.put(1, -5);
        tempMap.put(2, -4);
        tempMap.put(3, -4);
        tempMap.put(4, -3);
        tempMap.put(5, -3);
        tempMap.put(6, -2);
        tempMap.put(7, -2);
        tempMap.put(8, -1);
        tempMap.put(9, -1);
        tempMap.put(10, 0);
        tempMap.put(11, 0);
        tempMap.put(12, 1);
        tempMap.put(13, 1);
        tempMap.put(14, 2);
        tempMap.put(15, 2);
        tempMap.put(16, 3);
        tempMap.put(17, 3);
        tempMap.put(18, 4);
        tempMap.put(19, 4);
        tempMap.put(20, 5);
        ABILITY_MODIFIER_MAP = Collections.unmodifiableMap(tempMap);
    }

    public static int getModifier(int abilityScore) {
        return ABILITY_MODIFIER_MAP.get(abilityScore);
    }
}
