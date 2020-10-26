package com.charalito.petagramv2.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.charalito.petagramv2.R;
import com.charalito.petagramv2.email.SendMail;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables para los EditText
    private EditText etCorreo;
    private EditText etNombre;
    private EditText etMensaje;

    //Variable para el botón
    private Button btnEnviar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        // Creo el action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //Inicializando las vistas
        etCorreo  = (EditText) findViewById(R.id.etCorreo);
        etNombre  = (EditText) findViewById(R.id.etNombre);
        etMensaje = (EditText) findViewById(R.id.etMensaje);

        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        sendEmail();
    }

    private void sendEmail(){
        //Obteniendo contenido para email
        String email = etCorreo.getText().toString().trim();
        String subject = etNombre.getText().toString().trim();
        String message = etMensaje.getText().toString().trim();

        //Creaando objeto SendMail
        SendMail sm = new SendMail(this, email,subject,message);

        //Ejecutando sendmail para enviar correo
        sm.execute();

    }

}
