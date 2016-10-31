package com.philipgreen.dmwizard.player;

import com.philipgreen.dmwizard.races.BaseRaceClass;

/**
 * Created by pgreen on 10/30/16.
 *
 * Builder class responsible for dynamically building a PlayerCharacter.
 */

public class CharacterBuilder {
    private BaseRaceClass mRace;

    public CharacterBuilder setRace(BaseRaceClass playerRace) {
        mRace = playerRace;
        return this;
    }

    public BaseRaceClass getRace() {
        return mRace;
    }

}
