package com.example.animeapplication.presentation.animedisplay.search.adapter;

import com.example.animeapplication.R;

import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {



    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView nbEpisodesTextView;
        private ImageView iconImageView;
        private View v;
        private AnimeItemViewModel animeItemViewModel;
        private AnimeActionInterface animeActionInterface;
        private Switch favoriteSwitch;
        private SelectedAnime selectedAnime;



        public AnimeViewHolder(View v, final AnimeActionInterface animeActionInterface, final SelectedAnime selectedAnime) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.anime_title_textview);
            nbEpisodesTextView = v.findViewById(R.id.anime_nb_episodes_textview);
            iconImageView = v.findViewById(R.id.anime_icon_imageview);
            /*favoriteSwitch = v.findViewById(R.id.favorite_switch);*/
            this.animeActionInterface = animeActionInterface;
            this.selectedAnime = selectedAnime;
            //setupListeners();
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ici le id : ", animeItemViewModel.getSynopsis());
                    selectedAnime.selectedAnime(animeItemViewModel);
                }
            });


        }



       /*private void setupListeners() {
           /* favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    animeActionInterface.onFavoriteToggle(animeItemViewModel.getAnimeId(), b);
                }
            });
        }*/

        void bind(AnimeItemViewModel animeItemViewModel) {
            this.animeItemViewModel = animeItemViewModel;
            titleTextView.setText(animeItemViewModel.getTitle());
            nbEpisodesTextView.setText("Nombre d'épisode(s) : "+String.valueOf(animeItemViewModel.getNbEpisodes()));
            /*favoriteSwitch.setChecked(animeItemViewModel.isFavorite());*/
            Glide.with(v)
                    .load(animeItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);
        }


    }

    public List<AnimeItemViewModel> animeItemViewModelList;
    private AnimeActionInterface animeActionInterface;
    private SelectedAnime selectedAnime;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AnimeAdapter(AnimeActionInterface animeActionInterface, SelectedAnime selectedAnime) {
        animeItemViewModelList = new ArrayList<>();
        this.animeActionInterface = animeActionInterface;
        this.selectedAnime = selectedAnime;
    }

    public void bindViewModels(List<AnimeItemViewModel> animeItemViewModelList) {
        this.animeItemViewModelList.clear();
        this.animeItemViewModelList.addAll(animeItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anime, parent, false);
        AnimeViewHolder animeViewHolder = new AnimeViewHolder(v, animeActionInterface, selectedAnime);
        return animeViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        holder.bind(animeItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return animeItemViewModelList.size();
    }

    public interface SelectedAnime{
        void selectedAnime(AnimeItemViewModel animeItemViewModel);
    }
}

