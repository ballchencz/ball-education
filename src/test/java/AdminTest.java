import com.ballchen.education.account.dao.IAccountDAO;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.admin.dao.IMenuInfoDAO;
import com.ballchen.education.admin.entity.MenuInfo;
import com.ballchen.education.user.dao.IUserBasicDAO;
import com.ballchen.education.user.entity.UserBasic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class AdminTest {
    @Resource
    private IMenuInfoDAO menuInfoDAO;
    @Autowired
    private IUserBasicDAO userBasicDAO;
    @Autowired
    private IAccountDAO accountDAO;


    @Test
    public void testQueryById() throws Exception {
        String id = "123";
        MenuInfo menuInfo = menuInfoDAO.selectByPrimaryKey(id);
        System.out.println(menuInfo.getMenuName());
    }
    @Test
    public void testSelectByPrimaryKeyUserBasic() throws Exception{
        String id = "123";
        Account account = accountDAO.selectByPrimaryKey(id);
        System.out.println(account.getAccountName());
    }
}
