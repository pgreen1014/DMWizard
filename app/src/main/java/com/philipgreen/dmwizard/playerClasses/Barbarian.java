package com.philipgreen.dmwizard.playerClasses;

import com.philipgreen.dmwizard.data.BaseStats;

/**
 * Created by pgreen on 8/6/16.
 */
public class Barbarian extends BasePlayerClass{
    // Proficiency bonus modifiers by level. correct modifier = level - 1
    private static final int[] PROFICIENCY_BONUS_BY_LEVEL = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};
    // Number of rages by level. Level 20 has unlimited rages currently represented by 9999
    private static final int[] NUMBER_OF_RAGES_BY_LEVEL = {2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 9999};
    private static final int [] RAGE_DAMAGE_BY_LEVEL = {2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4};

    private int mDailyRageNumber; // total number of rages per day
    private int mDailyRageUsed = 0; // tracks number of rages used today
    private int mRageDamageBonus;

    public Barbarian(int str, int dex, int con, int intel, int wis, int cha, int level) {
        super(str, dex, con, intel, wis, cha, level);

        this.mDailyRageNumber = NUMBER_OF_RAGES_BY_LEVEL[level];
        this.mRageDamageBonus = NUMBER_OF_RAGES_BY_LEVEL[level];
    }

    @Override
    public int setHitDie() {
        return 12;
    }

    @Override
    public BaseStats[] setSavingThrowProficiencies() {
        BaseStats[] proficiencies = {BaseStats.STRENGTH, BaseStats.CONSTITUTION};
        return proficiencies;
    }

    @Override
    public int initializeProficiencyBonus(int level) {
        return PROFICIENCY_BONUS_BY_LEVEL[level];
    }

    private enum Features {
        RAGE, UNARMORED_DEFENSE, RECKLESS_ATTACK, DANGER_SENSE, PRIMAL_PATH, ABILITY_SOCRE_IMPROVEMENT,
        EXTRA_ATTACK, FAST_MOVEMENT, PATH_FEATURE, FERAL_INSTINCT, BRUTAL_CRITICAL_ONE_DICE, RELENTLESS_RAGE,
        BRUTAL_CRITICAL_TWO_DICE, PERSISTENT_RAGE, BRUTAL_CRITICAL_THREE_DICE, INDOMITABLE_MIGHT, PRIMAL_CHAMPIONe
    }
}
