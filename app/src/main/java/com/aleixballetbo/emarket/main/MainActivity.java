package com.aleixballetbo.emarket.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewModule;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.helloText)
    TextView helloText;

    @Inject
    MainPresenter presenter;

    @Inject
    @ForActivity
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);

        presenter.onStart();
    }

    @Override
    public void changeText(String text) {
        helloText.setText(text);
    }
}
