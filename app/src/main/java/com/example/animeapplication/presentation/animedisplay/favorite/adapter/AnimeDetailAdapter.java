package com.example.animeapplication.presentation.animedisplay.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.animeapplication.R;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimeDetailAdapter extends RecyclerView.Adapter<AnimeDetailAdapter.AnimeDetailViewHolder> {


    public static class AnimeDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView endingTextView;
        private TextView nbEpisodesTextView;
        private TextView descriptionTextView;
        private TextView launchingTextView;
        private ImageView iconImageView;
        private View v;
        private AnimeItemViewModel animeItemViewModel;
        private AnimeDetailActionInterface animeDetailActionInterface;
        private Switch favoriteSwitch;

        public AnimeDetailViewHolder(View v, final AnimeDetailActionInterface animeDetailActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.anime_title_textview);
            nbEpisodesTextView = v.findViewById(R.id.anime_nb_episodes_textview);
            descriptionTextView = v.findViewById(R.id.synopsis);
            launchingTextView = v.findViewById(R.id.launching_date);
            endingTextView = v.findViewById(R.id.ending_date);
            iconImageView = v.findViewById(R.id.anime_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            setupListeners();
            this.animeDetailActionInterface = animeDetailActionInterface;
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        animeDetailActionInterface.onRemoveFavorite(animeItemViewModel.getAnimeId());
                    }
                }
            });
        }

        void bind(AnimeItemViewModel animeItemViewModel) {
            this.animeItemViewModel = animeItemViewModel;
            titleTextView.setText(animeItemViewModel.getTitle());
            endingTextView.setText(animeItemViewModel.getEndingDate());
            //nbEpisodesTextView.setText(animeItemViewModel.getNbEpisodes());
            descriptionTextView.setText(animeItemViewModel.getSynopsis());
            favoriteSwitch.setChecked(true);
            launchingTextView.setText(animeItemViewModel.getLaunchingDate());
            Glide.with(v)
                    .load(animeItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);
        }

    }

    private List<AnimeItemViewModel> animeItemViewModelList;
    private AnimeDetailActionInterface animeDetailActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AnimeDetailAdapter(AnimeDetailActionInterface animeDetailActionInterface) {
        animeItemViewModelList = new ArrayList<>();
        this.animeDetailActionInterface = animeDetailActionInterface;
    }

    public void bindViewModels(List<AnimeItemViewModel> animeItemViewModelList) {
        this.animeItemViewModelList.clear();
        this.animeItemViewModelList.addAll(animeItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AnimeDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_anime, parent, false);
        AnimeDetailViewHolder animeDetailViewHolder = new AnimeDetailViewHolder(v, animeDetailActionInterface);
        return animeDetailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AnimeDetailViewHolder holder, int position) {
        holder.bind(animeItemViewModelList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return animeItemViewModelList.size();
    }

}