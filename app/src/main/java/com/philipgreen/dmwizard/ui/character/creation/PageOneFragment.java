package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.playerClasses.utils.PlayerClassEnum;
import com.philipgreen.dmwizard.races.utils.RaceListEnum;

import java.util.ArrayList;

/**
 * Created by pgreen on 10/31/16.
 *
 * Fragment that functions as the first page of the character creation flow.
 */

public class PageOneFragment extends Fragment {
    CardView mRacePickerCardView;
    ListView mRacePickerListView;
    TextView mChosenRaceTextView;
    ArrayAdapter<String> mRacePickerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> raceList = new ArrayList<>();
        for(RaceListEnum value: RaceListEnum.values()) {
            raceList.add(configureString(value.toString()));
        }

        mRacePickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, raceList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_creator_page_one, container, false);

        mRacePickerCardView = (CardView) v.findViewById(R.id.cardView_racePicker);
        mRacePickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRacePickerListView.getVisibility() == View.GONE) {
                    mRacePickerListView.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(mRacePickerCardView);
                } else {
                    mRacePickerListView.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(mRacePickerCardView);
                }
            }
        });
        mChosenRaceTextView = (TextView) v.findViewById(R.id.textView_chosenRace);
        mRacePickerListView = (ListView) v.findViewById(R.id.listView_racePicker);
        mRacePickerListView.setAdapter(mRacePickerAdapter);
        mRacePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView raceSelected = (TextView) view;
                String raceString = String.valueOf(raceSelected.getText());
                mChosenRaceTextView.setText(raceString);
                mRacePickerListView.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(mRacePickerCardView);
            }
        });

        return v;
    }

    private String configureString(String string) {
        String[] strings = string.split("_");
        String result = "";

        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                result += " ";
            }
            result += makeFirstCharUppercase(strings[i]);
        }
        
        return result;
    }

    private String makeFirstCharUppercase(String string) {
        String firstLetter = String.valueOf(string.charAt(0));
        String remainingString = string.substring(1);
        return firstLetter.toUpperCase() + remainingString.toLowerCase();
    }
}
