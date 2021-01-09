package com.example.animeapplication.presentation.animedisplay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.animeapplication.R;
import com.example.animeapplication.presentation.animedisplay.favorite.fragment.FavoriteFragment;
import com.example.animeapplication.presentation.animedisplay.search.fragment.SearchFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnimeDisplayActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    /**
     * Lors de la création de l"activité
     * @param savedInstanceState l'étât sauvegarder de l'instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    /**
     * mets en place le viewpager et le tablayout
     */
    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);
        tabLayout = findViewById(R.id.tablayout);

        final SearchFragment searchFragment = SearchFragment.newInstance();
        final FavoriteFragment fragmentTwo = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentStateAdapter(this) {

            /**
             *
             * @param position position du fragment. O pour SearchFragment et 1 pour FavoriteFragment
             * @return le Fragment à la position donnée
             */
            @Override
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return searchFragment;
                }
                return fragmentTwo;
            }

            /**
             * Non utilisé
             */
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return SearchFragment.TAB_NAME;
                }
                return FavoriteFragment.TAB_NAME;
            }

            /**
             *
             * @return le nombre de fragment
             */
            @Override
            public int getItemCount() {
                return 2;
            }
        });
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            /**
             *
             * @param tab le tab du tablayout
             * @param position position 0 ou 1 selon le fragment
             */
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(position == 0 ? SearchFragment.TAB_NAME : FavoriteFragment.TAB_NAME);
            }
        });
        tabLayoutMediator.attach();
    }
}
