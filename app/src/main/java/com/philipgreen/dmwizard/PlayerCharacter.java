package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

import java.util.ArrayList;

/**
 * Created by pgreen on 8/11/16.
 *
 * This class will manage all of the aspects of playing a character and will hold onto
 * data such as classes, race, feats and equipment.
 */
public class PlayerCharacter {
    private ArrayList<BasePlayerClass> mClasses;
    private BaseRaceClass mRace;
    private ArrayList<BaseWeapon> mWeapons;

    // Constructor for character with single class
    public PlayerCharacter(BasePlayerClass playerClass, BaseRaceClass playerRace) {
        this.mClasses.add(playerClass);
        this.mRace = playerRace;
    }

    // Constructor for character with multiple classes
    public PlayerCharacter(ArrayList<BasePlayerClass> playerClasses, BaseRaceClass playerRace) {
        this.mClasses = playerClasses;
        this.mRace = playerRace;
    }

}
