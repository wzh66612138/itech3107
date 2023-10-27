package au.edu.federation.itech3107.studentattendance30395613.student;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;

public class AddStudentActivity extends AppCompatActivity {
    private Button btnAddStudent;
    private EditText etName;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_add);
        btnAddStudent = findViewById(R.id.btnstu);
        etNumber = findViewById(R.id.tnum);
        etName = findViewById(R.id.edname);
        btnAddStudent.setOnClickListener(view -> {
            SQLiteDatabase db = new DBase(AddStudentActivity.this).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", etName.getText().toString());
            values.put("number", etNumber.getText().toString());
            values.put("course_id", getIntent().getIntExtra("id", 0));
            db.insert("STUDENT", null, values);
            finish();
        });
    }
}