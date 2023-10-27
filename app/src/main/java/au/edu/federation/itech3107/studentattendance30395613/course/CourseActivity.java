package au.edu.federation.itech3107.studentattendance30395613.course;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395613.DBase;
import au.edu.federation.itech3107.studentattendance30395613.R;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_course);
        db = new DBase(this).getReadableDatabase();
        recyclerView = findViewById(R.id.rview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Course> courses = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String start_name = cursor.getString(2);
                String end_time = cursor.getString(3);
                Course course = new Course(id, name, start_name, end_time);
                courses.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<RecyclerViewItem> adapter = new RecyclerView.Adapter<RecyclerViewItem>() {
            @NonNull
            @Override
            public RecyclerViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View courseItemView = getLayoutInflater().inflate(R.layout.course_one, null);
                return new RecyclerViewItem(courseItemView);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerViewItem holder, int position) {
                Course course = courses.get(position);
                holder.textCourseName.setText("Course Name: " + course.getName());
                holder.textCourseStart.setText("Start Time: " + course.getStartTime());
                holder.textCourseEnd.setText("End Time: " + course.getEndTime());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CourseActivity.this, SaveCourseActivity.class);
                        intent.putExtra("id", course.getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return courses.size();
            }
        };
        recyclerView.setAdapter(adapter);
        findViewById(R.id.buttonS).setOnClickListener(view -> startActivity(new Intent(CourseActivity.this, AddCourseActivity.class)));

    }
    String query = "SELECT id, name, start_time, end_time FROM COURSE";
}