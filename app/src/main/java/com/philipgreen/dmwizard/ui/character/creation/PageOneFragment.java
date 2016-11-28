package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.playerClasses.utils.PlayerClassEnum;
import com.philipgreen.dmwizard.races.utils.RaceListEnum;
import com.philipgreen.dmwizard.races.utils.RaceListManager;
import com.philipgreen.dmwizard.races.utils.SubRaceListEnum;
import com.philipgreen.dmwizard.ui.views.LevelAndClassPickerView;
import com.philipgreen.dmwizard.utils.UnitConverter;

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
    LinearLayout mContent;
    FloatingActionButton mFAB;
    ViewGroup mContainer;
    ArrayList<ListView> mPickerListViews = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListViewAdapters();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_creator_page_one, container, false);

        mContent = (LinearLayout) v.findViewById(R.id.content);
        // capture parent view as global variable
        mContainer = container;

        // initialize TextViews
        mChosenRaceTextView = (TextView) v.findViewById(R.id.textView_chosenRace);
        mChosenClassTextView = (TextView) v.findViewById(R.id.textView_chosenClass);
        mChosenLevelTextView = (TextView) v.findViewById(R.id.textView_chosenLevel);
        mChosenSubraceTextView = (TextView) v.findViewById(R.id.textView_chosenSubRace);

        // Set up CardViews and ListViews
        initCardViews(v, container);
        initListViews(v);

        // Floating Action Button
        mFAB = (FloatingActionButton) v.findViewById(R.id.floatingActionButton_addClassPicker);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                int margin = UnitConverter.convertPixelsToDP(PageOneFragment.this, 16);
                params.setMargins(margin, 0, margin, margin);

                LevelAndClassPickerView levelAndClassPickerView = new LevelAndClassPickerView(getContext(), PageOneFragment.this,
                        mLevelPickerAdapter, mClassPickerAdapter);

                // Slide the levelAndClassPickerView from the left
                levelAndClassPickerView.setTranslationX(-1000f);
                mContent.addView(levelAndClassPickerView, params);
                levelAndClassPickerView.animate().translationXBy(1000f).setDuration(500);
                // Provide gradual transition of the other views
                TransitionManager.beginDelayedTransition(mContainer);
            }
        });

        return v;
    }

    // Initialize CardViews and their listeners
    private void initCardViews(View v, final ViewGroup container) {
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
    }

    // Initialize ListViews and their listeners
    private void initListViews(View v) {
        mRacePickerListView = (ListView) v.findViewById(R.id.listView_racePicker);
        mRacePickerListView.setAdapter(mRacePickerAdapter);

        mLevelPickerListView = (ListView) v.findViewById(R.id.listView_levelPicker);
        mLevelPickerListView.setAdapter(mLevelPickerAdapter);

        mClassPickerListView = (ListView) v.findViewById(R.id.listView_classPicker);
        mClassPickerListView.setAdapter(mClassPickerAdapter);

        mSubracePickerListView = (ListView) v.findViewById(R.id.listView_subRacePicker);
        // initialize mPickerListViews ArrayList with the initialize ListViews
        initPickerListViews();

        mRacePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedRace =
                        onListItemSelected(view, mChosenRaceTextView, mRacePickerListView, (ViewGroup) mRacePickerCardView.getParent());

                generateSubRaceListView(selectedRace);

            }
        });

        mLevelPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemSelected(view, mChosenLevelTextView, mLevelPickerListView, mContainer);
            }
        });

        mClassPickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemSelected(view, mChosenClassTextView, mClassPickerListView, mContainer);
            }
        });
    }

    /**
     * Take and converts an enum.toString() and converts it into a readable string.
     * Enum should only contain capital letters and underscores. Example: HILL_DWARF will
     * be converted to Hill Dwarf
     * @param string enum.toString()
     * @return a human readable string
     */
    private String configureEnumToString(String string) {
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
     * Converts a string generated from configureEnumToString(string) back into
     * it enum type
     * @param raceText enum.toString() to convert back to string
     * @return return enum type, return null if enum is not found
     */
    private RaceListEnum getRaceEnumFromTextView(String raceText) {
        RaceListEnum raceValue = null;

        for(RaceListEnum value: RaceListEnum.values()) {
            if (configureEnumToString(value.toString()).equals(raceText)) {
                raceValue = value;
            }
        }

        return raceValue;
    }

    /**
     * generates the sub-race corresponding to the race selected. If selected race does not have a corresponding list of sub-races,
     * then the sub-race CardView is hidden.
     * @param raceText String of the race selected.
     */
    private void generateSubRaceListView(String raceText) {
        // Grab enum value from TextView
        RaceListEnum raceValue = getRaceEnumFromTextView(raceText);

        // Grab sub races of selected race
        SubRaceListEnum[] subraceList = RaceListManager.getSubraceList(raceValue);

        // hide subrace CardView if there are no subraces
        if (subraceList == null) {
            hideSubRacePicker();
            return;
        }

        // set layout parameters for CardView
        mSubracePickerCardView.setCardElevation(mRacePickerCardView.getCardElevation());
        mSubracePickerCardView.setVisibility(View.VISIBLE);
        mChosenSubraceTextView.setText(R.string.choose_subrace_hint);

        // Create the sub-race list adapter
        ArrayList<String> listForAdapter = new ArrayList<>();
        for (int i = 0; i < subraceList.length; i++) {
            // add "None" to top of list if it isn't a dragonborn
            if (i == 0 && raceValue != RaceListEnum.DRAGONBORN) {
                listForAdapter.add("None");
            }
            listForAdapter.add(configureEnumToString(subraceList[i].toString()));
        }

        // Set up initialize SubRace ListView
        mSubRacePickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listForAdapter);
        mSubracePickerListView.setAdapter(mSubRacePickerAdapter);
        mSubracePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemSelected(view, mChosenSubraceTextView, mSubracePickerListView, mContainer);
            }
        });
    }

    // Puts ListViews in a global ArrayList for iteration
    private void initPickerListViews() {
        mPickerListViews.add(mRacePickerListView);
        mPickerListViews.add(mSubracePickerListView);
        mPickerListViews.add(mLevelPickerListView);
        mPickerListViews.add(mClassPickerListView);
    }

    /**
     * function initializes the ArrayAdapters used for constructing list views.
     */
    private void initListViewAdapters() {
        ArrayList<String> raceList = new ArrayList<>();
        for(RaceListEnum value: RaceListEnum.values()) {
            raceList.add(configureEnumToString(value.toString()));
        }

        ArrayList<String> levelList = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            levelList.add(Integer.toString(i));
        }

        ArrayList<String> classList = new ArrayList<>();
        for(PlayerClassEnum playerClass: PlayerClassEnum.values()) {
            classList.add(configureEnumToString(configureEnumToString(playerClass.toString())));
        }

        mRacePickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, raceList);
        mLevelPickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, levelList);
        mClassPickerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, classList);
    }

    /**
     * sets a CardView's TextView to the text of the selected item of a ListView
     * @param view the view of the selected item in a ListView
     * @param textViewChosen TextView that will display the selected item's text
     * @param listViewToClose The ListView that will be made Visibility.Gone
     * @param viewForTransition the view to use the TranstionManager on, if this is null then no TransitionManager will be used
     * @return returns the text of the selected item
     */
    private String onListItemSelected(View view, TextView textViewChosen, ListView listViewToClose, @Nullable ViewGroup viewForTransition) {
        // grab text of the selected item and save as a string
        TextView textViewSelected = (TextView) view;
        String textViewString = String.valueOf(textViewSelected.getText());

        // set the CardView's selected text to the selected item
        textViewChosen.setText(textViewString);

        // Make selected TextView visible and and the ListView gone
        textViewChosen.setVisibility(View.VISIBLE);
        listViewToClose.setVisibility(View.GONE);

        if (viewForTransition != null) {
            TransitionManager.beginDelayedTransition(viewForTransition);
        }

        return textViewString;
    }

    /**
     * Closes a previously opened list view if a new list view is opened
     * @param openingListView the ListView that is currently being opened.
     */
    private void closeOtherListViews(ListView openingListView) {
        for (ListView view : mPickerListViews) {
            // if there is a list view open that is not the list view being opened then make it gone
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
