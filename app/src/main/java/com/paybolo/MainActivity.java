package com.paybolo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Optional login can be triggered here later
        // Example:
        // Button loginButton = findViewById(R.id.login_button);
        // loginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}