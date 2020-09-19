package com.example.cab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginRegisterActivity extends AppCompatActivity {
    private Button CustomerLoginButton;
    private Button CustomerRegisterButton;
    private TextView CustomerRegisterLink;
    private TextView CustomerStatus;
    private TextView EmailCustomer;
    private TextView PasswordCustomer;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        mAuth = FirebaseAuth.getInstance();

         CustomerLoginButton=(Button)findViewById(R.id.customer_login_button);
         CustomerRegisterButton=(Button)findViewById(R.id.customer_register_button);
         CustomerRegisterLink=(TextView) findViewById(R.id.register_customer_link);
         CustomerStatus=(TextView)findViewById(R.id.customer_status);
        EmailCustomer =(EditText) findViewById(R.id.email_customer);
        PasswordCustomer= (EditText)  findViewById(R.id.password_customer);
        loadingBar = new ProgressDialog(this);

         CustomerRegisterButton.setVisibility(View.INVISIBLE);
         CustomerRegisterButton.setEnabled(false);

         CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 CustomerLoginButton.setVisibility(View.INVISIBLE);
                 CustomerRegisterLink.setVisibility(View.INVISIBLE);
                 CustomerStatus.setText("Register Customer");
                 CustomerRegisterButton.setVisibility(View.VISIBLE);
                 CustomerRegisterButton.setEnabled(true);

             }
         });
        CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= EmailCustomer.getText().toString();
                String password= PasswordCustomer.getText().toString();

                RegisterCustomer(email, password);
            }
        });

        CustomerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= EmailCustomer.getText().toString();
                String password= PasswordCustomer.getText().toString();

                LoginCustomer(email, password);
            }
        });




    }

    private void LoginCustomer(String email, String password) {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please enter password first", Toast.LENGTH_SHORT).show();
        }

        else
        {


            loadingBar.setTitle("Customer login");
            loadingBar.setMessage("please wait,while we are loging...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer login Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else{
                                Toast.makeText(CustomerLoginRegisterActivity.this, "login UsSuccessful...try again..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });



        }



    }

    private void RegisterCustomer(String email, String password) {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please enter password first", Toast.LENGTH_SHORT).show();
        }
        else
        {


            loadingBar.setTitle("Customer Registration");
            loadingBar.setMessage("please wait,while we are register customer ..");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else{
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registration UsSuccessful...try again..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }

                    });


        }

    }
}