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
import com.philipgreen.dmwizard.playerClasses.utils.PlayerClassEnum;
import com.philipgreen.dmwizard.utils.UnitConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgreen on 12/25/16.
 */

public class ClassPickerFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ClassListAdapter mAdapter;
    private List<String> mClasses = new ArrayList<>();
    private Fragment mClassPicker = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createClassList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_picker, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.classPicker_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();

        return view;
    }

    private void updateUI() {
        mAdapter = new ClassListAdapter(mClasses);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createClassList() {
        List<String> classList = new ArrayList<>();

        for (PlayerClassEnum classEnum: PlayerClassEnum.values()) {
            classList.add(classEnum.toString());
        }

        mClasses = classList;
    }




    public class ClassListHolder extends RecyclerView.ViewHolder {
        TextView mClassItemTextView;
        private View mItemView;

        public ClassListHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
            mClassItemTextView = (TextView) itemView.findViewById(R.id.list_item_card_text_view);

        }

        void setBottomMarginTo16() {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
            int margin16DP = UnitConverter.convertPixelsToDP(mClassPicker, 16);
            params.setMargins(margin16DP, margin16DP, margin16DP, margin16DP);
        }
    }





    public class ClassListAdapter extends RecyclerView.Adapter<ClassListHolder> {
        private List<String> classesToDisplay;

        public ClassListAdapter(List<String> classes) {
            classesToDisplay = classes;
        }

        @Override
        public ClassListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_card, parent, false);
            return new ClassListHolder(view);
        }

        @Override
        public void onBindViewHolder(ClassListHolder holder, int position) {
            holder.mClassItemTextView.setText(classesToDisplay.get(position));

            if (isLastPosition(position)) {
                holder.setBottomMarginTo16();
            }
        }

        @Override
        public int getItemCount() {
            return classesToDisplay.size();
        }

        private boolean isLastPosition(int position) {
            if (position == classesToDisplay.size() -1) {
                return true;
            } else {
                return false;
            }
        }
    }

}
