package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.races.utils.RaceListEnum;
import com.philipgreen.dmwizard.races.utils.RaceListManager;
import com.philipgreen.dmwizard.utils.UnitConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgreen on 11/27/16.
 */

public class RacePickerFragment extends Fragment {
    private RecyclerView mRacePickerRecyclerView;
    private RaceListAdapter mAdapter;
    private List<String> mRaces = new ArrayList<>();
    private Fragment mRacePicker = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_picker, container, false);

        mRacePickerRecyclerView = (RecyclerView) view.findViewById(R.id.racePicker_recyclerView);
        mRacePickerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        createRaceList();
        updateUI();

        return view;
    }

    private void createRaceList() {
        for (String race : RaceListManager.getRaceListForUIPresentation()) {
            mRaces.add(race);
        }
    }

    private void updateUI() {
        mAdapter = new RaceListAdapter(mRaces);
        mRacePickerRecyclerView.setAdapter(mAdapter);
    }

    public class RaceListHolder extends RecyclerView.ViewHolder {
        TextView mRaceItemTextView;
        private View mItemView;

        RaceListHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mRaceItemTextView = (TextView) itemView.findViewById(R.id.list_item_race_text_view);
        }

        void setBottomMarginTo16() {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
            int margin16DP = UnitConverter.convertPixelsToDP(mRacePicker, 16);
            params.setMargins(margin16DP, margin16DP, margin16DP, margin16DP);
        }

    }

    private class RaceListAdapter extends RecyclerView.Adapter<RaceListHolder> {
        private List<String> racesToDisplay;

        RaceListAdapter(List<String> races) {
            racesToDisplay = races;
        }

        @Override
        public RaceListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_race, parent, false);
            return new RaceListHolder(view);
        }

        @Override
        public void onBindViewHolder(RaceListHolder holder, int position) {
            holder.mRaceItemTextView.setText(racesToDisplay.get(position));

            if (isLastPosition(position)) {
                holder.setBottomMarginTo16();
            }
        }

        @Override
        public int getItemCount() {
            return racesToDisplay.size();
        }

        private boolean isLastPosition(int position) {
            if (position == racesToDisplay.size() -1) {
                return true;
            } else {
                return false;
            }
        }
    }

}
