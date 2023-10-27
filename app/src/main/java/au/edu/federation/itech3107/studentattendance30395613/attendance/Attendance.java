package au.edu.federation.itech3107.studentattendance30395613.attendance;

public class Attendance {
    private Integer id;
    private Integer stuId;
    private String stuNo;

    private Integer status;

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Attendance(Integer id, Integer stuId, String stuNo, String studentName, Integer courseId, Integer status) {
        this.id = id;
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.studentName = studentName;
        this.courseId = courseId;
        this.status = status;
    }

    public Attendance() {
    }
    private Integer courseId;

    public Integer getId() {
        return id;
    }
    private String studentName;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
