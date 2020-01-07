import com.bitedu.data.Student;

import com.bitedu.db.DatabaseOperation;

import javax.sql.DataSource;

import static com.bitedu.db.DatabaseOperation.insert;
import static com.bitedu.db.DatabaseOperation.select;
import static com.bitedu.db.DatabaseOperation.selectCount;

public class Test {
    public static void main(String[] args) {
        Student stu=new Student();
        stu.setClass_id(5);
        stu.setName("李烨");
        stu.setId(2);
        //insert(stu);
        //select(1);
        selectCount();
    }
}
