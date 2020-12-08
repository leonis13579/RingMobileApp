package ru.realityfamily.ringapp.Logic;

import android.content.Context;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.ringapp.Network.SingletonRetrofit;

public class RingSettings {

    public static void RingOn(Context context, String id) {
        SingletonRetrofit.getINSTANCE().getRest().ringOn(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Состояние звука звонка включено", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void RingOff(Context context, String id) {
        SingletonRetrofit.getINSTANCE().getRest().ringOff(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Состояние звука звонка выключено", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
