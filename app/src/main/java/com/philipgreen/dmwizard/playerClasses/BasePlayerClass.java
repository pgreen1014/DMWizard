package com.philipgreen.dmwizard.playerClasses;

import com.philipgreen.dmwizard.data.ArmorProficiencies;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.player.utils.Skills;
import com.philipgreen.dmwizard.data.WeaponProficiencies;

/**
 * Created by pgreen on 8/6/16.
 */
public abstract class BasePlayerClass {
    private static final String TAG = "BasePlayerClass";

    private int mClassLevel;

    private BaseStats[] mSavingThrowProficiencies;
    private Skills[] mProficientSkills;
    private WeaponProficiencies[] mWeaponProficiencies;
    private ArmorProficiencies[] mArmorProficiencies;

    private int mProficiencyBonus;
    private int mHitDie;


    public BasePlayerClass(int level, Skills[] proficientSkills) {
        this.mClassLevel = level;

        this.mHitDie  = setHitDie();
        this.mWeaponProficiencies = initWeaponProficiencies();
        this.mArmorProficiencies = initArmorProficiencies();
        this.mProficientSkills = proficientSkills;
        this.mSavingThrowProficiencies = setSavingThrowProficiencies();
    }

    /////////////////////////////
    //    ABSTRACT FUNCTIONS   //
    /////////////////////////////

    public abstract int setHitDie();
    public abstract BaseStats[] setSavingThrowProficiencies();
    public abstract WeaponProficiencies[] initWeaponProficiencies();
    public abstract ArmorProficiencies[] initArmorProficiencies();

    ///////////////////////////////////////////////////
    ///               GETTERS AND SETTERS           ///
    ///////////////////////////////////////////////////


    public int getClassLevel() {
        return mClassLevel;
    }

    public int getProficiencyBonus() {
        return mProficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        mProficiencyBonus = proficiencyBonus;
    }

    public int getHitDie() {
        return mHitDie;
    }

    public void setHitDie(int hitDie) {
        mHitDie = hitDie;
    }

    public Skills[] getProficientSkills() {
        return mProficientSkills;
    }

    public BaseStats[] getSavingThrowProficiencies() {
        return mSavingThrowProficiencies;
    }
}
