package Spring.Demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author kikukk
 */
@Service
class UserService {
    @Autowired
    @Qualifier(value = "userDaoImp1")
    private UserDao userDao;


    public void add(){
        System.out.println("service add.....");
        userDao.add();
    }
}
