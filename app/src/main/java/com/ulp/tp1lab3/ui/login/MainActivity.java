// MainActivity.java
package com.ulp.tp1lab3.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.tp1lab3.databinding.ActivityMainBinding;
import com.ulp.tp1lab3.models.User;
import com.ulp.tp1lab3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();
                Intent intent = vm.handleLogin(mail, password);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuración del botón de registro
        binding.btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User usuarioNuevo = new User("", "", "", "", ""); // Objeto de usuario vacío
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                intent.putExtra("isLogin", false); // Indica creación de nuevo usuario
                intent.putExtra("user", usuarioNuevo);
                startActivity(intent);
            }
        });
    }
}
