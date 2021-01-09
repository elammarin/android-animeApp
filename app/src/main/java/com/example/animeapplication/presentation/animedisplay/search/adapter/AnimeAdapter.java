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

/**
 * Un adapter pour le fragment dédié à la recherche
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {


    /**
     * un ViewHolder pour Anime
     */
    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView nbEpisodesTextView;
        private ImageView iconImageView;
        private View v;
        private AnimeItemViewModel animeItemViewModel;
        private AnimeActionInterface animeActionInterface;
        private Switch favoriteSwitch;
        private SelectedAnime selectedAnime;


        /**
         * Constructeur du viewHolder
         * @param v la vue
         * @param animeActionInterface l'interface pour les actions à effectuer
         * @param selectedAnime l'animé selectionné
         */
        public AnimeViewHolder(View v, final AnimeActionInterface animeActionInterface, final SelectedAnime selectedAnime) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.anime_title_textview);
            nbEpisodesTextView = v.findViewById(R.id.anime_nb_episodes_textview);
            iconImageView = v.findViewById(R.id.anime_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.animeActionInterface = animeActionInterface;
            this.selectedAnime = selectedAnime;
            setupListeners();
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ici le id : ", animeItemViewModel.getSynopsis());
                    selectedAnime.selectedAnime(animeItemViewModel);
                }
            });


        }

        /**
         * mise en place des listeners
         */
        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    animeActionInterface.onFavoriteToggle(animeItemViewModel.getAnimeId(), b);
                }
            });
        }

        /**
         * defini les champs de texte et les images sur l'écran
         * @param animeItemViewModel le viewModel d'un animé
         */
        void bind(AnimeItemViewModel animeItemViewModel) {
            this.animeItemViewModel = animeItemViewModel;
            titleTextView.setText(animeItemViewModel.getTitle());
            nbEpisodesTextView.setText("Nombre d'épisode(s) : "+String.valueOf(animeItemViewModel.getNbEpisodes()));
            favoriteSwitch.setChecked(animeItemViewModel.isFavorite());
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

    /**
     *
     * @param animeActionInterface Interface pour pouvoir ajouter ou supprimer des favoris un animé
     * @param selectedAnime Interface pour pouvoir lancer l'activité de l'animé selectionné
     */
    public AnimeAdapter(AnimeActionInterface animeActionInterface, SelectedAnime selectedAnime) {
        animeItemViewModelList = new ArrayList<>();
        this.animeActionInterface = animeActionInterface;
        this.selectedAnime = selectedAnime;
    }

    /**
     * Ajoute tout les animesItemiewModel afin de pouvoir ensuite les afficher proprement à l'écran
     * @param animeItemViewModelList
     */
    public void bindViewModels(List<AnimeItemViewModel> animeItemViewModelList) {
        this.animeItemViewModelList.clear();
        this.animeItemViewModelList.addAll(animeItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)

    /**
     *
     * @param parent la vue contenant les differentes vues
     * @param viewType soit un linearLayout ou soit un gridLayout
     * @return un AnimeViewHolder
     */
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

    /**
     *
     * @param holder AnimeViewHolder
     * @param position la position de l'animé
     */
    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        holder.bind(animeItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    /**
     * le nombre d'animé à afficher
     */
    public int getItemCount() {
        return animeItemViewModelList.size();
    }

    /**
     * l'interface permettant d'obtenir l'animé choisi par l'utilisateur
     */
    public interface SelectedAnime{
        void selectedAnime(AnimeItemViewModel animeItemViewModel);
    }
}

