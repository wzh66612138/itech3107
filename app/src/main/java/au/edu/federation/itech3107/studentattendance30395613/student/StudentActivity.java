package au.edu.federation.itech3107.studentattendance30395613.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_query);
        Button btnAddStudent = findViewById(R.id.btnstu);
        btnAddStudent.setOnClickListener(view -> {
            Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
            intent.putExtra("id", getIntent().getIntExtra("id", 0));
            startActivity(intent);
        });
        rview = findViewById(R.id.rview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final List<Student> studentList = getData();
        rview.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<StudentActivity.StudentViewHolder> adapter = new RecyclerView.Adapter<StudentActivity.StudentViewHolder>() {
            @NonNull
            @Override
            public StudentActivity.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View courseItemView = getLayoutInflater().inflate(R.layout.student_one, null);
                return new StudentActivity.StudentViewHolder(courseItemView);
            }

            @Override
            public void onBindViewHolder(@NonNull StudentActivity.StudentViewHolder holder, int position) {
                Student student = studentList.get(position);
                holder.num.setText("student no: " + student.getNumber() + " name:" + student.getName());
            }

            @Override
            public int getItemCount() {
                return studentList.size();
            }
        };
        rview.setAdapter(adapter);
    }


    private RecyclerView rview;

    private List<Student> getData() {
        Integer courseId = getIntent().getIntExtra("id", 0);
        SQLiteDatabase db = new DBase(this).getReadableDatabase();
        String query = "SELECT id, number, name FROM STUDENT where course_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId)});
        List<Student> process = process(cursor, courseId);
        cursor.close();
        return process;
    }

    public List<Student> process(Cursor cursor, Integer courseId) {
        List<Student> students = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String number = cursor.getString(1);
                String name = cursor.getString(2);
                Student student = new Student(id, name, number, courseId);
                students.add(student);
            } while (cursor.moveToNext());
        }
        return students;
    }

    public static final class StudentViewHolder extends RecyclerView.ViewHolder {
        final TextView num;
        final TextView name;

        public StudentViewHolder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.tnumber);
            name = itemView.findViewById(R.id.tname);
        }
    }

}