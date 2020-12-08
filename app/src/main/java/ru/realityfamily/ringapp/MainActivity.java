package ru.realityfamily.ringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ru.realityfamily.ringapp.Fragment.AuthFragment;
import ru.realityfamily.ringapp.Fragment.MyFragment;
import ru.realityfamily.ringapp.Fragment.PersonFragment;

public class MainActivity extends AppCompatActivity {

    ImageView appBarBackBtn;
    ImageView appBarExtrBtn;
    TextView appBarTitle;

    BackButtonStatus backButtonStatus;
    public enum BackButtonStatus{
        back,
        person
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarBackBtn = findViewById(R.id.appBarBackBtn);
        appBarExtrBtn = findViewById(R.id.appBarExtrBtn);
        appBarTitle = findViewById(R.id.appBarTitle);

        changeBackButton(BackButtonStatus.back);

        AuthFragment af = new AuthFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, af).addToBackStack(af.Title).commit();
        appBarTitle.setText(af.Title);
    }

    public void changeFragment(MyFragment fragment) {
        appBarTitle.setText(fragment.Title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, fragment).addToBackStack(fragment.Title).commit();
        getSupportFragmentManager().executePendingTransactions();

        if (backButtonStatus == BackButtonStatus.back) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                appBarBackBtn.setVisibility(View.VISIBLE);
            } else {
                appBarBackBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void backFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            String Title = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 2).getName();
            appBarTitle.setText(Title);
        }

        if (fm.getBackStackEntryCount() < 3 && backButtonStatus == BackButtonStatus.back) {
            appBarBackBtn.setVisibility(View.INVISIBLE);
        }
    }

    public MainActivity clearHistory() {
        getSupportFragmentManager().popBackStack(null ,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        return this;
    }

    public void changeBackButton(BackButtonStatus backButtonStatus) {
        this.backButtonStatus = backButtonStatus;
        switch (backButtonStatus) {
            case back:
                appBarBackBtn.setImageResource(R.drawable.back_arrow);
                appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backFragment();
                    }
                });

                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    appBarBackBtn.setVisibility(View.VISIBLE);
                }
                break;

            case person:
                appBarBackBtn.setImageResource(R.drawable.person);
                appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeFragment(new PersonFragment());
                    }
                });

                appBarBackBtn.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                backFragment();
                return false;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}