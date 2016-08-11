package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.data.Alignment;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pgreen on 8/11/16.
 */
public class PlayerCharacter {
    private static final String TAG = "PlayerCharacter";

    private String mCharacterName;
    private int mCharacterLevel;       // Character level is the total number of class levels
    private Alignment mAlignment;
    // TODO add Background
    private int mExperiencePoints;
    // TODO Personality Traits
    // TODO Ideals
    // TODO Bonds
    // TODO Flaws
    private int mAge;
    private int mHeight;
    // TODO eyes
    // TODO skin
    // TODO hair
    // TODO Money

    private BaseRaceClass mRace;
    private ArrayList<BasePlayerClass> mClasses;

    private int mStrength;
    private int mDexterity;
    private int mConstitution;
    private int mIntelligence;
    private int mWisdom;
    private int mCharisma;

    private int mStrengthSavingThrow;
    private int mDexteritySavingThrow;
    private int mConstituionSavingThrow;
    private int mIntelligenceSavingThrow;
    private int mWisdomSavingThrow;
    private int mCharismaSavingThrow;

    private int mProficiencyBonus;
    private int mInspiration;

    private int mArmorClass;
    private int mInitiative;
    private int mSpeed;       // Speed is in feet need to convert to squares (mSpeed / 5)

    private int mHitPoints;
    private int mMaxHitPoints;
    private int mTemporaryHitPoints;
    private int HitDice;
    private int mDeathSaveSuccesses;
    private int mDeathSaveFailures;

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

    private int mPassiveWisdom;

    private ArrayList<Languages> mLanguages;

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

    public PlayerCharacter(BasePlayerClass playerClass, int str, int dex, int con, int intel, int wis, int cha) {
        this.mClasses = new ArrayList<>();
        mClasses.add(playerClass);
        
        this.mStrength = str;
        this.mDexterity = dex;
        this.mConstitution = con;
        this.mIntelligence = intel;
        this.mWisdom = wis;
        this.mCharisma = cha;
    }

    /////////////////////////
    //  ABILITY MODIFIERS  //
    /////////////////////////

    public int getStrenthModifier() {
        return ABILITY_MODIFIER_MAP.get(mStrength);
    }

    public int getDexterityModifier() {
        return ABILITY_MODIFIER_MAP.get(mDexterity);
    }

    public int getConstitutionModifier() {
        return ABILITY_MODIFIER_MAP.get(mConstitution);
    }

    public int getIntelligenceModifier() {
        return ABILITY_MODIFIER_MAP.get(mIntelligence);
    }

    public int getWisdomModifier() {
        return ABILITY_MODIFIER_MAP.get(mWisdom);
    }

    public int getCharismaModifier() {
        return ABILITY_MODIFIER_MAP.get(mCharisma);
    }

}
