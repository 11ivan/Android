package com.example.icastillo.pirdrapapeltijeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textUser;
    TextView errorNombre;
    EditText textPassword;
    TextView errorPass;
    Button btnLogin;
    Button btnRegistro;
    Player player=new Player();
    UtilFicheros utilFicheros=new UtilFicheros();
    GestoraLoginActivity gestoraLoginActivity=new GestoraLoginActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUser=(EditText) findViewById(R.id.textUser);
        errorNombre=(TextView) findViewById(R.id.errorUser);
        textPassword=(EditText) findViewById(R.id.textPassword);
        errorPass=(TextView) findViewById(R.id.errorPass);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegistro=(Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnLogin:
                    //Comprobar datos en fichero de usuarios
                    //en caso de ser correctos iniciamos mainActivity
                    //sino lanzamos mensaje de error
                    gestionaLogin();
                break;

            case R.id.btnRegister:
                    //Iniciamos actividad de Registro
                    iniciaRegistroActivity();
                break;
        }
    }

    public void gestionaLogin(){
        player.setNombre(textUser.getText().toString());
        player.setPassword(textPassword.getText().toString());

        //Comprobar los datos
        int[] arrayErrores=gestoraLoginActivity.compruebaCamposLogin(this, player);

        //Si no hay errores vamos a MainActivity
        if(!compruebaErrores(arrayErrores)){
            //Ir a MainActivity
            iniciaMainActivity();
        }
    }

    /*
    * Proposito: Inicia la actividad LoginActivity
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha iniciado la actividad LoginActivity
    * */
    public void iniciaMainActivity(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
    * Proposito: Inicia la actividad RegistroActivity
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha iniciado la actividad RegistroActivity
    * */
    public void iniciaRegistroActivity(){
        Intent intent=new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }


    /*
    * Proposito: Comprueba si hay algún error en el array de errores para hacer visible el mensaje de error necesario
    * Precondiciones: No hay
    * Entradas: Un array de enteros que contendrá los errores si los hubiera
    * Salidas: No hay
    * Postcondiciones: Se mostrarán los mensajes correspondientes a cada error si los hubiera
    * */
    public boolean compruebaErrores(int[] arrayErrores){
        boolean hayError=false;

        //Error nombre vacio
        if(arrayErrores[0]==1){
            errorNombre.setText("El nombre no puede estar vacío");
            errorNombre.setVisibility(View.VISIBLE);
            hayError=true;
            //Error nombre ya existe
        }else if(arrayErrores[0]==2){
            errorNombre.setText("El nombre de usuario no existe");
            errorNombre.setVisibility(View.VISIBLE);
            hayError=true;
        }else{//No hay error
            if(errorNombre.getVisibility()== View.VISIBLE){
                errorNombre.setVisibility(View.INVISIBLE);
            }
        }
        //Error password vacio
        if(arrayErrores[1]==1){
            errorPass.setText("Debe introducir el password");
            errorPass.setVisibility(View.VISIBLE);
            if(!hayError){
                hayError=true;
            }
        }else{//No hay error
            if(errorPass.getVisibility()== View.VISIBLE){
                errorPass.setVisibility(View.INVISIBLE);
            }
        }

        if(!hayError) {
            //Error password incorrecto
            if (arrayErrores[2] == 1) {
                errorPass.setText("Password incorrecto");
                errorPass.setVisibility(View.VISIBLE);
                hayError = true;
            } else {//No hay error
                if (errorPass.getVisibility() == View.VISIBLE) {
                    errorPass.setVisibility(View.INVISIBLE);
                }
            }
        }
        return  hayError;
    }



}
