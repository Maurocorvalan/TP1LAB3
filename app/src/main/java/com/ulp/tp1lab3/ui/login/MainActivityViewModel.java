// MainActivityViewModel.java
package com.ulp.tp1lab3.ui.login;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ulp.tp1lab3.models.User;
import com.ulp.tp1lab3.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Application application;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public Intent handleLogin(String mail, String password) {
        boolean loginSuccessful = login(mail, password);
        if (loginSuccessful) {
            String savedMail = getUserMail();
            Log.d("MainActivityViewModel", "Inicio de sesión exitoso para: " + savedMail);
            Intent intent = prepareIntentForRegistration(true, savedMail);
            User userDetails = getUserDetails(mail); // Obtiene los detalles del usuario
            intent.putExtra("user", userDetails); // Agrega los detalles del usuario al intent
            return intent;
        } else {
            Log.d("MainActivityViewModel", "Inicio de sesión fallido para: " + mail);
            return null;
        }
    }

    private boolean login(String mail, String password) {
        SharedPreferences sp = application.getSharedPreferences("users.xml", 0);
        String savedMail = sp.getString("mail", "");
        String savedPassword = sp.getString("password", "");
        return mail.equals(savedMail) && password.equals(savedPassword);
    }

    private String getUserMail() {
        SharedPreferences sp = application.getSharedPreferences("users.xml", 0);
        return sp.getString("mail", "");
    }

    private User getUserDetails(String mail) {
        SharedPreferences sp = application.getSharedPreferences("users.xml", 0);
        String dni = sp.getString("dni", "");
        String apellido = sp.getString("apellido", "");
        String nombre = sp.getString("nombre", "");
        String password = sp.getString("password", "");
        return new User(dni, apellido, nombre, mail, password);
    }

    private Intent prepareIntentForRegistration(boolean isLogin, String mail) {
        Intent intent = new Intent(application, RegistroActivity.class);
        intent.putExtra("isLogin", isLogin);
        intent.putExtra("mail", mail);
        return intent;
    }

}
