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

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pgreen on 10/31/16.
 *
 * Fragment that functions as the first page of the character creation flow.
 */

public class PageOneFragment extends Fragment {
    CardView mRacePickerCardView, mLevelPickerCardView, mClassPickerCardView;
    ListView mRacePickerListView, mLevelPickerListView, mClassPickerListView;
    TextView mChosenRaceTextView, mChosenLevelTextView, mChosenClassTextView;
    ArrayAdapter<String> mRacePickerAdapter, mClassPickerAdapter, mLevelPickerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> raceList = new ArrayList<>();
        for(RaceListEnum value: RaceListEnum.values()) {
            raceList.add(configureString(value.toString()));
        }

        ArrayList<String> levelList = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            levelList.add(Integer.toString(i));
        }

        ArrayList<String> classList = new ArrayList<>();
        for(PlayerClassEnum playerClass: PlayerClassEnum.values()) {
            classList.add(configureString(configureString(playerClass.toString())));
        }

        mRacePickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, raceList);
        mLevelPickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, levelList);
        mClassPickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, classList);
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
                    mChosenRaceTextView.setVisibility(View.GONE);
                    mRacePickerListView.getLayoutParams().height = 800;
                    TransitionManager.beginDelayedTransition((ViewGroup) mRacePickerCardView.getParent());
                }
            }
        });
        mLevelPickerCardView = (CardView) v.findViewById(R.id.cardView_levelPicker);
        mLevelPickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLevelPickerListView.setVisibility(View.VISIBLE);
                mChosenLevelTextView.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(mLevelPickerCardView);
            }
        });
        mClassPickerCardView = (CardView) v.findViewById(R.id.cardView_classPicker);
        mClassPickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClassPickerListView.setVisibility(View.VISIBLE);
                mChosenClassTextView.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(mClassPickerCardView);
            }
        });
        mChosenRaceTextView = (TextView) v.findViewById(R.id.textView_chosenRace);
        mChosenClassTextView = (TextView) v.findViewById(R.id.textView_chosenClass);
        mChosenLevelTextView = (TextView) v.findViewById(R.id.textView_chosenLevel);
        mRacePickerListView = (ListView) v.findViewById(R.id.listView_racePicker);
        mRacePickerListView.setAdapter(mRacePickerAdapter);
        mRacePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView raceSelected = (TextView) view;
                String raceString = String.valueOf(raceSelected.getText());
                mChosenRaceTextView.setText(raceString);
                mRacePickerListView.setVisibility(View.GONE);
                mChosenRaceTextView.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition((ViewGroup) mRacePickerCardView.getParent());
            }
        });
        mLevelPickerListView = (ListView) v.findViewById(R.id.listView_levelPicker);
        mLevelPickerListView.setAdapter(mLevelPickerAdapter);
        mLevelPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView levelSelected = (TextView) view;
                String levelString = String.valueOf(levelSelected.getText());
                mChosenLevelTextView.setText(levelString);
                mLevelPickerListView.setVisibility(View.GONE);
                mChosenLevelTextView.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition(mLevelPickerCardView);
            }
        });
        mClassPickerListView = (ListView) v .findViewById(R.id.listView_classPicker);
        mClassPickerListView.setAdapter(mClassPickerAdapter);
        mClassPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView classSelected = (TextView) view;
                String classString = String.valueOf(classSelected.getText());
                mChosenClassTextView.setText(classString);
                mClassPickerListView.setVisibility(View.GONE);
                mChosenClassTextView.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition(mClassPickerCardView);
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
