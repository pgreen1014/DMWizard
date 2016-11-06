package com.philipgreen.dmwizard.ui.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.philipgreen.dmwizard.R;

/**
 * Created by pgreen on 11/6/16.
 *
 * Programmatically defined LinearLayout containing a level picker CardView and class picker CardView. This class
 * is intended to allow the dynamically adding of level and class pickers to the character creation process.
 */

public class LevelAndClassPickerView extends LinearLayout {

    public LevelAndClassPickerView(Context context) {
        super(context);
        setAttributes();

        LevelPicker levelPicker = new LevelPicker(context);
        this.addView(levelPicker, 0);

        ClassPicker classPicker = new ClassPicker(context);
        this.addView(classPicker, 1);
    }

    private void setAttributes() {
        this.setOrientation(HORIZONTAL);
        this.setId(View.generateViewId());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 0, 16, 16);
        this.setLayoutParams(params);
    }

    /**
     * Private inner CardView class representing a LevelPicker
     */
    private class LevelPicker extends CardView {

        public LevelPicker(Context context) {
            super(context);
            setLevelPickerAttributes();
            this.addView(createChosenTextView(context), 0);
            this.addView(createListViewChoser(context), 1);
        }

        private void setLevelPickerAttributes() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 4.0f);

            this.setUseCompatPadding(true);

            this.setLayoutParams(params);
        }

        private TextView createChosenTextView(Context context) {
            TextView chosenTextView = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            chosenTextView.setLayoutParams(params);

            chosenTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            chosenTextView.setPadding(0, 10, 0, 10);
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

        public ClassPicker(Context context) {
            super(context);
            setClassPickerAttributes();
            this.addView(createChosenTextView(context), 0);
            this.addView(createListViewChoser(context), 1);
        }

        private void setClassPickerAttributes() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            params.setMarginStart(16);

            this.setUseCompatPadding(true);

            this.setLayoutParams(params);
        }

        private TextView createChosenTextView(Context context) {
            TextView chosenTextView = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            chosenTextView.setLayoutParams(params);

            chosenTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            chosenTextView.setPadding(0, 10, 0, 10);
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
