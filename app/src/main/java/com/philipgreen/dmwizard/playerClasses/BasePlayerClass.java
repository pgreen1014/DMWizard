package com.philipgreen.dmwizard.playerClasses;

import android.util.Log;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.dice.Dice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pgreen on 8/6/16.
 */
public abstract class BasePlayerClass {
    private static final String TAG = "BasePlayerClass";

    private int mClassLevel;

    private int mStrength;
    private int mDexterity;
    private int mConstitution;
    private int mIntelligence;
    private int mWisdom;
    private int mCharisma;

    private BaseStats[] mSavingThrowProficiencies;

    private int mStrengthSavingThrow;
    private int mDexteritySavingThrow;
    private int mConstitutionSavingThrow;
    private int mIntelligenceSavingThrow;
    private int mWisdomSavingThrow;
    private int mCharismaSavingThrow;

    private int mProficiencyBonus;

    private int mInitiativeModifier;
    private int mHitPoints;
    private int mHitDie;
    private int mMeleeAttackBonus;
    private int mRangedAttackBonus;
    private int mMeleeDamageBonus;
    private int mRangedDamageBonus;

    // statically create and put values into ABILITY_MODIFIER_MAP
    public static final Map<Integer, Integer> ABILITY_MODIFIER_MAP;
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

    public BasePlayerClass(int str, int dex, int con, int intel, int wis, int cha,
                           int level) {
        this.mClassLevel = level;
        this.mStrength = str;
        this.mDexterity = dex;
        this.mConstitution = con;
        this.mIntelligence = intel;
        this.mWisdom = wis;
        this.mCharisma = cha;
        this.mProficiencyBonus = initializeProficiencyBonus(this.mClassLevel);
        this.mHitDie  = setHitDie();
        this.mSavingThrowProficiencies = setSavingThrowProficiencies();

        this.mInitiativeModifier = getDexterityModifier();
        this.mHitPoints = setHitPoints();
        this.mMeleeAttackBonus = mProficiencyBonus + getStrengthModifier();
        this.mMeleeDamageBonus = mProficiencyBonus + getStrengthModifier();
        this.mRangedAttackBonus = mProficiencyBonus + getDexterityModifier();
        this.mRangedDamageBonus = mProficiencyBonus + getDexterityModifier();

        setSavingThrows(mSavingThrowProficiencies);
    }

    /////////////////////////////
    //    ABSTRACT FUNCTIONS   //
    /////////////////////////////

    public abstract int setHitDie();
    public abstract BaseStats[] setSavingThrowProficiencies();
    public abstract int initializeProficiencyBonus(int level);

    private int setHitPoints() {
        int totalHitPoints = 0;

        for(int i = 1; i >= mClassLevel; i ++) {

            if (i == 1) {
                totalHitPoints += (mHitDie + getConstitutionModifier());
            } else {
                totalHitPoints += (Dice.rollDie(mHitDie) + getConstitutionModifier());
            }
        }

        return totalHitPoints;
    }

    private void setSavingThrows(BaseStats[] savingThrowProficiencies) {
        // Set proficient saving throws
        for(BaseStats stat: savingThrowProficiencies) {
            switch (stat) {
                case STRENGTH:
                    mStrengthSavingThrow = getStrengthModifier() + mProficiencyBonus;
                    break;
                case DEXTERITY:
                    mDexteritySavingThrow = getDexterityModifier() + mProficiencyBonus;
                    break;
                case CONSTITUTION:
                    mConstitutionSavingThrow = getConstitutionModifier() + mProficiencyBonus;
                    break;
                case INTELLIGENCE:
                    mIntelligenceSavingThrow = getIntelligenceModifier() + mProficiencyBonus;
                    break;
                case WISDOM:
                    mWisdomSavingThrow = getWisdomModifier() + mProficiencyBonus;
                    break;
                case CHARISMA:
                    mCharismaSavingThrow = getCharismaModifier() + mProficiencyBonus;
                    break;
                default:
                    Log.e(TAG, "Undefined enumeration constant: BaseStats: " + stat.toString());
            }
        }

        // Whatever saving throws were not set should be set to character's stat modifier
        if(mStrengthSavingThrow == 0) {
            mStrengthSavingThrow = getStrengthModifier();
        }
        if(mDexteritySavingThrow == 0) {
            mDexteritySavingThrow = getDexterityModifier();
        }
        if(mConstitutionSavingThrow == 0) {
            mConstitutionSavingThrow = getConstitutionModifier();
        }
        if(mIntelligenceSavingThrow == 0) {
            mIntelligenceSavingThrow = getIntelligenceModifier();
        }
        if(mWisdomSavingThrow == 0) {
            mWisdomSavingThrow = getWisdomModifier();
        }
        if(mCharismaSavingThrow == 0) {
            mCharismaSavingThrow = getCharismaModifier();
        }

    }

