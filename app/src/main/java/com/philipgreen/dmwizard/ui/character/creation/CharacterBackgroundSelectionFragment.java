package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philipgreen.dmwizard.R;

/**
 * Created by pgreen on 2/5/17.
 */

public class CharacterBackgroundSelectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_background_selection, container, false);

        return view;
    }

}
