package ru.realityfamily.ringapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.ringapp.Adapter.SettingsAdapter;
import ru.realityfamily.ringapp.MainActivity;
import ru.realityfamily.ringapp.Models.CloudObject;
import ru.realityfamily.ringapp.Network.SingletonRetrofit;
import ru.realityfamily.ringapp.R;
import ru.realityfamily.ringapp.Service.NotifyService;

public class System_Setting extends MyFragment {
    Intent service;

    public System_Setting(String Title) { this.Title = Title; }
    public System_Setting() { this.Title = "Управление звонком"; }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = new Intent(getContext(), NotifyService.class);
        getActivity().startService(service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_system__setting, container, false);

        MainActivity ma = ((MainActivity) getActivity());
        ma.changeBackButton(MainActivity.BackButtonStatus.person);

        RecyclerView rv = v.findViewById(R.id.settingsRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        SingletonRetrofit.getINSTANCE().getRest().getAllObjects().enqueue(new Callback<List<CloudObject>>() {
            @Override
            public void onResponse(Call<List<CloudObject>> call, Response<List<CloudObject>> response) {
                rv.setAdapter(new SettingsAdapter(getContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<CloudObject>> call, Throwable t) {

            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().stopService(service);
    }
}