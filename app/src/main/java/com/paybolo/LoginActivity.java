package com.paybolo;

import android.content.Intent;
import com.paybolo.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText phoneInput;
    Button sendOtpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneInput = findViewById(R.id.phone_input);
        sendOtpBtn = findViewById(R.id.send_otp_btn);

        sendOtpBtn.setOnClickListener(view -> {
            String phone = phoneInput.getText().toString();
            // You can add Firebase OTP logic here
        });
    }
}
