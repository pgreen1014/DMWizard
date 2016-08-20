package com.philipgreen.dmwizard;

import android.util.ArraySet;
import android.util.Log;

import com.philipgreen.dmwizard.data.Alignment;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.dice.Dice;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by pgreen on 8/11/16.
 */
public class PlayerCharacter {
    private static final String TAG = "PlayerCharacter";
    // Proficiency bonus modifiers by level. correct modifier = level - 1
    private static final int[] PROFICIENCY_BONUS_BY_LEVEL = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};

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

    private HashSet<BaseStats> mSavingThrowProficiencies;  // A set because there should be no repeated values TODO needs testing
    private int mStrengthSavingThrow;
    private int mDexteritySavingThrow;
    private int mConstitutionSavingThrow;
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

    // Constructor for creating a level 1 character
    public PlayerCharacter(BasePlayerClass playerClass, int str, int dex, int con, int intel, int wis, int cha) {
        this.mClasses = new ArrayList<>();
        mClasses.add(playerClass);

        this.mCharacterLevel = setCharacterLevel(mClasses);
        initProficiencyBonus(mCharacterLevel);

        this.mStrength = str;
        this.mDexterity = dex;
        this.mConstitution = con;
        this.mIntelligence = intel;
        this.mWisdom = wis;
        this.mCharisma = cha;

        this.mInitiative = getDexterityModifier();
        this.mProficientSkills = setupSkillProficiencies(mClasses);
        this.mSavingThrowProficiencies = setUpSavingThrowProficiencies(mClasses);

        setUpSkills(mProficientSkills);
        setSavingThrows(mSavingThrowProficiencies);
    }

    public String toString() {
        return "Proficiency bonus: " + Integer.toString(mProficiencyBonus) + "\n\n"
                + "Classes: " + getClassesToString() + "\n"
                + "Initiative: " + Integer.toString(getInitiativeModifier()) + "\n\n"
                + "ABILITIES\n"
                + "Strength: " + Integer.toString(mStrength) + "\n"
                + "Strength modifier: " + Integer.toString(getStrengthModifier()) + "\n\n"
                + "Dexterity: " + Integer.toString(mDexterity) + "\n"
                + "Dexterity modifier: " + Integer.toString(getDexterityModifier()) + "\n\n"
                + "Constitution: " + Integer.toString(mConstitution) + "\n"
                + "Constitution modifier: " + Integer.toString(getConstitutionModifier()) + "\n\n"
                + "Intelligence: " + Integer.toString(mIntelligence) + "\n"
                + "Intelligence modifier: " + Integer.toString(getIntelligenceModifier()) + "\n\n"
                + "Wisdom: " + Integer.toString(mWisdom) + "\n"
                + "Wisdom modifier: " + Integer.toString(getWisdomModifier()) + "\n\n"
                + "Charisma: " + Integer.toString(mCharisma) + "\n"
                + "Charisma modifier: " + Integer.toString(getCharismaModifier()) + "\n\n"
                + "SAVING THROWS\n"
                + "Saving Throw Proficiencies: " + getProficientSavesToString() + "\n"
                + "Strength Saving Throw: " + Integer.toString(getStrengthSavingThrow()) + "\n"
                + "Dexterity Saving Throw: " + Integer.toString(getDexteritySavingThrow()) + "\n"
                + "Constitution Saving Throw: " + Integer.toString(getConstitutionSavingThrow()) + "\n"
                + "Intelligence Saving Throw: " + Integer.toString(getIntelligenceSavingThrow()) + "\n"
                + "Wisdom Saving Throw: " + Integer.toString(getWisdomSavingThrow()) + "\n"
                + "Charisma Saving Throw: " + Integer.toString(getCharismaSavingThrow()) + "\n\n"
                + "SKILLS\n"
                + "Proficient Skills: " + getProficientSkillsToString() + "\n"
                + "Acrobatics: " + Integer.toString(mAcrobatics) + "\n"
                + "Animal Handling: " + Integer.toString(mAnimalHandling) + "\n"
                + "Arcana: " + Integer.toString(mArcana) + "\n"
                + "Athletics: " + Integer.toString(mAthletics) + "\n"
                + "Deception: " + Integer.toString(mDeception) + "\n"
                + "History: " + Integer.toString(mHistory) + "\n"
                + "Insight: " + Integer.toString(mInsight) + "\n"
                + "Intimidation: " + Integer.toString(mIntimidation) + "\n"
                + "Investigation: " + Integer.toString(mInvestigation) + "\n"
                + "Medicine: " + Integer.toString(mMedicine) + "\n"
                + "Nature: " + Integer.toString(mNature) + "\n"
                + "Perception: " + Integer.toString(mPerception) + "\n"
                + "Performance: " + Integer.toString(mPerformance) + "\n"
                + "Persuasion: " + Integer.toString(mPersuasion) + "\n"
                + "Religion: " + Integer.toString(mReligion) + "\n"
                + "Sleight of Hand: " + Integer.toString(mSleightOfHand) + "\n"
                + "Stealth: " + Integer.toString(mStealth) + "\n"
                + "Survival: " + Integer.toString(mSurvival);

    }

    private int setCharacterLevel(ArrayList<BasePlayerClass> classes) {
        int characterLevel = 0;

        for(BasePlayerClass playerClass: classes) {
            characterLevel += playerClass.getClassLevel();
        }

        return characterLevel;
    }

    private void initProficiencyBonus(int characterLevel) {
        // if Character level is greater than 20 then set to highest proficiency
        if (mCharacterLevel > 20) {
            mProficiencyBonus = PROFICIENCY_BONUS_BY_LEVEL[19];
            return;
        }
        mProficiencyBonus = PROFICIENCY_BONUS_BY_LEVEL[mCharacterLevel - 1];
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
        this.mAthletics = getStrengthModifier();
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
                    this.mSurvival += mProficiencyBonus;
                    break;
            }
        }
    }

    private HashSet<BaseStats> setUpSavingThrowProficiencies(ArrayList<BasePlayerClass> playerClasses) {
        HashSet<BaseStats> savingThrowProficiencies = new HashSet<>();

        for(BasePlayerClass playerClass: playerClasses) {
            BaseStats[] proficiencies = playerClass.getSavingThrowProficiencies();

            for(BaseStats proficiency: proficiencies) {
                savingThrowProficiencies.add(proficiency);
            }
        }

        return savingThrowProficiencies;
    }

    private void setSavingThrows(HashSet<BaseStats> savingThrowProficiencies) {
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

    /////////////////////////
    //  ABILITY MODIFIERS  //
    /////////////////////////

    public int getStrengthModifier() {
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

    ////////////////////////////////
    //      CHARACTER ACTIONS     //
    ////////////////////////////////

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

    /////////////////////////////////
    //     GETTERS AND SETTERS     //
    /////////////////////////////////

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

    public int getInitiativeModifier() {
        return mInitiative;
    }

    public void setInitiativeModifier(int initiative) {
        mInitiative = initiative;
    }

    public String getClassesToString() {
        String classes = "";
        for(BasePlayerClass playerClass: mClasses) {
            classes += playerClass.toString() + " ";
        }
        return classes;
    }

    public String getProficientSavesToString() {
        String stats = "";
        for(BaseStats stat: mSavingThrowProficiencies) {
            switch (stat) {
                case STRENGTH:
                    stats += "Strength ";
                    break;
                case DEXTERITY:
                    stats += "Dexterity ";
                    break;
                case CONSTITUTION:
                    stats += "Constitution ";
                    break;
                case INTELLIGENCE:
                    stats += "Intelligence ";
                    break;
                case WISDOM:
                    stats += "Wisdom ";
                    break;
                case CHARISMA:
                    stats += "Charisma ";
                    break;
                default:
                    Log.e(TAG, "undefined BaseStat enumerated type");
                    break;
            }
        }
        return stats;
    }

    public String getProficientSkillsToString() {
        String skills = "";
        for(Skills skill: mProficientSkills) {
            switch (skill) {
                case ACROBATICS:
                    skills += "Acrobatics ";
                    break;
                case ANIMAL_HANDLING:
                    skills += "Animal_Handling ";
                    break;
                case ARCANA:
                    skills += "Arcana ";
                    break;
                case ATHLETICS:
                    skills += "Athletics ";
                    break;
                case DECEPTION:
                    skills += "Deception ";
                    break;
                case HISTORY:
                    skills += "History ";
                    break;
                case INSIGHT:
                    skills += "Insight ";
                    break;
                case INTIMIDATION:
                    skills += "Intimidation ";
                    break;
                case INVESTIGATION:
                    skills += "Investigation ";
                    break;
                case MEDICINE:
                    skills += "Medicine ";
                    break;
                case NATURE:
                    skills += "Nature ";
                    break;
                case PERCEPTION:
                    skills += "Perception ";
                    break;
                case PERFORMANCE:
                    skills += "Performance ";
                    break;
                case PERSUASION:
                    skills += "Persuasion ";
                    break;
                case RELIGION:
                    skills += "Religion ";
                    break;
                case SLEIGHT_OF_HAND:
                    skills += "Sleight_of_Hand ";
                    break;
                case STEALTH:
                    skills += "Stealth ";
                    break;
                case SURVIVAL:
                    skills += "Survival ";
                    break;
                default:
                    Log.e(TAG, "Undefined Skills enumerated type ");
                    break;
            }
        }
        return skills;
    }
}
