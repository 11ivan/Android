package com.example.icastillo.pirdrapapeltijeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textUser;
    TextView errorNombre;
    EditText textPassword;
    TextView errorPassword;
    EditText textConfirmPassword;
    TextView errorConfirmPassword;
    Button btnRegistro;
    Player player=new Player();
    Util util=new Util();
    UtilFicheros utilFicheros=new UtilFicheros();
    GestoraRegistroActivity gestoraRegistroActivity=new GestoraRegistroActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textUser=(EditText) findViewById(R.id.textUserRegistro);
        errorNombre=(TextView) findViewById(R.id.errorNombre);
        textPassword=(EditText) findViewById(R.id.textPasswordRegistro);
        errorPassword=(TextView) findViewById(R.id.errorPassword);
        textConfirmPassword=(EditText) findViewById(R.id.textConfirmPassword);
        errorConfirmPassword=(TextView) findViewById(R.id.errorConfirmPassword);
        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        gestionaRegistro();
    }

    /*
    * Proposito: Gestiona el proceso de registro del Usuario
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones:
    * */
    public void gestionaRegistro(){
        player.setNombre(textUser.getText().toString());
        player.setPassword(textPassword.getText().toString());

        //Comprobar los datos
        int[] arrayErrores=gestoraRegistroActivity.compruebaCamposRegistro(this, player, textConfirmPassword.getText().toString());

        //Si no hay errores escribimos el usuario en el fichero y volvemos a LoginActivity
        if(!compruebaErrores(arrayErrores)){
            //Escribir usuario en fichero
            utilFicheros.writePlayer(this, player);
            //Volver a LoginActivity
            iniciaLoginActivity();
        }
    }

    /*
    * Proposito: Inicia la actividad LoginActivity
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha iniciado la actividad LoginActivity
    * */
    public void iniciaLoginActivity(){
        Intent intent=new Intent(this, LoginActivity.class);
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
            errorNombre.setText("El nombre de usuario ya existe");
            errorNombre.setVisibility(View.VISIBLE);
            hayError=true;
        }else{//No hay error
            if(errorNombre.getVisibility()== View.VISIBLE){
                errorNombre.setVisibility(View.INVISIBLE);
            }
        }
        //Error password vacio
        if(arrayErrores[1]==1){
            errorPassword.setText("El password no puede estar vacío");
            errorPassword.setVisibility(View.VISIBLE);
            if(!hayError){
                hayError=true;
            }
        }else{//No hay error
            if(errorPassword.getVisibility()== View.VISIBLE){
                errorPassword.setVisibility(View.INVISIBLE);
            }
        }

        if(!hayError) {
            //Error confirmacion del password vacio
            if (arrayErrores[2] == 1) {
                errorConfirmPassword.setText("Debe confirmar el password");
                errorConfirmPassword.setVisibility(View.VISIBLE);
                hayError = true;
            } else {//No hay error
                if (errorConfirmPassword.getVisibility() == View.VISIBLE) {
                    errorConfirmPassword.setVisibility(View.INVISIBLE);
                }
            }
        }
        if(!hayError) {
            //Error coincidencia password y confirmacion
            if (arrayErrores[3] == 1) {
                errorConfirmPassword.setText("Los password no coinciden");
                errorConfirmPassword.setVisibility(View.VISIBLE);
                hayError = true;
            } else {//No hay error
                if (errorConfirmPassword.getVisibility() == View.VISIBLE) {
                    errorConfirmPassword.setVisibility(View.INVISIBLE);
                }
            }
        }
        return  hayError;
    }


}
