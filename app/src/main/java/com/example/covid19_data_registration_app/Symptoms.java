/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashSet;
import java.util.Set;

public class Symptoms extends AppCompatActivity implements View.OnClickListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private CheckBox option4;
    private CheckBox option5;
    private CheckBox option6;
    private CheckBox option7;
    private Button finishButton;

    // -------------------------------------
    // Global Variables
    // -------------------------------------
    private int finalScore;
    private int score;

    // -------------------------------------
    // API Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        option1 = findViewById(R.id.checkBoxS1);
        option2 = findViewById(R.id.checkBoxS2);
        option3 = findViewById(R.id.checkBoxS3);
        option4 = findViewById(R.id.checkBoxS4);
        option5 = findViewById(R.id.checkBoxS5);
        option6 = findViewById(R.id.checkBoxS6);
        option7 = findViewById(R.id.checkBoxS7);
        finishButton = findViewById(R.id.finishButton);

        finishButton.setEnabled(false);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);
        option7.setOnClickListener(this);
        finishButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.checkBoxS1:

                enableButton();
                if(option1.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS2:

                enableButton();
                if(option2.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS3:

                enableButton();
                if (option3.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS4:

                enableButton();
                if (option4.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS5:

                enableButton();
                if (option5.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS6:

                enableButton();
                if (option6.isChecked()){

                    option7.setChecked(false);
                    score += 4;

                }else{
                    score -= 4;
                }

                break;

            case R.id.checkBoxS7:

                enableButton();
                if (option7.isChecked()){

                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                    option5.setChecked(false);
                    option6.setChecked(false);

                    score = 0;

                }

                break;

            case R.id.finishButton:

                //Send to worker thread
                new Thread(()->{

                    String id = getIntent().getExtras().getString("id");
                    addId(id);
                    addInfo();

                    //Once finished, sent to Ui Thread
                    runOnUiThread(()->{

                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);

                    });
                }).start();

                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        finalScore = getIntent().getExtras().getInt("score");

    }

    // -------------------------------------
    // Logic Methods
    // -------------------------------------
    public void enableButton(){

        if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()||option5.isChecked()||option6.isChecked()||option7.isChecked()){

            finishButton.getBackground().setTint(Color.rgb(240, 24, 86));
            finishButton.setEnabled(true);

        }else{

            finishButton.getBackground().setTint(Color.rgb(220, 220, 220));
            finishButton.setEnabled(false);

        }

    }

    /*
     * If the application is running for the first time, this method creates an instance of a new HashSet and saves the info;
     * otherwise it just saves the id in the HashSet that you already get from the SharedPreferences
     */
    public void addInfo(){

        String currentUsername = getIntent().getExtras().getString("username");
        int currentScore = finalScore + score;

        SharedPreferences sharedPreferences = getSharedPreferences("myInfoBin", MODE_PRIVATE);
        Set<String> infoSet = sharedPreferences.getStringSet("infoSet", null);

        if(infoSet==null)
            infoSet=new HashSet<String>();

        infoSet.add(currentUsername+"%"+currentScore);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putStringSet("infoSet", infoSet);
        edit.commit();

    }

    /*
     * If the application is running for the first time, this method creates an instance of a new HashSet and saves the id;
     * otherwise it just saves the id in the HashSet that you already get from the SharedPreferences
     */
    public void addId(String id){

        SharedPreferences sharedPreferences = getSharedPreferences("myIdBin", MODE_PRIVATE);
        Set<String> idSet = sharedPreferences.getStringSet("idSet", null);

        if(idSet==null)
            idSet=new HashSet<String>();

        idSet.add(id);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putStringSet("idSet",idSet);
        edit.commit();

    }

}