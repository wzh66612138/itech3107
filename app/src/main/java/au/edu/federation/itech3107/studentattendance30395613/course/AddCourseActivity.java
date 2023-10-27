package au.edu.federation.itech3107.studentattendance30395613.course;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;

public class AddCourseActivity extends AppCompatActivity {
    private Button addcou;
    private EditText couname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_add);
        SQLiteDatabase db = new DBase(AddCourseActivity.this).getWritableDatabase();
        addcou = findViewById(R.id.buttonS);
        couname = findViewById(R.id.etname);
        coutime = findViewById(R.id.timeS);
        coutimeE = findViewById(R.id.timeE);
        coutimeE.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    EditText edit = findViewById(R.id.timeE);
                    edit.setText(selectedDate);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
        addcou.setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put("name", couname.getText().toString());
            values.put("start_time", coutime.getText().toString());
            values.put("end_time", coutimeE.getText().toString());
            db.insert("COURSE", null, values);
            finish();
        });

        coutime.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    EditText editText = findViewById(R.id.timeS);
                    editText.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
                }
            },  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }


    private EditText coutime;
    private EditText coutimeE;
}