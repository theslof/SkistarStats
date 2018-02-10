package se.theslof.skistarstats.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class APIClient {
    private static final String baseURL = "https://www.skistar.com/myskistar/game/api/v3/";
    private static Retrofit client = null;

    private APIClient(){}

    public static Retrofit getClient(){
        if(client == null){
            client = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return client;
    }

    public static SkistarAPIService getSkistarService(){
        return getClient().create(SkistarAPIService.class);
    }
}
