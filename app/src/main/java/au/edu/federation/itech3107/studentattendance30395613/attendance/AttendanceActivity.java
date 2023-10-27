package au.edu.federation.itech3107.studentattendance30395613.attendance;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;

public class AttendanceActivity extends AppCompatActivity {
    private RecyclerView rview;
    private SQLiteDatabase liteData;
    private Integer courseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_list);
        liteData = new DBase(this).getReadableDatabase();
        courseId = getIntent().getIntExtra("id", 0);
        rview = findViewById(R.id.rview);
        editText = findViewById(R.id.date);
        editText.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AttendanceActivity.this, (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                List<Attendance> attendances = new ArrayList<>();
                Cursor cursor = liteData.rawQuery(query, new String[]{String.valueOf(courseId), selectedDate});
                if (cursor.moveToFirst()) {
                    do {
                        Integer id = cursor.getInt(0);
                        Integer student_id = cursor.getInt(1);
                        String student_number = cursor.getString(2);
                        String student_name = cursor.getString(3);
                        Integer status = cursor.getInt(4);
                        Attendance attendance = new Attendance(id, student_id, student_number, student_name, courseId, status);
                        attendances.add(attendance);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                editText.setText(selectedDate);
                rview.setAdapter(new RecyclerView.Adapter<RecyclerViewAtt>() {
                    @NonNull
                    @Override
                    public RecyclerViewAtt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View courseItemView = getLayoutInflater().inflate(R.layout.attendance_one, null);
                        return new RecyclerViewAtt(courseItemView);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerViewAtt holder, int position) {
                        Attendance attendance = attendances.get(position);
                        holder.text.setText(attendance.getStatus() == 0 ? "False" : "True");
                        holder.stuNo.setText("student no: " + attendance.getStuNo() + " name: " + attendance.getStudentName());
                    }

                    @Override
                    public int getItemCount() {
                        return attendances.size();
                    }
                });
            }, year, month, day);
            datePickerDialog.show();
        });
        rview.setLayoutManager(new LinearLayoutManager(this));
    }
    String query = "SELECT id, student_id, student_number, student_name, status  FROM ATTENDANCE where course_id = ? and date = ?";
    EditText editText;

    public static final class RecyclerViewAtt extends RecyclerView.ViewHolder {
        final TextView stuNo;
        final TextView stuName;
        final TextView text;

        public RecyclerViewAtt(View itemView) {
            super(itemView);
            stuNo = itemView.findViewById(R.id.tnumber);
            stuName = itemView.findViewById(R.id.tname);
            text = itemView.findViewById(R.id.textStatus);
        }
    }
}