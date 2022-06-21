package com.example.registration_and_login_sarasanapalr1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
//Variables created to add validations to all the fields on Registration Page
    Button button;
    EditText efirstName, efamilyname, eEmail,eDOB,ePassword;
    boolean isAllFieldsChecked = false;
    EditText mdateFormat;
//    DateTimepicker
    DatePickerDialog.OnDateSetListener onDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.registration);
        efirstName = (EditText) findViewById(R.id.firstname);
        efamilyname =  (EditText) findViewById(R.id.familyname);
        eDOB = findViewById(R.id.date);
        eEmail = findViewById(R.id.email);
        ePassword = findViewById(R.id.password);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mdateFormat = findViewById(R.id.date);
        mdateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = month +"/"+dayOfMonth+"/"+year;
                mdateFormat.setText(date);
            }
        };
        //    Setting the onClickListener for Register Button

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = checkAllFields();
                if(isAllFieldsChecked){
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
//Field validation Logic on Registration Page
    private boolean checkAllFields() {
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailToText = eEmail.getText().toString();
        if(efirstName.length()==0){
            efirstName.setError("This Field is Required");
            return false;
        } else if(!(efirstName.length()>=3)){
            efirstName.setError("FirstName should atleast be 3 char and not more than 30");
            return false;
        }
        if(efamilyname.length() == 0){
            efamilyname.setError("This field is Required");
            return false;
        }
        if(eDOB.length() == 0){
            eDOB.setError("This Field is Required");
            return false;
        }
        if(eEmail.length() == 0){
            eEmail.setError("This Field is Required");
            return false;
        } else if(!(Patterns.EMAIL_ADDRESS.matcher(emailToText).matches())){
            eEmail.setError("Email is not valid");
            return false;
        }

        if(ePassword.length()==0){
            ePassword.setError("Password is required");
            return false;
        } else if(ePassword.length() <=8){
            ePassword.setError("Password must be minimum 8 characters");
            return false;
        }
        return true;
    }

    //        Logic to redirect the page to Login screen
    public void onClick(View view) {
        moveToLoginScreen();
    }

    private void moveToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}