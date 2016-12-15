package com.philipgreen.dmwizard.ui.character.creation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.philipgreen.dmwizard.races.utils.RaceListEnum;
import com.philipgreen.dmwizard.races.utils.RaceListManager;
import com.philipgreen.dmwizard.races.utils.SubRaceListEnum;

import java.util.ArrayList;

/**
 * Created by pgreen on 12/13/16.
 */

public class SubRacePickerDialogFragment extends DialogFragment {
    public static final String RACE_KEY = "race";


    static SubRacePickerDialogFragment newInstance(String race) {
        SubRacePickerDialogFragment subRacePickerFragment = new SubRacePickerDialogFragment();

        Bundle args = new Bundle();
        args.putString(RACE_KEY, race);
        subRacePickerFragment.setArguments(args);

        return subRacePickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String selectedRace = getArguments().getString(RACE_KEY);

        ArrayList<String> subRaceListSelection = RaceListManager.getSubRaceListAsString(selectedRace);
        if (subRaceListSelection.size() == 0) {
            this.dismiss();
        }
        ArrayAdapter<String> subRaceListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subRaceListSelection);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setAdapter(subRaceListAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Clicked item " + i, Toast.LENGTH_SHORT).show();
            }
        });

        return dialogBuilder.create();
    }
    
}
