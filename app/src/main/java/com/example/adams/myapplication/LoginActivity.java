package com.example.adams.myapplication;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

  private static final String DEMO_EMAIL = "espresso@spoon.com";
  private static final String DEMO_PASSWORD = "lemoncake";

  private EditText emailEditText, passwordEditText;
  private View errorView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);

    emailEditText = findViewById(R.id.email);
    passwordEditText = findViewById(R.id.password);

    View submitButton = findViewById(R.id.submit);
    submitButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        validateFields();

        if (emailEditText.getError() == null && passwordEditText.getError() == null) {
          validateAccount();
        }
      }
    });
  }

  private void validateFields() {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
      emailEditText.setError(getString(R.string.msg_email_error));
    } else {
      emailEditText.setError(null);
    }

    if (passwordEditText.getText().toString().isEmpty()) {
      passwordEditText.setError(getString(R.string.msg_password_error));
    } else {
      passwordEditText.setError(null);
    }
  }

  private void validateAccount() {
    if (errorView == null) {
      errorView = findViewById(R.id.error);
    }

    if (!emailEditText.getText().toString().equals(DEMO_EMAIL) || !passwordEditText.getText().toString().equals(DEMO_PASSWORD)) {
      errorView.setVisibility(View.VISIBLE);
    } else {
      errorView.setVisibility(View.GONE);
    }
  }
}
