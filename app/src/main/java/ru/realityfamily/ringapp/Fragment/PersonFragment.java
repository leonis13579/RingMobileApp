package ru.realityfamily.ringapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.ringapp.MainActivity;
import ru.realityfamily.ringapp.R;

public class PersonFragment extends MyFragment{

    public PersonFragment(String Title) {
        this.Title = Title;
    }

    public PersonFragment() { this.Title = "Личный кабинет"; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.person_fragment, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.changeBackButton(MainActivity.BackButtonStatus.back);

        Button exitButton = v.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.clearHistory();
                ma.changeFragment(new AuthFragment());
            }
        });

        return v;
    }
}
