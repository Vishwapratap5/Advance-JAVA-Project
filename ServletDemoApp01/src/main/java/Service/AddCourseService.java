package Service;

import DAO.AddCourseDAO;
import Model.Course;

public class AddCourseService {

    public void  addCourse(Course course){
        if(course.getCourseDuration() ==null|| course.getCourseName()==null || course.getCourseFees()<0){
            System.out.println("Invalid Details");
            return;
        }
        AddCourseDAO addCourseDAO = new AddCourseDAO();
        addCourseDAO.AddCourse(course);
    }
}
