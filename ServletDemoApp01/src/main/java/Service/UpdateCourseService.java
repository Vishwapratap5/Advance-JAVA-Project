package Service;

import DAO.UpdateCourseDAO;
import Model.Course;

public class UpdateCourseService {
    public Course UpdateCourse(Course course) {
        if(course.getCourseDuration() ==null|| course.getCourseName()==null || course.getCourseFees()<0){
            System.out.println("Invalid Details");
            return null;
        }
        UpdateCourseDAO updateCourseDAO = new UpdateCourseDAO();
        Course UpdatedCourse=updateCourseDAO.UpdateCourse(course);

        return UpdatedCourse;
    }
}
