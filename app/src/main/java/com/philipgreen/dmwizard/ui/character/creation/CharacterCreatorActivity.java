package com.philipgreen.dmwizard.ui.character.creation;

import android.support.v4.app.Fragment;

public class CharacterCreatorActivity extends CharacterCreationFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RacePickerFragment();
    }


}
