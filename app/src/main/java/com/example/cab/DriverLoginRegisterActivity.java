package com.example.cab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button DriverLoginButton;
    private Button DriverRegisterButton;
    private TextView DriverRegisterLink;
    private TextView DriverStatus;
    private EditText EmailDriver;
    private EditText PasswordDriver;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);
        mAuth = FirebaseAuth.getInstance();

        DriverLoginButton=(Button)findViewById(R.id.driver_login_button);
        DriverRegisterButton=(Button)findViewById(R.id.driver_register_button);
        DriverRegisterLink=(TextView) findViewById(R.id.register_driver_link);
        DriverStatus=(TextView) findViewById(R.id.driver_status);
        EmailDriver =(EditText) findViewById(R.id.email_driver);
        PasswordDriver= (EditText)  findViewById(R.id.password_driver);
        loadingBar = new ProgressDialog(this);


        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(View.INVISIBLE);
                DriverStatus.setText("Register Driver");
                DriverRegisterButton.setVisibility(View.VISIBLE);
                DriverRegisterButton.setEnabled(true);

            }
        });
        DriverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= EmailDriver.getText().toString();
                String password= PasswordDriver.getText().toString();
                
               RegisterDriver(email, password);
            }
        });

        DriverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= EmailDriver.getText().toString();
                String password= PasswordDriver.getText().toString();

                LoginDriver(email, password);
            }
        });



    }

    private void LoginDriver(String email, String password) {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please enter password first", Toast.LENGTH_SHORT).show();
        }

        else
        {


            loadingBar.setTitle("Driver login");
            loadingBar.setMessage("please wait,while we are loging...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver login Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriverMapActivity.class);
                                startActivity(driverIntent);
                            }
                            else{
                                Toast.makeText(DriverLoginRegisterActivity.this, "login UsSuccessful...try again..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });



        }
    }





    private void RegisterDriver(String email, String password) {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please enter password first", Toast.LENGTH_SHORT).show();
        }
        else
        {


            loadingBar.setTitle("Driver Registration");
            loadingBar.setMessage("please wait,while we are register your data");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriverMapActivity.class);
                                startActivity(driverIntent);
                            }
                            else{
                                Toast.makeText(DriverLoginRegisterActivity.this, "Registration UsSuccessful...try again..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });



        }
    }

}