    private HashMap<Integer, Integer> createAbilityModifierMap() {
        return null;
    }

    ///////////////////////////////////////////////////
    ////            ABILITY MODFIER GETTERS         ///
    ///////////////////////////////////////////////////

    public int getStrengthModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mStrength);
    }

    public int getDexterityModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mDexterity);
    }

    public int getConstitutionModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mConstitution);
    }

    public int getIntelligenceModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mIntelligence);
    }

    public int getWisdomModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mWisdom);
    }

    public int getCharismaModifier() {
        return ABILITY_MODIFIER_MAP.get(this.mCharisma);
    }

    ///////////////////////////////////////////////////
    ///               GETTERS AND SETTERS           ///
    ///////////////////////////////////////////////////


    public int getClassLevel() {
        return mClassLevel;
    }

    public void setClassLevel(int classLevel) {
        mClassLevel = classLevel;
    }

    public int getStrength() {
        return mStrength;
    }

    public void setStrength(int strength) {
        mStrength = strength;
    }

    public int getDexterity() {
        return mDexterity;
    }

    public void setDexterity(int dexterity) {
        mDexterity = dexterity;
    }

    public int getConstitution() {
        return mConstitution;
    }

    public void setConstitution(int constitution) {
        mConstitution = constitution;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public void setIntelligence(int intelligence) {
        mIntelligence = intelligence;
    }

    public int getWisdom() {
        return mWisdom;
    }

    public void setWisdom(int wisdom) {
        mWisdom = wisdom;
    }

    public int getCharisma() {
        return mCharisma;
    }

    public void setCharisma(int charisma) {
        mCharisma = charisma;
    }

    public int getStrengthSavingThrow() {
        return mStrengthSavingThrow;
    }

    public void setStrengthSavingThrow(int strengthSavingThrow) {
        mStrengthSavingThrow = strengthSavingThrow;
    }

    public int getDexteritySavingThrow() {
        return mDexteritySavingThrow;
    }

    public void setDexteritySavingThrow(int dexteritySavingThrow) {
        mDexteritySavingThrow = dexteritySavingThrow;
    }

    public int getConstitutionSavingThrow() {
        return mConstitutionSavingThrow;
    }

    public void setConstitutionSavingThrow(int constitutionSavingThrow) {
        mConstitutionSavingThrow = constitutionSavingThrow;
    }

    public int getIntelligenceSavingThrow() {
        return mIntelligenceSavingThrow;
    }

    public void setIntelligenceSavingThrow(int intelligenceSavingThrow) {
        mIntelligenceSavingThrow = intelligenceSavingThrow;
    }

    public int getWisdomSavingThrow() {
        return mWisdomSavingThrow;
    }

    public void setWisdomSavingThrow(int wisdomSavingThrow) {
        mWisdomSavingThrow = wisdomSavingThrow;
    }

    public int getCharismaSavingThrow() {
        return mCharismaSavingThrow;
    }

    public void setCharismaSavingThrow(int charismaSavingThrow) {
        mCharismaSavingThrow = charismaSavingThrow;
    }

    public int getProficiencyBonus() {
        return mProficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        mProficiencyBonus = proficiencyBonus;
    }

    public int getInitiativeModifier() {
        return mInitiativeModifier;
    }

    public void setInitiativeModifier(int initiativeModifier) {
        mInitiativeModifier = initiativeModifier;
    }

    public int getHitPoints() {
        return mHitPoints;
    }

    public void setHitPoints(int hitPoints) {
        mHitPoints = hitPoints;
    }

    public int getHitDie() {
        return mHitDie;
    }

    public void setHitDie(int hitDie) {
        mHitDie = hitDie;
    }

    public int getMeleeAttackBonus() {
        return mMeleeAttackBonus;
    }

    public void setMeleeAttackBonus(int meleeAttackBonus) {
        mMeleeAttackBonus = meleeAttackBonus;
    }

    public int getRangedAttackBonus() {
        return mRangedAttackBonus;
    }

    public void setRangedAttackBonus(int rangedAttackBonus) {
        mRangedAttackBonus = rangedAttackBonus;
    }

    public int getMeleDamageBonus() {
        return mMeleeDamageBonus;
    }

    public void setMeleDamageBonus(int meleDamageBonus) {
        mMeleeDamageBonus = meleDamageBonus;
    }

    public int getRangedDamageBounus() {
        return mRangedDamageBonus;
    }

    public void setRangedDamageBounus(int rangedDamageBounus) {
        mRangedDamageBonus = rangedDamageBounus;
    }
}
