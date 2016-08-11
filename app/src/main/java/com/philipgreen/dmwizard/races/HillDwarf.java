package com.philipgreen.dmwizard.races;

import com.philipgreen.dmwizard.data.BaseStats;

import java.util.HashMap;

/**
 * Created by pgreen on 8/10/16.
 */
public class HillDwarf extends Dwarf {

    public HillDwarf() {

    }

    @Override
    public HashMap<BaseStats, Integer> initStatBonuses() {
        HashMap<BaseStats, Integer> statBonusMap = new HashMap<>();
        statBonusMap.put(BaseStats.CONSTITUTION, 2);
        statBonusMap.put(BaseStats.WISDOM, 1);
        return statBonusMap;
    }

    // TODO add dwarven toughness
}
