package au.edu.federation.itech3107.studentattendance30395613.attendance;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import au.edu.federation.itech3107.studentattendance30395613.R;

public class Btn1Click {
    private AddAttendanceActivity addAttendanceActivity;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(addAttendanceActivity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    EditText editText = addAttendanceActivity.findViewById(R.id.date);
                    editText.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
    };
    public Btn1Click(AddAttendanceActivity addAttendanceActivity){
        this.addAttendanceActivity = addAttendanceActivity;
        addAttendanceActivity.time.setOnClickListener(clickListener);
    }
}
