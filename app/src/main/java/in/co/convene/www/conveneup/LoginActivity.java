package in.co.convene.www.conveneup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button bLogin;
    EditText etUsername,etPassword;
    TextView tvIncorrect;
    CheckBox cbShowPwd;
    SessionManager session;
    LoginActivity loginActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        bLogin = (Button)findViewById(R.id.bLogin);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvIncorrect = (TextView) findViewById(R.id.tvIncorrect);
        cbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        loginActivity = this;
        session.checkLogin(this);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String Username = user.get(SessionManager.KEY_NAME);
        Log.d("rishi", "Username in main activity :" +Username);
        cbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    public void OnLogin(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String Choice = "Login";
        Toast.makeText(getApplicationContext(), "Logging in.", Toast.LENGTH_SHORT).show();
        Log.d("rishi","in on login");
        BackgroundWorker backgroundWorker = new BackgroundWorker(loginActivity,this,session,tvIncorrect,Choice,username);
        backgroundWorker.execute(username, password);
    }
}