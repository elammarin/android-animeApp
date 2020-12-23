package com.example.animeapplication.presentation.animedisplay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.animeapplication.R;
import com.example.animeapplication.presentation.animedisplay.favorite.fragment.FavoriteFragment;
import com.example.animeapplication.presentation.animedisplay.search.fragment.SearchFragment;

public class AnimeDisplayActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final SearchFragment searchFragment = SearchFragment.newInstance();
        final FavoriteFragment fragmentTwo = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return searchFragment;
                }
                return fragmentTwo;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return SearchFragment.TAB_NAME;
                }
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
