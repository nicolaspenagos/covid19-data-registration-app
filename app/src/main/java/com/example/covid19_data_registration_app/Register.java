/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private CheckBox option4;
    private CheckBox option5;
    private Button continuePollButton;

    // -------------------------------------
    // Global Variables
    // -------------------------------------
    private int score;

    // -------------------------------------
    // API Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        option1 = findViewById(R.id.checkBox1);
        option2 = findViewById(R.id.checkBox2);
        option3 = findViewById(R.id.checkBox3);
        option4 = findViewById(R.id.checkBox4);
        option5 = findViewById(R.id.checkBox5);
        continuePollButton = findViewById(R.id.continuePollButton);

        continuePollButton.setEnabled(false);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        continuePollButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.checkBox1:

                enableButton();
                if(option1.isChecked()) {

                    option5.setChecked(false);
                    score += 3;

                }else{
                    score -= 3;
                }

                break;

            case R.id.checkBox2:

                enableButton();
                if(option2.isChecked()){

                    option5.setChecked(false);
                    score += 3;

                }else{
                    score -= 3;
                }

                break;

            case R.id.checkBox3:

                enableButton();
                if(option3.isChecked()){

                    option5.setChecked(false);
                    score += 3;

                }else{
                    score -= 3;
                }
                    option5.setChecked(false);

                break;

            case R.id.checkBox4:

                enableButton();
                if(option4.isChecked()){

                    option5.setChecked(false);
                    score += 3;

                }else {
                    score -= 3;
                }

                break;

            case R.id.checkBox5:

                enableButton();
                if(option5.isChecked()){

                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                    score = 0;
                }

                break;

            case R.id.continuePollButton:

                String currentUsername = getIntent().getExtras().getString("username");
                String currentId  = getIntent().getExtras().getString("id");

                Intent i = new Intent(this, Symptoms.class);
                i.putExtra("score", score);
                i.putExtra("username", currentUsername);
                i.putExtra("id", currentId);
                startActivity(i);

                break;

        }
    }

    // -------------------------------------
    // Logic Methods
    // -------------------------------------
    public void enableButton(){

        if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()||option5.isChecked()){

            continuePollButton.getBackground().setTint(Color.rgb(240, 24, 86));
            continuePollButton.setEnabled(true);

        }else{

            continuePollButton.getBackground().setTint(Color.rgb(220, 220, 220));
            continuePollButton.setEnabled(false);

        }

    }

}