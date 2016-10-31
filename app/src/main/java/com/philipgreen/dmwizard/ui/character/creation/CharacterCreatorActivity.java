package com.philipgreen.dmwizard.ui.character.creation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.philipgreen.dmwizard.R;

public class CharacterCreatorActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creator);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer_characterCreator);

        if (fragment == null) {
            fragment = new PageOneFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer_characterCreator, fragment)
                    .commit();
        }
    }
}
