package com.example.mohamad.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

       GridView gridview = (GridView) rootView.findViewById(R.id.movies_GridView);
        if (gridview != null) {
            gridview.setAdapter(new MovieAdapter());

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    startActivity(intent);
                }
            });
        }
       return rootView; //inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.Refresh) {
            Network network = new Network();
            network.execute();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    public class Network extends AsyncTask<String, Integer, List<Movie>> {

        ImageView imageView;
        MovieAdapter movieAdapter;

        public Network(ImageView imageView, MovieAdapter movieAdapter) {
            this.imageView = imageView;
            this.movieAdapter = movieAdapter;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected List<Movie> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            String baseUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
            String MovieJsonString = null;
            Uri uri = Uri.parse(baseUrl).buildUpon().appendQueryParameter("api_key", BuildConfig.MOVIE_APP_API_KEY).build();

            try {
                URL url = new URL(uri.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));


                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                MovieJsonString = buffer.toString();
            } catch (IOException e) {
                Log.e("MainActivityFragment", "Error ", e);
                return null;
            } finally {

                if (connection != null) {
                    connection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivityFragment", "Error closing stream", e);
                    }
                }
            }


            //I think there is a method that implements this code
            try {

                JSONObject object = new JSONObject(MovieJsonString);
                JSONArray result = object.getJSONArray("results");

                for (int i = 0; i < result.length(); i++) {
                    JSONObject object1 = result.getJSONObject(i);
                    String poster = object1.getString("poster_path");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Gson gson = new GsonBuilder().create();
            Movie movie = new Movie();

            movie = gson.fromJson(MovieJsonString, Movie.class);
            return null;
        }


        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            if (movies != null) {
                ArrayList<Movie> mMovies = new ArrayList<>();
                mMovies.addAll(movies);
            }
        }
    }


}

