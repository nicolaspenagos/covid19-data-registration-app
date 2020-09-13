/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class NewRegister extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private EditText usernameEditText;
    private EditText idEditText;
    private Button continueRegitrationButton;

    // -------------------------------------
    // API Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        usernameEditText = findViewById(R.id.usernmaeEditText);
        idEditText = findViewById(R.id.idEditText);
        continueRegitrationButton = findViewById(R.id.continueRegistrationButtton);

        continueRegitrationButton.setOnClickListener(

                (view)->{

                    String username = usernameEditText.getText().toString();
                    String id = idEditText.getText().toString();

                    boolean inputDataOk = checkInputData(username, id);
                    boolean idOk = checkId(id);

                    if(inputDataOk && idOk){

                        addId(id);

                        Intent i = new Intent(this, Register.class);
                        i.putExtra("username", username);
                        startActivity(i);

                    }else{

                        String msg = (!idOk) ? "Este id ya esta registrado":"No se permiten campos vacios";
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                    }

                }
        );

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------

    /*
     * If the application is running for the first time, this method creates an instance of a new HashSet and saves the id;
     * otherwise it just saves the id in the HashSet that you already get from the SharedPreferences
     */
    public void addId(String id){

        SharedPreferences sharedPreferences = getSharedPreferences("bin", MODE_PRIVATE);
        Set<String> idSet = sharedPreferences.getStringSet("idSet", null);

        if(idSet==null)
            idSet=new HashSet<String>();

        idSet.add(id);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putStringSet("idSet",idSet);
        edit.commit();

    }
    public boolean checkInputData(String username, String id){
        return (!username.equals(""))&&(username!=null)&&(!id.equals(""))&&(id!=null);
    }

    public boolean checkId(String id){

        Set<String> idSet =  getSharedPreferences("bin", MODE_PRIVATE).getStringSet("idSet", null);
        return (id==null) ? true : !idSet.contains(id);

    }

}