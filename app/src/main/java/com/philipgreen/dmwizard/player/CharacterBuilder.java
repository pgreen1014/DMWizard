package com.philipgreen.dmwizard.player;

import com.philipgreen.dmwizard.player.background.Background;
import com.philipgreen.dmwizard.player.utils.Skills;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;

import java.util.ArrayList;

/**
 * Created by pgreen on 10/30/16.
 *
 * Builder class responsible for dynamically building a PlayerCharacter.
 */

public class CharacterBuilder {
    private BaseRaceClass mRace;
    private BasePlayerClass mClass;
    private Background mBackground;
    private int mStrength;
    private int mDexterity;
    private int mConstitution;
    private int mIntelligence;
    private int mWisdom;
    private int mCharisma;
    private ArrayList<Skills> mSkills;

    public CharacterBuilder setRace(BaseRaceClass playerRace) {
        mRace = playerRace;
        return this;
    }

    public CharacterBuilder setClass(BasePlayerClass playerClass) {
        mClass = playerClass;
        return this;
    }

    public CharacterBuilder setBackground(Background background) {
        mBackground = background;
        return this;
    }

    public CharacterBuilder setStrength(int str) {
        mStrength = str;
        return this;
    }

    public CharacterBuilder setDexterity(int dex) {
        mDexterity = dex;
        return this;
    }

    public CharacterBuilder setConstitution(int con) {
        mConstitution = con;
        return this;
    }

    public CharacterBuilder setIntelligence(int intelligence) {
        mIntelligence = intelligence;
        return this;
    }

    public CharacterBuilder setWisdom(int wis) {
        mWisdom = wis;
        return this;
    }

    public CharacterBuilder setCharisma(int cha) {
        mCharisma = cha;
        return this;
    }

    public CharacterBuilder setSkills(ArrayList<Skills> skills) {
        mSkills = skills;
        return this;
    }

    //////////////////////////////////////
    //             GETTERS              //
    //////////////////////////////////////

    public BaseRaceClass getRace() {
        return mRace;
    }

    public BasePlayerClass getPlayerClass() {
        return mClass;
    }

    public Background getBackground() {
        return mBackground;
    }

    public int getStrength() {
        return mStrength;
    }

    public int getDexterity() {
        return mDexterity;
    }

    public int getConstitution() {
        return mConstitution;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public int getWisdom() {
        return mWisdom;
    }

    public int getCharisma() {
        return mCharisma;
    }

    public ArrayList<Skills> getSkills() {
        return mSkills;
    }
}
