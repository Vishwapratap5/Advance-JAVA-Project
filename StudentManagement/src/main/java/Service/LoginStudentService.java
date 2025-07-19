package Service;

import DAO.LoginStudentDAO;
import Model.Student;

public class LoginStudentService {

    public boolean LoginStudent(Student student) {
        if(student.getEmail()==null||student.getEmail()==""){
            System.out.println("invalid student name");
            return false;
        }
        if(student.getPassword().length()>10 || student.getPassword().length()<6){
            System.out.println("password is not acceptable");
            return false;
        }
        LoginStudentDAO loginStudentDAO = new LoginStudentDAO();
        return loginStudentDAO.LoginStudent(student);
    }
}
