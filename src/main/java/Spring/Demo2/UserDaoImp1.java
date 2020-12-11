package Spring.Demo2;

import org.springframework.stereotype.Repository;

/**
 * @author kikukk
 */

@Repository
class UserDaoImp1 implements UserDao {
    @Override
    public void add() {
        System.out.println("Dao add.....");
    }
}
