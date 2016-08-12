package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.data.Alignment;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.data.Skills;
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

    private ArrayList<Skills> mProficientSkills;
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
        this.mProficientSkills = setupSkillProficiencies(mClasses);
        setUpSkills(mProficientSkills);
    }

    // Grabs skill proficiencies from all classes to create an array list of all character proficiencies
    private ArrayList<Skills> setupSkillProficiencies(ArrayList<BasePlayerClass> classes) {
        ArrayList<Skills> combinedProficientSkills = new ArrayList<>();

        for(BasePlayerClass playerClass: classes) {
            Skills[] skills = playerClass.getProficientSkills();

            for(Skills skill: skills) {
                combinedProficientSkills.add(skill);
            }

        }

        return combinedProficientSkills;
    }

    // Set skill modifiers
    private void setUpSkills(ArrayList<Skills> proficientSkills) {
        this.mAcrobatics = getDexterityModifier();
        this.mAnimalHandling = getWisdomModifier();
        this.mArcana = getIntelligenceModifier();
        this.mAthletics = getStrenthModifier();
        this.mDeception = getCharismaModifier();
        this.mHistory = getIntelligenceModifier();
        this.mInsight = getWisdomModifier();
        this.mIntimidation = getCharismaModifier();
        this.mInvestigation = getIntelligenceModifier();
        this.mMedicine = getWisdomModifier();
        this.mNature = getIntelligenceModifier();
        this.mPerception = getWisdomModifier();
        this.mPerformance = getCharismaModifier();
        this.mPersuasion = getCharismaModifier();
        this.mReligion = getIntelligenceModifier();
        this.mSleightOfHand = getDexterityModifier();
        this.mStealth = getDexterityModifier();
        this.mSurvival = getWisdomModifier();
        for(Skills skill: proficientSkills) {
            switch (skill) {
                case ACROBATICS:
                    this.mAcrobatics += mProficiencyBonus;
                    break;
                case ANIMAL_HANDLING:
                    this.mAnimalHandling += mProficiencyBonus;
                    break;
                case ARCANA:
                    this.mArcana += mProficiencyBonus;
                    break;
                case ATHLETICS:
                    this.mAthletics += mProficiencyBonus;
                    break;
                case DECEPTION:
                    this.mDeception += mProficiencyBonus;
                    break;
                case HISTORY:
                    this.mHistory += mProficiencyBonus;
                    break;
                case INSIGHT:
                    this.mInsight += mProficiencyBonus;
                    break;
                case INTIMIDATION:
                    this.mIntimidation += mProficiencyBonus;
                    break;
                case INVESTIGATION:
                    this.mInvestigation += mProficiencyBonus;
                    break;
                case MEDICINE:
                    this.mMedicine += mProficiencyBonus;
                    break;
                case NATURE:
                    this.mNature += mProficiencyBonus;
                    break;
                case PERCEPTION:
                    this.mPerception += mProficiencyBonus;
                    break;
                case PERFORMANCE:
                    this.mPerformance += mProficiencyBonus;
                    break;
                case PERSUASION:
                    this.mPersuasion += mProficiencyBonus;
                    break;
                case RELIGION:
                    this.mReligion += mProficiencyBonus;
                    break;
                case SLEIGHT_OF_HAND:
                    this.mSleightOfHand += mProficiencyBonus;
                    break;
                case STEALTH:
                    this.mStealth += mProficiencyBonus;
                    break;
                case SURVIVAL:
                    this.mStealth += mProficiencyBonus;
                    break;
            }
        }
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

    public ArrayList<Skills> getProficientSkills() {
        return mProficientSkills;
    }
}
