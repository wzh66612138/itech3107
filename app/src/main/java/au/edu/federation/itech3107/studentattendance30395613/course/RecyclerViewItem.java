package au.edu.federation.itech3107.studentattendance30395613.course;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395613.R;

public class RecyclerViewItem extends RecyclerView.ViewHolder {
        TextView textCourseName;
        TextView textCourseStart;
        TextView textCourseEnd;

        public RecyclerViewItem(View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.cName);
            textCourseStart = itemView.findViewById(R.id.tStime);
            textCourseEnd = itemView.findViewById(R.id.tEnd);
        }
    }
