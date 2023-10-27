package au.edu.federation.itech3107.studentattendance30395613.attendance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;
import au.edu.federation.itech3107.studentattendance30395613.student.Student;

import java.util.ArrayList;
import java.util.List;

public class AddAttendanceActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    public EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_add);
        RecyclerView rview = findViewById(R.id.rview);
        db = new DBase(AddAttendanceActivity.this).getWritableDatabase();
        Button btn = findViewById(R.id.button);
        time = findViewById(R.id.date);
        new Btn1Click(this);
        List<Student> studentList = getSampleStudentData();
        List<ItemViewAtt> viewHolderList = new ArrayList<>();
        setclick(btn,studentList,viewHolderList);
        rview.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<ItemViewAtt> adapter = new RecyclerView.Adapter<ItemViewAtt>() {
            @NonNull
            @Override
            public ItemViewAtt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View courseItemView = getLayoutInflater().inflate(R.layout.attendance_item, null);
                ItemViewAtt attendanceViewHolder = new ItemViewAtt(courseItemView);
                viewHolderList.add(attendanceViewHolder);
                return attendanceViewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull ItemViewAtt holder, int position) {
                Student student = studentList.get(position);
                holder.textnumber.setText("student no: " + student.getNumber() + " name: " + student.getName());
            }

            @Override
            public int getItemCount() {
                return studentList.size();
            }
        };
        rview.setAdapter(adapter);
    }

    private List<Student> getSampleStudentData() {
        Integer courseId = getIntent().getIntExtra("id", 0);
        List<Student> students = new ArrayList<>();
        String query = "SELECT id, number, name FROM STUDENT where course_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId)});
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String number = cursor.getString(1);
                String name = cursor.getString(2);
                Student student = new Student(id, name, number, courseId);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }


    public void setclick(Button btn, List<Student> studentList, List<ItemViewAtt> viewHolderList){
        btn.setOnClickListener(view -> {
            for (int i = 0; i < studentList.size(); i++) {
                Student student = studentList.get(i);
                ContentValues values = new ContentValues();
                ItemViewAtt attendanceViewHolder = viewHolderList.get(i);
                values.put("student_name", student.getName());
                values.put("course_id", student.getCourseId());
                values.put("date", time.getText().toString());
                values.put("student_id", student.getId());
                values.put("student_number", student.getNumber());
                values.put("status", !attendanceViewHolder.chbox.isChecked() ? 0 : 1);
                db.insert("ATTENDANCE", null, values);
            }
            finish();
        });
    }


}