package com.philipgreen.dmwizard.ui.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private ArrayAdapter<String> mLevelAdapter;
    private ArrayAdapter<String> mClassAdapter;

    public LevelAndClassPickerView(Context context, Fragment fragment, ArrayAdapter<String> levelAdapter, ArrayAdapter<String> classAdapter) {
        super(context);
        setAttributes();

        mLevelAdapter = levelAdapter;
        mClassAdapter = classAdapter;

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
        TextView levelText;
        Fragment host;

        public LevelPicker(Context context, final Fragment fragment) {
            super(context);
            setLevelPickerAttributes();
            host = fragment;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;

            levelText = createChosenTextView(context, fragment);

            this.addView(levelText, 0, params);
            this.addView(createListViewChooser(context), 1);

            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    getChildAt(1).setVisibility(VISIBLE);
                    getChildAt(0).setVisibility(GONE);
                    TransitionManager.beginDelayedTransition((ViewGroup) fragment.getView());
                }
            });
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

        private ListView createListViewChooser(Context context) {
            final ListView listView = new ListView(context);
            listView.setAdapter(mLevelAdapter);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            listView.setLayoutParams(params);
            listView.setNestedScrollingEnabled(true);

            listView.setVisibility(GONE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textView = (TextView) view;
                    String itemText = String.valueOf(textView.getText());
                    levelText.setText(itemText);
                    listView.setVisibility(GONE);
                    levelText.setVisibility(VISIBLE);
                    TransitionManager.beginDelayedTransition((ViewGroup) host.getView());
                }
            });

            return listView;
        }
    }

    /**
     * Private inner CardView class representing a class picker
     */
    private class ClassPicker extends CardView {
        private TextView classSelectedText;
        private Fragment host;

        public ClassPicker(Context context, final Fragment fragment) {
            super(context);
            setClassPickerAttributes();

            host = fragment;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;

            classSelectedText = createChosenTextView(context, fragment);

            this.addView(classSelectedText, 0, params);
            this.addView(createListViewChooser(context), 1);

            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    getChildAt(1).setVisibility(VISIBLE);
                    getChildAt(0).setVisibility(GONE);
                    TransitionManager.beginDelayedTransition((ViewGroup) fragment.getView());
                }
            });
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

        private ListView createListViewChooser(Context context) {
            final ListView listView = new ListView(context);

            listView.setAdapter(mClassAdapter);
            
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            listView.setLayoutParams(params);
            listView.setNestedScrollingEnabled(true);

            listView.setVisibility(GONE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textView = (TextView) view;
                    String itemText = String.valueOf(textView.getText());
                    classSelectedText.setText(itemText);
                    listView.setVisibility(GONE);
                    classSelectedText.setVisibility(VISIBLE);
                    TransitionManager.beginDelayedTransition((ViewGroup) host.getView());
                }
            });

            return listView;
        }
    }

}
