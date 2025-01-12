package com.sindhura.samsunggallery.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sindhura.samsunggallery.R;
import com.sindhura.samsunggallery.adapters.PhotoAdapter;
import com.sindhura.samsunggallery.utils.PhotoUtils;

/**
 * Created by Sindhura on 3/29/2017.
 */

public class PicturesActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        PhotoUtils.updateScreenWidth(this);

        //Collapsing toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Display the back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initialize recyclerView to show photos
        RecyclerView rvPhotos = (RecyclerView) findViewById(R.id.rvPhoto);

        //Get name of the album from which the photos are to be displayed
        if (getIntent().hasExtra(getResources().getString(R.string.KEY_ALBUM))) {
            String albumName = getIntent().getExtras().getString(getResources().getString(R.string.KEY_ALBUM), null);
            if (albumName != null) {
                //Initialize recyclerView with vertical linear layout
                rvPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                //Populate photos from the album in assets
                rvPhotos.setAdapter(new PhotoAdapter(albumName, PhotoUtils.getAssetFiles(this, getResources().getString(R.string.KEY_FOLDER) + "/" + albumName)));
            }
        }
    }


    //Define behaviour of pressing the back arrow in the toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
