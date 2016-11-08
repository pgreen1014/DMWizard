package com.philipgreen.dmwizard.ui.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.utils.UnitConverter;

/**
 * Created by pgreen on 11/6/16.
 *
 * Programmatically defined LinearLayout containing a level picker CardView and class picker CardView. This class
 * is intended to allow the dynamically adding of level and class pickers to the character creation process.
 */

public class LevelAndClassPickerView extends LinearLayout {

    public LevelAndClassPickerView(Context context, Fragment fragment) {
        super(context);
        setAttributes();

        LevelPicker levelPicker = new LevelPicker(context, fragment);
        this.addView(levelPicker, 0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        int margin = UnitConverter.convertPixelsToDP(fragment, 16);
        params.setMarginStart(margin);
        ClassPicker classPicker = new ClassPicker(context, fragment);
        this.addView(classPicker, 1, params);
    }

    private void setAttributes() {
        this.setOrientation(HORIZONTAL);
        this.setId(View.generateViewId());
    }

    /**
     * Private inner CardView class representing a LevelPicker
     */
    private class LevelPicker extends CardView {

        public LevelPicker(Context context, Fragment fragment) {
            super(context);
            setLevelPickerAttributes();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            
            this.addView(createChosenTextView(context, fragment), 0, params);
            this.addView(createListViewChoser(context), 1);
        }

        private void setLevelPickerAttributes() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 4.0f);

            this.setUseCompatPadding(true);

            this.setLayoutParams(params);
        }

        private TextView createChosenTextView(Context context, Fragment fragment) {
            TextView chosenTextView = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            chosenTextView.setLayoutParams(params);

            chosenTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            int padding = UnitConverter.convertPixelsToDP(fragment, 10);
            chosenTextView.setPadding(0, padding, 0, padding);
            chosenTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            chosenTextView.setTag("chosenItem");
            chosenTextView.setText("1");

            return chosenTextView;
        }

        private ListView createListViewChoser(Context context) {
            ListView listView = new ListView(context);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            listView.setLayoutParams(params);

            listView.setVisibility(GONE);

            return listView;
        }
    }

    /**
     * Private inner CardView class representing a class picker
     */
    private class ClassPicker extends CardView {

        public ClassPicker(Context context, Fragment fragment) {
            super(context);
            setClassPickerAttributes();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;

            this.addView(createChosenTextView(context, fragment), 0, params);
            this.addView(createListViewChoser(context), 1);
        }

        private void setClassPickerAttributes() {

            this.setUseCompatPadding(true);

        }

        private TextView createChosenTextView(Context context, Fragment fragment) {
            TextView chosenTextView = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            chosenTextView.setLayoutParams(params);

            chosenTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            int padding = UnitConverter.convertPixelsToDP(fragment, 10);
            chosenTextView.setPadding(0, padding, 0, padding);
            chosenTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            chosenTextView.setTag("chosenItem");
            chosenTextView.setHint(R.string.choose_class_hint);

            return chosenTextView;
        }

        private ListView createListViewChoser (Context context) {
            ListView listView = new ListView(context);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            listView.setLayoutParams(params);

            listView.setVisibility(GONE);

            return listView;
        }
    }

}
