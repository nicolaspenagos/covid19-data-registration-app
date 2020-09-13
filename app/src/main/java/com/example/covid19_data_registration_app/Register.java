/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.covid19_data_registration_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Register extends AppCompatActivity implements View.OnClickListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private CheckBox option4;
    private CheckBox option5;

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

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.checkBox1:

                if(option1.isChecked())
                 option5.setChecked(false);

                break;

            case R.id.checkBox2:

                if(option2.isChecked())
                    option5.setChecked(false);

                break;

            case R.id.checkBox3:

                if(option3.isChecked())
                    option5.setChecked(false);

                break;

            case R.id.checkBox4:

                if(option4.isChecked())
                    option5.setChecked(false);

                break;

            case R.id.checkBox5:

                if(option5.isChecked()){

                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);

                }

                break;
                
        }
    }
}