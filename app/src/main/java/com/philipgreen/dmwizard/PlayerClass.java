package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.data.Alignment;
import com.philipgreen.dmwizard.data.Languages;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;

import java.util.ArrayList;

/**
 * Created by pgreen on 8/11/16.
 */
public class PlayerClass {
    private static final String TAG = "PlayerClass";

    private String mCharacterName;
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
    private ArrayList<BasePlayerClass> mClass;

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

    public PlayerClass() {
        
    }

}
