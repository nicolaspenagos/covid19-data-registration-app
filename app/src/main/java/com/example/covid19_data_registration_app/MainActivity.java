/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
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

        dataTextView.setMovementMethod(new ScrollingMovementMethod());
        dataTextView.setTypeface(Typeface.MONOSPACE);

        registerButton.setOnClickListener(

                (view)->{

                    Intent i = new Intent(this,NewRegister.class);
                    startActivity(i);

                }

        );

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        //Send to worker thread
        new Thread(()->   showInfo()).start();

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showInfo(){

        String msg = "\n";
        HashSet<String> info = (HashSet<String>) getSharedPreferences("myInfoBin", MODE_PRIVATE).getStringSet("infoSet", new HashSet<String>());

        ArrayList<String> sortedInfo = new ArrayList<String>(info);

        sortedInfo.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                int i1 = Integer.parseInt(s.split("%")[1]);
                int i2 = Integer.parseInt(t1.split("%")[1]);

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

        for(int i=0; i<sortedInfo.size(); i++){

            String[] parts = sortedInfo.get(i).split("%");
            msg+= String.format("%-20s %s", parts[0] , parts[1])+"\n";

        }

        String finalMsg = msg;
        runOnUiThread(()-> dataTextView.setText(finalMsg));

    }

}