package Service;

import DAO.DeleteCourseDAO;

import java.sql.SQLException;

public class DeleteCourseService {
    public void deleteCourse(int courseId) {
        if(courseId<0){
            System.out.println("Invalid course Id");
        }
        DeleteCourseDAO deleteCourseDAO=new DeleteCourseDAO();
        deleteCourseDAO.deleteCourse(courseId);
    }
}
