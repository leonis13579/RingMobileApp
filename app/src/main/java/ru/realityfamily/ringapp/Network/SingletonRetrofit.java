package ru.realityfamily.ringapp.Network;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.realityfamily.ringapp.R;

public class SingletonRetrofit {

    private static SingletonRetrofit INSTANCE;

    public static SingletonRetrofit getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonRetrofit();
        }

        return INSTANCE;
    }

    private SingletonRetrofit() {
    }


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dev.rightech.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RestController rest;

    public RestController getRest() {
        if (rest == null) {
            rest = retrofit.create(RestController.class);
        }

        return rest;
    }
}
