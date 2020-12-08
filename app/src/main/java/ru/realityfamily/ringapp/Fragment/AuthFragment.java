package ru.realityfamily.ringapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.ringapp.MainActivity;
import ru.realityfamily.ringapp.Models.AuthData;
import ru.realityfamily.ringapp.R;
import ru.realityfamily.ringapp.Service.NotifyService;

public class AuthFragment extends MyFragment {

    EditText login;
    EditText password;
    Button enter;
    TextView register;

    public AuthFragment(String Title) { this.Title = Title; }
    public AuthFragment() { this.Title = "Авторизация"; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auth_fragment, container, false);

        login = v.findViewById(R.id.login_login);
        password = v.findViewById(R.id.login_pass);
        enter = v.findViewById(R.id.login_enterButton);
        register = v.findViewById(R.id.reg_advice);

        if (!AuthData.getINSTANCE().getLogin().isEmpty()) {
            Log.d("AuthFragment", AuthData.getINSTANCE().getLogin());
            login.setText(AuthData.getINSTANCE().getLogin());
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new RegisterFragment());
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    AuthData.getINSTANCE().setLogin(login.getText().toString());
                    AuthData.getINSTANCE().setPassword(password.getText().toString());

                    MainActivity ma = (MainActivity) getActivity();
                    ma.clearHistory();
                    ma.changeFragment(new System_Setting());
                }
            }
        });

        return v;
    }
}
