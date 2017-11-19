package com.example.icastillo.intentemail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private static final String PATTERN_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
    EditText correoOrigen;
    //EditText correoDestino;
    EditText texto;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correoOrigen=(EditText) findViewById(R.id.correoOrigen);
       // correoDestino=(EditText) findViewById(R.id.correoDstino);
        texto=(EditText) findViewById(R.id.texto);
        btnSend=(Button) findViewById(R.id.button);


        btnSend.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(validaCampos()){
            sendEmail();
        }
    }


    /**
     * Proposito: Valida que los todos los campos de correo electrónico sean correctos y el texto  aenviar no esté vacío
     * Precondiciones: No hay
     * Entradas: No hay
     * Salidas: Un booleano
     * Postcondiciones: El booleano será verdadero si los campos de correo elctrónico y el texto a enviar son correctos false sino
     */
    public boolean validaCampos(){
        boolean valido=false;

        if(validateEmail(correoOrigen.getText().toString()) /*&& validateEmail(correoDestino.getText().toString())*/ && texto.getText().toString().replace(" ", "").length()>0){
            valido=true;
        }
        return valido;
    }

    /**
     * Proposito: Validate given email with regular expression.
     * Precondiciones: La cadena no estará vacía
     * Entradas: Una cadena
     * Salidas: Un booleano
     * Postcondiciones: El booleano será verdadero si la cadena cumple la expresión regular false sino
     */
    /*public boolean validateEmail(String email) {
        boolean vale;
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        vale=matcher.matches();
        return vale;
    }*/



    public static boolean validateEmail(String emailStr) {
        boolean vale;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        vale=matcher.find();
        return vale;
    }

    public void sendEmail() {
       // Log.i("Send email", "");
        //String[] TO = {"ivancastillocalle@gmail.com"};
        //String[] CC = {"@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, correoOrigen.getText().toString());
        //emailIntent.putExtra(Intent.EXTRA_CC, correoOrigen.getText().toString());
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, texto.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            //Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
