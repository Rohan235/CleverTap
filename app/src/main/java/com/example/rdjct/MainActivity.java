package com.example.rdjct;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnLogin ,btnRaise;
    EditText getName;
    EditText getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);   //Set Log level to DEBUG log warnings or other important messages

        assert clevertapDefaultInstance != null; // for checking null-pointer Exception;

        clevertapDefaultInstance.pushEvent("Product Viewed");

        btnRaise = findViewById(R.id.raiseEvent);

        btnLogin = findViewById(R.id.loginButton);
        getName = findViewById(R.id.usernameEditText);
        getEmail = findViewById(R.id.emailEditText);

        btnLogin.setOnClickListener(v -> {
            String username = getName.getText().toString();
            String email = getEmail.getText().toString();

            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

            profileUpdate.put("Identity", username);

            profileUpdate.put("Email", email);

            clevertapDefaultInstance.onUserLogin(profileUpdate);

        });

        btnRaise.setOnClickListener(v -> {
            clevertapDefaultInstance.pushEvent("TEST");
        });


    }
}