package au.edu.federation.itech3107.studentattendance30395613;

import android.database.sqlite.SQLiteDatabase;

public class DBInit {

    private static String n1= "COURSE";
    private static String n2= "STUDENT";
    private static String n3= "ATTENDANCE";
    private static String n4= "ACCOUNT";
    public DBInit(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+n1+"(id   integer primary key autoincrement,start_time varchar(255),name       varchar(255),end_time   varchar(255))");
        sqLiteDatabase.execSQL("CREATE TABLE "+n2+"(" +
                " id    integer primary key autoincrement,\n" + "course_id   integer,number   varchar(255),name   varchar(255))");
        sqLiteDatabase.execSQL("CREATE TABLE "+n3+"(id         integer primary key autoincrement,student_id   integer,student_number   varchar(255),student_name   varchar(255),status   integer,date   varchar(255),course_id   varchar(255))");
        sqLiteDatabase.execSQL("CREATE TABLE "+n4+"(   id   integer primary key autoincrement,     username   varchar(255), password   varchar(255))");
    }
}
