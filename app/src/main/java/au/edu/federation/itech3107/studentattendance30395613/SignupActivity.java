package au.edu.federation.itech3107.studentattendance30395613;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        findViewById(R.id.sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                EditText name = findViewById(R.id.username);
                EditText passwod = findViewById(R.id.password);

                values.put("username", name.getText().toString());
                values.put("password", passwod.getText().toString());
                SQLiteDatabase db = new DBase(SignupActivity.this).getWritableDatabase();
                db.insert("ACCOUNT", null, values);
                Toast.makeText(SignupActivity.this, "success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}