package com.codehub.marvelheroesapp.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codehub.marvelheroesapp.ELEOS.FavDB;
import com.codehub.marvelheroesapp.R;
import com.codehub.marvelheroesapp.json.HeroesModel;
import com.codehub.marvelheroesapp.json.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapt extends RecyclerView.Adapter<HeroAdapt.ViewHolder> {

//    private Integer id;
//    private String name;
//    private String description;
//    private ImageModel thumbnail;
    private ArrayList<HeroesModel> heroesModels;
    private Context context;
    private FavDB favDB;
    private List<HeroesModel> heroes;

    public HeroAdapt(ArrayList<HeroesModel> heroesModels, Context context){
        this.heroesModels = heroesModels;
        this.context =  context;
    }

    public HeroAdapt(Context context, List<HeroesModel> heroes) {
        this.heroes = heroes;
        this.context =  context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view,
                parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HeroesModel heroesModel = heroesModels.get(position);
        
        readCursorData(heroesModel, holder);
//        holder.imageView.setImageResource(heroesModel.getThumbnail());
        holder.titleTextView.setText(heroesModel.getName());

    }



    @Override
    public int getItemCount() {
        return heroesModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.thumbnail);
            titleTextView = itemView.findViewById(R.id.title);
            favBtn = itemView.findViewById(R.id.heart);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    HeroesModel heroesModel = heroesModels.get(position);

                    if (heroesModel.getFavStatus().equals("0")) {
                        heroesModel.setFavStatus("1");
                        favDB.insertIntoTheDatabase(heroesModel.getName(), heroesModel.getThumbnail(),
                                heroesModel.getId(), heroesModel.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    } else {
                        heroesModel.setFavStatus("0");
                        favDB.remove_fav(heroesModel.getId());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(HeroesModel heroesModel, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(heroesModel.getId());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                heroesModel.setFavStatus(item_fav_status);

                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }

}