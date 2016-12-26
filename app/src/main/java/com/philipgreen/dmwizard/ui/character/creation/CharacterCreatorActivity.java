package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.philipgreen.dmwizard.R;

import java.util.ArrayList;

public class CharacterCreatorActivity extends AppCompatActivity {

    private final ArrayList<Fragment> characterCreationFragmentList = new ArrayList<Fragment>() {
        {
            add(new RacePickerFragment());
            add(new ClassPickerFragment());
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creator);

        getSupportActionBar().setTitle("Character Creation");

        CharacterCreatorPagerAdapter pagerAdapter = new CharacterCreatorPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager_character_creator);
        viewPager.setAdapter(pagerAdapter);
    }

    public class CharacterCreatorPagerAdapter extends FragmentPagerAdapter {

        public CharacterCreatorPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return characterCreationFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return characterCreationFragmentList.size();
        }
    }

}
