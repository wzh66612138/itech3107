package au.edu.federation.itech3107.studentattendance30395613;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import au.edu.federation.itech3107.studentattendance30395613.course.CourseActivity;

public class CheckUserName {
    public boolean check(LoginActivity loginActivity) {

        EditText ename = loginActivity.findViewById(R.id.username);
        EditText epassword = loginActivity.findViewById(R.id.password);
        SQLiteDatabase db = new DBase(loginActivity).getReadableDatabase();
        String query = "SELECT username, password FROM ACCOUNT where username = ? and password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{ ename.getText().toString(), epassword.getText().toString()});
        if (cursor.moveToFirst()) {
            Intent intent = new Intent(loginActivity, CourseActivity.class);
            loginActivity.startActivity(intent);
            return true;
        }
        return false;
    }
}
