package com.test.multilanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String currentLanguage = "";
    private static final String LANGUAGE_KEY = "language_key";
    static final String ENGLISH = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_english).setOnClickListener(this);
        findViewById(R.id.btn_hindi).setOnClickListener(this);
        findViewById(R.id.btn_spanish).setOnClickListener(this);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentLanguage = mPreferences.getString(LANGUAGE_KEY, ENGLISH);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_english:
                if (!currentLanguage.contentEquals(LocaleManager.ENGLISH))
                    setNewLocale(this, LocaleManager.ENGLISH);
                else
                    Toast.makeText(this, getString(R.string.language_error), Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_hindi:
                if (!currentLanguage.contentEquals(LocaleManager.HINDI))
                    setNewLocale(this, LocaleManager.HINDI);
                else
                    Toast.makeText(this, getString(R.string.language_error), Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_spanish:
                if (!currentLanguage.contentEquals(LocaleManager.SPANISH))
                    setNewLocale(this, LocaleManager.SPANISH);
                else
                    Toast.makeText(this, getString(R.string.language_error), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
