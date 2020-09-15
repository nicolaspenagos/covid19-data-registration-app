/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private Button registerButton;
    private TextView dataTextView;

    // -------------------------------------
    // API Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.registerButton);
        dataTextView = findViewById(R.id.dataTextView);

        registerButton.setOnClickListener(

                (view)->{

                    Intent i = new Intent(this,NewRegister.class);
                    startActivity(i);

                }

        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        showInfo();

    }

    public void showInfo(){

        String msg = "";
        HashSet<String> info = (HashSet<String>) getSharedPreferences("bin3", MODE_PRIVATE).getStringSet("infoSet", new HashSet<String>());

        if(!info.isEmpty()){

            for (String s: info){

                String[] parts = s.split("%");
              //  msg+= String.format("%-20s %s", parts[0] , parts[1])+"\n";
                Log.e("debug", parts[0]+space(parts[0].length(),parts[1].length())+parts[1]+"\n");
                msg+= parts[0]+space(parts[0].length(),parts[1].length())+parts[1]+"\n";

            }

        }

        dataTextView.setText(msg);


    }

    public String space(int x, int y){
        Log.e("debug", x +" "+y);
        int z = 30-x-y;

        String space = "";
        for(int i = 0; i<z; i++){
            space+=" ";
        }

        return space;
    }

}