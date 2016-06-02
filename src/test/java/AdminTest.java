import com.alibaba.fastjson.JSONArray;
import com.ballchen.education.account.dao.IAccountDAO;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.admin.dao.IMenuInfoDAO;
import com.ballchen.education.admin.entity.MenuInfo;
import com.ballchen.education.security.dao.IAuthorizationDAO;
import com.ballchen.education.security.dao.IRoleAuthorizationDAO;
import com.ballchen.education.security.dao.IRoleDAO;
import com.ballchen.education.security.entity.Authorization;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.security.service.IRoleService;
import com.ballchen.education.user.dao.IUserBasicDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-servlet.xml"})
public class AdminTest {
    @Resource
    private IMenuInfoDAO menuInfoDAO;
    @Autowired
    private IUserBasicDAO userBasicDAO;
    @Autowired
    private IAccountDAO accountDAO;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAuthorizationDAO authorizationDAO;
    @Autowired
    private IRoleAuthorizationDAO roleAuthorizationDAO;


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
        System.out.println(account+"021213135");
    }

    @Test
    public void testRoleDao(){
        Role role = new Role();
        role.setId(UUID.randomUUID().toString());
        role.setRoleCode("TTTTTTT");
        role.setRoleName("机");
        role.setMark("dsfawefewf");
        this.roleDAO.insert(role);
    }

    @Test
    public void testRoleService() throws NoSuchAlgorithmException {
/*        for(int i=0;i<3;i++){
            Role role = new Role();
            role.setId(UUID.randomUUID().toString());
            role.setRoleName("角色"+i);
            role.setRoleCode("ROLE"+i);
            role.setMark("MARK"+i);
            roleService.insertRole(role);
        }
        for(int j=0;j<3;j++){
            AuthorizationAnno authorization = new AuthorizationAnno();
            authorization.setId(UUID.randomUUID().toString());
            authorization.setAuthorizationName("权限"+j);
            authorization.setAuthorizationCode("AUTHORIZATION"+j);
            authorization.setWorkUrl("www.baidu.com"+j);
            authorization.setMark("MARK"+j);
            authorizationDAO.insert(authorization);
        }*/
        String [] roleIds = new String[3];
        String [] authorizationIds = new String[3];
        roleIds[0] = "a6b7986f-2ffa-4e98-b116-128db538907c";
        roleIds[1] = "8e331d3b-b503-40d4-8597-28cbfbf8fd1c";
        roleIds[2] = "55d3cfbb-4735-4b88-8ae1-29c0df99fd48";
        authorizationIds[0] = "ec7237b3-c587-4685-affd-4b809e39fbff";
        authorizationIds[1] = "12805466-b236-4dfb-936f-3ced8ccd55b8";
        authorizationIds[2] = "ecf3dbb7-d4e2-45dc-b8f8-2de636a3070f";
/*        for (int i=0;i<authorizationIds.length;i++){
            RoleAuthorization ra = new RoleAuthorization();
            ra.setId(UUID.randomUUID().toString());
            ra.setAuthorizationId(authorizationIds[i]);
            ra.setroleid(roleids[2]);
            roleAuthorizationDAO.insert(ra);
        }*/
        List<Authorization> authorizations = this.authorizationDAO.selectAuthorizationWithRoles();
        List<Role> roles = this.roleDAO.selectRoleWithAuthorization();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(JSONArray.toJSONString(roles,true));
        System.out.println("----------------------------------------------------------------------------");
    }

    @Test
    public void testPaginationAccount(){
        Calendar c = Calendar.getInstance();
        c.set(2016,5,1,0,0,0);
        Account account = new Account();
        account.setCreateTime(c.getTime());
        account.setAccountName("chenzhao");
        account.setPassword("fawefawwef");
        account.setOffset(0);
        account.setLimit(10);
        List<Account> accounts = this.accountDAO.getAccountPagination(account);
        System.out.println(JSONArray.toJSONString(accounts,true));
    }

    @Test
    public void testGetAccountPaginationCount(){
        Calendar c = Calendar.getInstance();
        Account account = new Account();
        account.setCreateTime(c.getTime());
        account.setAccountName("chenzhao");
        account.setPassword("fawefawwef");
        long count = this.accountDAO.getAccountPaginationCount(account);
        System.out.println(count);
    }
}
