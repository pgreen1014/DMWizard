package com.philipgreen.dmwizard.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.philipgreen.dmwizard.R;

/**
 * Created by pgreen on 11/27/16.
 *
 * Abstract class used for handling the adding of a fragment to a fragment container for the character creation flow
 */

public abstract class CharacterCreationFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creator);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer_characterCreator);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer_characterCreator, fragment)
                    .commit();
        }
    }
}
