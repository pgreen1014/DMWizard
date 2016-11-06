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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.playerClasses.utils.PlayerClassEnum;
import com.philipgreen.dmwizard.races.utils.RaceListEnum;
import com.philipgreen.dmwizard.races.utils.RaceListManager;
import com.philipgreen.dmwizard.races.utils.SubRaceListEnum;

import java.util.ArrayList;

/**
 * Created by pgreen on 10/31/16.
 *
 * Fragment that functions as the first page of the character creation flow.
 */

public class PageOneFragment extends Fragment {
    CardView mRacePickerCardView, mLevelPickerCardView, mClassPickerCardView, mSubracePickerCardView;
    ListView mRacePickerListView, mLevelPickerListView, mClassPickerListView, mSubracePickerListView;
    TextView mChosenRaceTextView, mChosenLevelTextView, mChosenClassTextView, mChosenSubraceTextView;
    ArrayAdapter<String> mRacePickerAdapter, mClassPickerAdapter, mLevelPickerAdapter, mSubRacePickerAdapter;
    ViewGroup mContainer;
    ArrayList<ListView> mPickerListViews = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_creator_page_one, container, false);

        mContainer = container;
        mChosenRaceTextView = (TextView) v.findViewById(R.id.textView_chosenRace);
        mChosenClassTextView = (TextView) v.findViewById(R.id.textView_chosenClass);
        mChosenLevelTextView = (TextView) v.findViewById(R.id.textView_chosenLevel);
        mChosenSubraceTextView = (TextView) v.findViewById(R.id.textView_chosenSubRace);

        // SET UP CARD VIEWS
        mRacePickerCardView = (CardView) v.findViewById(R.id.cardView_racePicker);
        mLevelPickerCardView = (CardView) v.findViewById(R.id.cardView_levelPicker);
        mClassPickerCardView = (CardView) v.findViewById(R.id.cardView_classPicker);
        mSubracePickerCardView = (CardView) v.findViewById(R.id.cardView_subRacePicker);

        mRacePickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRacePickerListView.getVisibility() == View.GONE) {
                    mRacePickerListView.setVisibility(View.VISIBLE);
                    mChosenRaceTextView.setVisibility(View.GONE);
                    closeOtherListViews(mRacePickerListView);
                    TransitionManager.beginDelayedTransition(container);
                }
            }
        });

        mLevelPickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLevelPickerListView.setVisibility(View.VISIBLE);
                mChosenLevelTextView.setVisibility(View.GONE);
                closeOtherListViews(mLevelPickerListView);
                TransitionManager.beginDelayedTransition(container);
            }
        });

        mClassPickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClassPickerListView.setVisibility(View.VISIBLE);
                mChosenClassTextView.setVisibility(View.GONE);
                closeOtherListViews(mClassPickerListView);
                TransitionManager.beginDelayedTransition(container);
            }
        });

        mSubracePickerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSubracePickerCardView.getCardElevation() == mRacePickerCardView.getCardElevation()) {
                    mSubracePickerListView.setVisibility(View.VISIBLE);
                    mChosenSubraceTextView.setVisibility(View.GONE);
                    closeOtherListViews(mSubracePickerListView);
                    TransitionManager.beginDelayedTransition(container);
                }
            }
        });

        // SET UP LIST VIEWS
        mRacePickerListView = (ListView) v.findViewById(R.id.listView_racePicker);
        mRacePickerListView.setAdapter(mRacePickerAdapter);
        mLevelPickerListView = (ListView) v.findViewById(R.id.listView_levelPicker);
        mLevelPickerListView.setAdapter(mLevelPickerAdapter);
        mClassPickerListView = (ListView) v.findViewById(R.id.listView_classPicker);
        mClassPickerListView.setAdapter(mClassPickerAdapter);
        mSubracePickerListView = (ListView) v.findViewById(R.id.listView_subRacePicker);
        initPickerListViews();

        mRacePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView raceSelected = (TextView) view;
                String raceString = String.valueOf(raceSelected.getText());
                mChosenRaceTextView.setText(raceString);
                mRacePickerListView.setVisibility(View.GONE);
                mChosenRaceTextView.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition((ViewGroup) mRacePickerCardView.getParent());
                generateSubRaceListView(raceString);
                /*
                onItemListViewClick(view, mChosenRaceTextView, mRacePickerListView);

                TextView raceSelected = (TextView) view;
                String raceString = String.valueOf(raceSelected.getText());
                generateSubRaceListView(raceString);
                */
            }
        });

        mLevelPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemListViewClick(view, mChosenLevelTextView, mLevelPickerListView);
            }
        });

        mClassPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemListViewClick(view, mChosenClassTextView, mClassPickerListView);
            }
        });

        return v;
    }

    /**
     * Take and converts an enum.toString() and converts it into a readable string.
     * Enum should only contain capital letters and underscores. Example: HILL_DWARF will
     * be converted to Hill Dwarf
     * @param string enum.toString()
     * @return a human readable string
     */
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

    // Makes the first letter of a string upper case
    private String makeFirstCharUppercase(String string) {
        String firstLetter = String.valueOf(string.charAt(0));
        String remainingString = string.substring(1);
        return firstLetter.toUpperCase() + remainingString.toLowerCase();
    }

    /**
     * Converts a string generated from configureString(string) back into
     * it enum type
     * @param raceText enum.toString() to convert back to string
     * @return return enum type, return null if enum is not found
     */
    private RaceListEnum getRaceEnumFromTextView(String raceText) {
        RaceListEnum raceValue = null;

        for(RaceListEnum value: RaceListEnum.values()) {
            if (configureString(value.toString()).equals(raceText)) {
                raceValue = value;
            }
        }

        return raceValue;
    }

    private void generateSubRaceListView(String raceText) {
        // Grab enum value from TextView
        RaceListEnum raceValue = getRaceEnumFromTextView(raceText);

        // Grab sub races of selected race
        SubRaceListEnum[] subraceList = RaceListManager.getSubraceList(raceValue);

        if (subraceList == null) {
            hideSubRacePicker();
            return;
        }
        
        mSubracePickerCardView.setCardElevation(mRacePickerCardView.getCardElevation());
        mSubracePickerCardView.setVisibility(View.VISIBLE);
        mChosenSubraceTextView.setText(R.string.choose_subrace_hint);

        ArrayList<String> listForAdapter = new ArrayList<>();
        for (int i = 0; i < subraceList.length; i++) {
            if (i == 0) {
                listForAdapter.add("None");
            }
            listForAdapter.add(configureString(subraceList[i].toString()));
        }

        mSubRacePickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listForAdapter);

        mSubracePickerListView.setAdapter(mSubRacePickerAdapter);
        mSubracePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemListViewClick(view, mChosenSubraceTextView, mSubracePickerListView);
            }
        });
    }

    private void initPickerListViews() {
        mPickerListViews.add(mRacePickerListView);
        mPickerListViews.add(mSubracePickerListView);
        mPickerListViews.add(mLevelPickerListView);
        mPickerListViews.add(mClassPickerListView);
    }

    private void onItemListViewClick(View view, TextView textViewChosen, ListView listViewToClose) {
        TextView textViewSelected = (TextView) view;
        String textViewString = String.valueOf(textViewSelected.getText());

        textViewChosen.setText(textViewString);
        textViewChosen.setVisibility(View.VISIBLE);
        listViewToClose.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(mContainer);

    }

    private void closeOtherListViews(ListView openingListView) {
        for (ListView view : mPickerListViews) {
            // if there is a list view open that is not the list view being open then make it gone
            if (view != openingListView && view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
                CardView parentCardView = (CardView) view.getParent();
                parentCardView.findViewWithTag("chosenItem").setVisibility(View.VISIBLE);
            }
        }
    }

    private void hideSubRacePicker() {
        mSubracePickerCardView.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition((ViewGroup) mSubracePickerCardView.getParent());
    }
}
