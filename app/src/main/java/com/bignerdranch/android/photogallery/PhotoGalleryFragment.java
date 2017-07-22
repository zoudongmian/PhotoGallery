package com.bignerdranch.android.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/21.
 */

public class PhotoGalleryFragment extends Fragment {
    private RecyclerView mPhotoRecyclerView;
    private  static final String TAG = "PhotoGalleryFragment";
    private class FetchItemsTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
           try {
               String result = new FlickrFetcher()
                       .getUrlString("https://bignerdranch.com");
               Log.i(TAG,"Fetched contents of URL: " +result);
           }catch (IOException ioe){
               Log.e(TAG,"Failed to fetch URL: " ,ioe);
           }
            return null;
        }
    }
    public static PhotoGalleryFragment newInstance(){
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_photo_gallery,container,false);
        mPhotoRecyclerView = (RecyclerView)v.findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        return v;
    }
}
