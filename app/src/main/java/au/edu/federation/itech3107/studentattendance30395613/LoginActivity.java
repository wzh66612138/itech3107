package au.edu.federation.itech3107.studentattendance30395613;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bsign = findViewById(R.id.sign);
        findViewById(R.id.login).setOnClickListener(view -> {
            if (!new CheckUserName().check(this)){
                Toast.makeText(this, "fail login", Toast.LENGTH_SHORT).show();
            };
        });
        bsign.setOnClickListener(view -> {
            startActivity(new Intent(this, SignupActivity.class));
        });
    }

}