package au.edu.federation.itech3107.studentattendance30395613.student;

public class Student {
    private Integer id;
    private String name;
    private String number;
    private Integer courseId;

    public Student(Integer id, String name, String number, Integer courseId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.courseId = courseId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
