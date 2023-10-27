package au.edu.federation.itech3107.studentattendance30395613.attendance;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395613.R;

public class ItemViewAtt extends RecyclerView.ViewHolder {

        public CheckBox chbox;
    public TextView textname;
    public TextView textnumber;

        public ItemViewAtt(View itemView) {
            super(itemView);
            textnumber = itemView.findViewById(R.id.tnumber);
            textname = itemView.findViewById(R.id.tname);
            chbox = itemView.findViewById(R.id.checkBox);
        }
    }