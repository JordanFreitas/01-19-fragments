package edu.uw.fragmentdemo;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MovieActivity extends Activity implements MovieFragment.OnMovieSelectionListener {

    private static final String TAG = "MovieActivity";

    private ArrayAdapter<Movie> adapter; //adapter for list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"The banker" person who can move around and also tell you whats inside
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.container, new MovieFragment());
        ft.commit();


    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment detail = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title", movie.toString());
        bundle.putString("imdb", movie.imdbId);


        detail.setArguments(bundle);
            //can just use . instead of calling each time
    getFragmentManager().beginTransaction()
            .replace(R.id.container, detail)
            .addToBackStack(null)
            .commit();
    }
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount()!=0) {
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}

