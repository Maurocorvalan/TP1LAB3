package com.ulp.tp1lab3.ui.registro;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ulp.tp1lab3.models.User;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<User> userMutable = new MutableLiveData<>();
    private Application application;
    private boolean cargarDatosUsuarioExistente = true;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        if (cargarDatosUsuarioExistente) {
            cargarUsuarioExistente();
        }
    }
    public void setCargarDatosUsuarioExistente(boolean cargar) {
        this.cargarDatosUsuarioExistente = cargar;
    }

    public LiveData<User> getUserMutable() {
        return userMutable;
    }

    private void cargarUsuarioExistente() {
        // Obtener los datos del usuario existente desde SharedPreferences
        SharedPreferences sp = application.getSharedPreferences("users.xml", 0);
        String dni = sp.getString("dni", "");
        String apellido = sp.getString("apellido", "");
        String nombre = sp.getString("nombre", "");
        String mail = sp.getString("mail", "");
        String password = sp.getString("password", "");

        // Crear un objeto User con los datos del usuario existente
        User existingUser = new User(dni, apellido, nombre, mail, password);

        // Actualizar el LiveData con los datos del usuario existente
        userMutable.setValue(existingUser);
    }

    public void guardarUsuario(User user) {
        // Guardar los datos del usuario en SharedPreferences
        SharedPreferences sp = application.getSharedPreferences("users.xml", 0);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("dni", user.getDni());
        editor.putString("apellido", user.getApellido());
        editor.putString("nombre", user.getNombre());
        editor.putString("mail", user.getMail());
        editor.putString("password", user.getPassword());
        editor.apply();

        // Mostrar un mensaje de confirmación en el Log
        String message = "Datos del usuario guardados: " + user.toString();
        Log.d("salida", message);
    }
}