package Service;

import DAO.AddStudentDAO;
import Model.Student;

public class AddStudentService {

    public boolean addStudent(Student student) {

        if(student.getStudentName()==null||student.getStudentName()==""){
            System.out.println("invalid student name");
            return false;
        }
        if(student.getPassword().length()>10 || student.getPassword().length()<6){
            System.out.println("password is not acceptable");
            return false;
        }
       AddStudentDAO addStudentDAO = new AddStudentDAO();
        return addStudentDAO.AddStudent(student);
    }
}
