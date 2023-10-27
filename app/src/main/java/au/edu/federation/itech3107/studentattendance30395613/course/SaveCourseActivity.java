package au.edu.federation.itech3107.studentattendance30395613.course;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;
import au.edu.federation.itech3107.studentattendance30395613.student.StudentActivity;
import au.edu.federation.itech3107.studentattendance30395613.attendance.AddAttendanceActivity;
import au.edu.federation.itech3107.studentattendance30395613.attendance.AttendanceActivity;

public class SaveCourseActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText etCourseName;
    private EditText etStartTime;
    private EditText etEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_set);
        Integer id = getIntent().getIntExtra("id", 0);

        etCourseName = findViewById(R.id.etname);
        etStartTime = findViewById(R.id.timeS);
        etEndTime = findViewById(R.id.timeE);
        findViewById(R.id.btnSet).setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put("name", etCourseName.getText().toString());
            values.put("start_time", etStartTime.getText().toString());
            values.put("end_time", etEndTime.getText().toString());
            db.update("COURSE", values, "id = ?", new String[]{String.valueOf(id)});
            Toast.makeText(SaveCourseActivity.this, "success", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnList).setOnClickListener(view -> {
            Intent intent = new Intent(SaveCourseActivity.this, StudentActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        findViewById(R.id.btnDelete).setOnClickListener(view -> {
            db.delete("COURSE", "id = ?", new String[]{String.valueOf(id)});
            finish();
        });

        findViewById(R.id.btnQuery).setOnClickListener(view -> {
            Intent intent = new Intent(SaveCourseActivity.this, AttendanceActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            Intent intent = new Intent(SaveCourseActivity.this, AddAttendanceActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        etStartTime.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(SaveCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    EditText editText = findViewById(R.id.timeS);
                    editText.setText(selectedDate);
                }
            }, year, month, day);
            datePickerDialog.show();
        });
        etEndTime.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(SaveCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    EditText editText = findViewById(R.id.timeE);
                    editText.setText(selectedDate);
                }
            }, year, month, day);
            datePickerDialog.show();
        });
        db = new DBase(this).getReadableDatabase();
        String query = "SELECT id, name, start_time, end_time FROM COURSE where id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String start_name = cursor.getString(2);
            String end_time = cursor.getString(3);
            etCourseName.setText(name);
            etStartTime.setText(start_name);
            etEndTime.setText(end_time);
        }
        cursor.close();
    }
}