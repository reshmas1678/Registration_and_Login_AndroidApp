package com.example.registration_and_login_sarasanapalr1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
//Variable Declaration for the Login Activity
    Button button;
    EditText lemail,lpassword;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.login);
        lemail = (EditText) findViewById(R.id.loginemail);
        lpassword = (EditText) findViewById(R.id.loginpassword);
//    Setting the onClickListener for Login Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = checkAllFields();
                if(isAllFieldsChecked){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
//Code for field validation on Login Page
    private boolean checkAllFields() {
        String emailToText = lemail.getText().toString();
        if(lemail.length() == 0){
            lemail.setError("This Field is Required");
            return false;
        } else if(!(Patterns.EMAIL_ADDRESS.matcher(emailToText).matches())){
            lemail.setError("Email is not valid");
            return false;
        }

        if(lpassword.length()==0){
            lpassword.setError("Password is required");
            return false;
        } else if(lpassword.length() <=8){
            lpassword.setError("Password must be minimum 8 characters");
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        moveToregistrationScreen();
    }

    private void moveToregistrationScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}