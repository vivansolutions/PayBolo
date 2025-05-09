package com.paybolo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    EditText phoneNumberInput;
    Button sendOtpButton;

    // Firebase Authentication instance
    private FirebaseAuth mAuth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // Link UI Elements
        phoneNumberInput = findViewById(R.id.phoneNumberInput); // Make sure your EditText ID is correct
        sendOtpButton = findViewById(R.id.sendOtpButton); // Make sure your Button ID is correct

        // Click Listener for OTP
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberInput.getText().toString().trim();

                if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                    phoneNumberInput.setError("Enter a valid phone number");
                    phoneNumberInput.requestFocus();
                    return;
                }

                // Format to international format
                if (!phoneNumber.startsWith("+91")) {
                    phoneNumber = "+91" + phoneNumber;
                }

                sendOTP(phoneNumber);
            }
        });
    }

    // Method to send OTP
    private void sendOTP(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        Log.d("PayBolo", "Verification Completed: " + credential);
                        Toast.makeText(MainActivity.this, "Verification Completed!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.e("PayBolo", "Verification Failed", e);
                        Toast.makeText(MainActivity.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        Log.d("PayBolo", "Code Sent: " + verificationId);
                        MainActivity.this.verificationId = verificationId;
                        Toast.makeText(MainActivity.this, "OTP Sent Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}