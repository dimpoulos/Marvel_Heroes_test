package com.codehub.marvelheroesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codehub.marvelheroesapp.Common;
import com.codehub.marvelheroesapp.DDatabase.ModelDB.Favorite;
import com.codehub.marvelheroesapp.ELEOS.FavDB;
import com.codehub.marvelheroesapp.Fragments.FirstFragment;
import com.codehub.marvelheroesapp.ItemDetails;
import com.codehub.marvelheroesapp.R;
import com.codehub.marvelheroesapp.json.HeroesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<HeroesModel> heroes;
    private List<HeroesModel> heroesListFull; //for search
    private FavDB favDB;
//    CallbackManager callbackManager;
//    ShareDialog shareDialog;


    public Adapter(Context ctx, List<HeroesModel> heroes) {
        this.inflater = LayoutInflater.from(ctx);
        this.heroes = heroes;
        heroesListFull = new ArrayList<>(heroes);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String title = heroes.get(position).getName();
        final String description = heroes.get(position).getDescription();
        final String image = heroes.get(position).getThumbnail().getPath() + ".jpg";
//        final int numOfComics = heroes.get(position).getComics().getAvailable();
//        final int numOfSeries = heroes.get(position).getSeries().getAvailable();


//        readCursorData(heroesModel, holder){
//            holder.image.setImageResource(heroesModel.getThumbnail());
//
//        }

        holder.Name.setText(title);
//        holder.NumOfComics.setText("Comics: " + numOfComics);
//        holder.NumOfSeries.setText("Series: " + numOfSeries);
        Picasso.get().load(image).into(holder.image);


        //set onClickListener to item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Intent
                Intent intent = new Intent(v.getContext(), ItemDetails.class);
                intent.putExtra("title", title);//sending title of "custom of list view"
                intent.putExtra("subtitle", description);
                intent.putExtra("image", image);
                v.getContext().startActivity(intent);
            }
        });





//        //set onCLickListener to heart icon
//        holder.add_to_fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //change color of heart when you click on it
//                holder.add_to_fav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
//            }
//        });
//
    }



    @Override
    public int getItemCount() {
        return heroes.size();
    }

    //ViewHolder creation
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, subtitle;
        ImageView image;
        ImageButton add_to_fav, share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.thumbnail);
            add_to_fav = itemView.findViewById(R.id.heart);
            share = itemView.findViewById(R.id.share);

        }
    }

    //31/5/2020 For Searching...
    public void filterList(List<HeroesModel> filteredList) {
        heroes = filteredList;
        notifyDataSetChanged();
    }



}
