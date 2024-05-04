package com.ulp.tp1lab3.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulp.tp1lab3.models.User;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, User user) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("dni", user.getDni());
        editor.putString("apellido", user.getApellido());
        editor.putString("nombre", user.getNombre());
        editor.putString("mail", user.getMail());
        editor.putString("password", user.getPassword());
        editor.commit();
    }

    public static User leer(Context context) {
        SharedPreferences sp = conectar(context);

        String dni = sp.getString("dni", null);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String mail = sp.getString("mail", "-1");
        String password = sp.getString("password", "-1");
        return new User(dni, apellido, nombre, mail, password);
    }

    public static User login(Context context, String mail, String password) {
        SharedPreferences sp = conectar(context);
        String savedMail = sp.getString("mail", "");
        String savedPassword = sp.getString("password", "");
        if (mail.equals(savedMail) && password.equals(savedPassword)) {
            return leer(context);
        } else {
            return null;
        }
    }
}
