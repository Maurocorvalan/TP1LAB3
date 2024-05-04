// RegistroActivity.java
package com.ulp.tp1lab3.ui.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.tp1lab3.databinding.ActivityRegistroBinding;
import com.ulp.tp1lab3.models.User;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel vm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        setContentView(binding.getRoot());

        // Observar los cambios en los datos del usuario
        vm.getUserMutable().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // Actualizar la vista con los datos del usuario
                binding.etDni.setText(user.getDni());
                binding.etLastName.setText(user.getApellido());
                binding.etName.setText(user.getNombre());
                binding.etMail.setText(user.getMail());
                binding.etPassword.setText(user.getPassword());
            }
        });

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos del usuario de los campos de entrada
                String dni = binding.etDni.getText().toString();
                String apellido = binding.etLastName.getText().toString();
                String nombre = binding.etName.getText().toString();
                String mail = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();

                User user = new User(dni, apellido, nombre, mail, password);

                vm.guardarUsuario(user);

                Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


