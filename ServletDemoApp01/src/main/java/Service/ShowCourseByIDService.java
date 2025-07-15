package Service;


import DAO.GetCourseByIDDAO;
import Model.Course;

public class ShowCourseByIDService {
    public Course getCourseByID(Course course) {
        if(course.getCourseID()<0){
            System.out.println("Invalid course Id");
        }
        GetCourseByIDDAO getCourseByIDDAO=new GetCourseByIDDAO();
        Course course2=getCourseByIDDAO.getCourse(course);
        return course2;
    }
}
