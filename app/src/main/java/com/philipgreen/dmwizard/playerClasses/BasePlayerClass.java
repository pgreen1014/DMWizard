package com.philipgreen.dmwizard.playerClasses;

import android.util.Log;

import com.philipgreen.dmwizard.data.ArmorProficiencies;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.data.WeaponProficiencies;
import com.philipgreen.dmwizard.data.WeaponProperties;
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

    private WeaponProficiencies[] mWeaponProficiencies;
    private ArmorProficiencies[] mArmorProficiencies;

    private Skills[] mProficientSkills;
    private int mAcrobatics;
    private int mAnimalHandling;
    private int mArcana;
    private int mAthletics;
    private int mDeception;
    private int mHistory;
    private int mInsight;
    private int mIntimidation;
    private int mInvestigation;
    private int mMedicine;
    private int mNature;
    private int mPerception;
    private int mPerformance;
    private int mPersuasion;
    private int mReligion;
    private int mSleightOfHand;
    private int mStealth;
    private int mSurvival;

    public BasePlayerClass(int str, int dex, int con, int intel, int wis, int cha,
                           int level, Skills[] proficientSkills) {
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

        this.mWeaponProficiencies = initWeaponProficiencies();
        this.mArmorProficiencies = initArmorProficiencies();
        this.mProficientSkills = proficientSkills;
    }

    /////////////////////////////
    //    ABSTRACT FUNCTIONS   //
    /////////////////////////////

    public abstract int setHitDie();
    public abstract BaseStats[] setSavingThrowProficiencies();
    public abstract int initializeProficiencyBonus(int level);
    public abstract WeaponProficiencies[] initWeaponProficiencies();
    public abstract ArmorProficiencies[] initArmorProficiencies();

    ///////////////////////////////////////////////////
    ////               CHARACTER ACTIONS            ///
    ///////////////////////////////////////////////////

    public int rollStrSavingThrow() {
        return Dice.rollDie(20) + getStrengthSavingThrow();
    }

    public int rollDexSavingThrow() {
        return Dice.rollDie(20) + getDexteritySavingThrow();
    }

    public int rollConSavingThrow() {
        return Dice.rollDie(20) + getConstitutionSavingThrow();
    }

    public int rollIntSavingThrow() {
        return Dice.rollDie(20) + getIntelligenceSavingThrow();
    }

    public int rollWisSavingThrow() {
        return Dice.rollDie(20) + getWisdomSavingThrow();
    }

    public int rollChaSavingThrow() {
        return Dice.rollDie(20) + getCharismaSavingThrow();
    }

    public int rollInitiative() {
        return Dice.rollDie(20) + getInitiativeModifier();
    }

    public int rollAcrobatics() {
        return Dice.rollDie(20) + mAcrobatics;
    }

    public int rollAnimalHandling() {
        return Dice.rollDie(20) + mAnimalHandling;
    }

    public int rollArcana() {
        return Dice.rollDie(20) + mArcana;
    }

    public int rollAthletics() {
        return Dice.rollDie(20) + mAthletics;
    }

    public int rollDeception() {
        return Dice.rollDie(20) + mDeception;
    }

    public int rollHistory() {
        return Dice.rollDie(20) + mHistory;
    }

    public int rollInsight() {
        return Dice.rollDie(20) + mInsight;
    }

    public int rollIntimidation() {
        return Dice.rollDie(20) + mIntimidation;
    }

    public int rollInvestigation() {
        return Dice.rollDie(20) + mInvestigation;
    }

    public int rollMedicine() {
        return Dice.rollDie(20) + mMedicine;
    }

    public int rollNature() {
        return Dice.rollDie(20) + mNature;
    }

    public int rollPerception() {
        return Dice.rollDie(20) + mPerception;
    }

    public int rollPerformance() {
        return Dice.rollDie(20) + mPerformance;
    }

    public int rollPersuasion() {
        return Dice.rollDie(20) + mPersuasion;
    }

    public int rollReligion() {
        return Dice.rollDie(20) + mReligion;
    }

    public int rollSleightOfHand() {
        return Dice.rollDie(20) + mSleightOfHand;
    }

    public int rollStealth() {
        return Dice.rollDie(20) + mStealth;
    }

    public int rollSurvival() {
        return Dice.rollDie(20) + mSurvival;
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

    public Skills[] getSkillProficiencies() {
        return this.mProficientSkills;
    }

    public BaseStats[] getSavingThrowProficiencies() {
        return this.mSavingThrowProficiencies;
    }
}
