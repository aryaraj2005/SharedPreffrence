package com.example.languagechangeapsharedpreferrendcedexample.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.languagechangeapsharedpreferrendcedexample.R;
import com.example.languagechangeapsharedpreferrendcedexample.ui.login.LoginViewModel;
import com.example.languagechangeapsharedpreferrendcedexample.ui.login.LoginViewModelFactory;
import com.example.languagechangeapsharedpreferrendcedexample.databinding.ActivityLoginBinding;

import java.util.Locale;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

  
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         loadLocale();// isko kisi bhi method me rakh sakte haai
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.chnglang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });
    }

    private void ChangeLanguage() {
        final String [] language = {"English" ,"Hindi" , "Bengali" ," Urdu"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Change language");
        mBuilder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             if (i==0){
                 setlocal("");
                 recreate();//when we choose any language then our activity get recreated;
             }

               else if (i==1){
                    setlocal("hi");
                    recreate();//when we choose any language then our activity get recreated;
                }
                else if (i==2){
                    setlocal("bn");
                    recreate();//when we choose any language then our activity get recreated;
                }
                else if (i==3){
                    setlocal("ur");
                    recreate();//when we choose any language then our activity get recreated;
                }
            }
        });
        mBuilder.create();
        mBuilder.show();


    }

    private void setlocal(String language) {
        Locale locale = new Locale(language);  // used to get all language
        Locale.setDefault(locale);     // we use its default value
        Configuration configuration = new Configuration();
        configuration.locale  =locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics()); // jaise hum language ko
        // cahnge karenge sare lamnguage update ho jayegenge

        // Shared prefferenced ka use karenge for remain the language should be saved'
         SharedPreferences.Editor editor = getSharedPreferences("Setting" , MODE_PRIVATE).edit();
         editor.putString("change_lang" , language);
         editor.apply();
    }
    private void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Setting",MODE_PRIVATE );
       String language= preferences.getString("change_lang" ,"");
        setlocal(language);

    }

}