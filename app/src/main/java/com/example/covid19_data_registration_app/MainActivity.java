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

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

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

        //Send to worker thread
        new Thread(()->   showInfo()).start();

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------
    public void showInfo(){

        String msg = "Resultados ordenados por nivel de riesgo: \n\n";
        HashSet<String> info = (HashSet<String>) getSharedPreferences("infoBin", MODE_PRIVATE).getStringSet("infoSet", new HashSet<String>());

        //
        // It was not possible to obtain and cast the TreeSet directly from the SharedPrederes since so far
        // the API does not guarantees that the Set you get when calling getStringSet() is the same, or even
        // the same implementation, as the one stored when calling putStringSet().
        //
        TreeSet<String> sortedInfo = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {

                int i1 = Integer.parseInt(s1.split("%")[1]);
                int i2 = Integer.parseInt(s2.split("%")[1]);

                //Rverse order
                if(i1>i2){
                    return -1;
                }else if(i1<i2){
                    return 1;
                }else{
                    return 0;
                }

            }
        });

        sortedInfo.addAll(info);

        if(!sortedInfo.isEmpty()){

            for (String s: sortedInfo){

                String[] parts = s.split("%");
                msg+= String.format("%-20s %s", parts[0] , parts[1])+"\n";
              //  msg+= parts[0]+space(parts[0].length(),parts[1].length())+parts[1]+"\n";

            }

        }

        String finalMsg = msg;
        runOnUiThread(()-> dataTextView.setText(finalMsg));

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