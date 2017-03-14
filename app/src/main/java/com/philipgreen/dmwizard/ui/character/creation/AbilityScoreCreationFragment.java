package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philipgreen.dmwizard.R;

/**
 * Created by pgreen on 2/5/17.
 */

public class AbilityScoreCreationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ability_score_creation, container, false);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);
        CardView bottomSheet = (CardView) view.findViewById(R.id.abilityScoreSelection_cardView);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e("onStateChanged", "onStateChanged: " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e("onSlide", "onSlide");
            }
        });

        behavior.setPeekHeight(100);


        return view;
    }

}
