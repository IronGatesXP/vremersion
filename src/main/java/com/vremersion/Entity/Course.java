package com.vremersion.Entity;

/**
 * @author XP
 * @date 2018/5/2 15:12
 */
public class Course {
    private int id;
    private String courseName;
    private String courseTeacher;
    private String courseDescribe;
    private String courseImage;
    private String courseExamStandard;
    private String courseData;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseTeacher(){
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher){
        this.courseTeacher = courseTeacher;
    }

    public String getCourseDescribe(){
        return courseDescribe;
    }

    public void setCourseDescribe(String courseDescribe){
        this.courseDescribe = courseDescribe;
    }

    public String getCourseImage(){
        return courseImage;
    }

    public void setCourseImage(String courseImage){
        this.courseImage = courseImage;
    }

    public String getCourseExamStandard(){
        return courseExamStandard;
    }

    public void setCourseExamStandard(String courseExamStandard){
        this.courseExamStandard = courseExamStandard;
    }

    public String getCourseData(){
        return courseData;
    }

    public void setCourseData(String courseData){
        this.courseData = courseData;
    }

}
