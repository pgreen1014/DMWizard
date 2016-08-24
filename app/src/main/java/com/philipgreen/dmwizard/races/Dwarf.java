package com.philipgreen.dmwizard.races;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.data.Weapons;

import java.util.HashMap;

/**
 * Created by pgreen on 8/10/16.
 */
public class Dwarf extends BaseRaceClass {

    public Dwarf() {

    }

    @Override
    public int initSpeed() {
        return 25;
    }

    @Override
    public Weapons[] initWeaponProficiencies() {
        return new Weapons[] {Weapons.BATTLEAXE, Weapons.HANDAXE, Weapons.LIGHT_HAMMER, Weapons.WARHAMMER};
    }

    @Override
    public Languages[] initLanguages() {
        return new Languages[] {Languages.COMMON, Languages.DWARVISH};
    }

    @Override
    public HashMap<BaseStats, Integer> initStatBonuses() {
        HashMap<BaseStats, Integer> statBonusMap = new HashMap<>();
        statBonusMap.put(BaseStats.CONSTITUTION, 2);
        return statBonusMap;
    }
}
