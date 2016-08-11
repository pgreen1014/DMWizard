package com.philipgreen.dmwizard.races;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.data.WeaponType;

import java.util.HashMap;

/**
 * Created by pgreen on 8/10/16.
 */
public class Dwarf extends BaseRaceClass {
    @Override
    public int initSpeed() {
        return 25;
    }

    @Override
    public WeaponType[] initWeaponProficiencies() {
        return new WeaponType[] {WeaponType.BATTLEAXE, WeaponType.HANDAXE, WeaponType.LIGHT_HAMMER, WeaponType.WARHAMMER};
    }

    @Override
    public Languages[] initLanguages() {
        return new Languages[] {Languages.COMMON, Languages.DWARVISH};
    }

    @Override
    public HashMap<BaseStats, Integer> initStatBonueses() {
        HashMap<BaseStats, Integer> statBonusMap = new HashMap<>();
        statBonusMap.put(BaseStats.CONSTITUTION, 2);
        return statBonusMap;
    }
}
