package com.philipgreen.dmwizard.ui.character.creation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.widget.Toast;

import com.philipgreen.dmwizard.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterCreatorActivity extends AppCompatActivity implements OnCharacterCreationTraitSelectedListener{

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creator);

        mToolbar = (Toolbar) findViewById(R.id.charcter_creator_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.character_creator_view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.character_creator_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void  setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RacePickerFragment(), "Race");
        adapter.addFragment(new ClassPickerFragment(), "Class");
        adapter.addFragment(new AbilityScoreCreationFragment(), "Abilities");
        adapter.addFragment(new CharacterBackgroundSelectionFragment(), "Background");
        adapter.addFragment(new CharacterDescriptionSelectionFragment(), "Description");
        adapter.addFragment(new CharacterEquipmentSelectionFragment(), "Equipment");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCharacterTraitSelected(String trait) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
