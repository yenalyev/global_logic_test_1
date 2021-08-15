package test;

import entity.User;
import helper.RequestHelper;
import helper.UserHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class UserCrudTest {
    private static Logger logger = Logger.getLogger(UserCrudTest.class.getName());

    static User userUnderTest;
    static User user = new User.Builder()
            .setName("test test user")
            .setEmail("testUserMail@test.com")
            .setStatus("active")
            .setGender("male")
            .build();


    @Test(groups = {"user", "crud_user"}, priority = 1)
    public void checkAddUser() {
        logger.info("Start add user test execution");
        userUnderTest = UserHelper.addUser(user);
        logger.info("User - " + userUnderTest.toString() + " was created");
        Assert.assertTrue(UserHelper.compareUser(user, userUnderTest));
        logger.info("Add user test passed");
    }

    @Test(groups = {"user", "crud_user"}, priority = 2)
    public void checkGetUser() {
        logger.info("Start get user test execution");
        User getUser = UserHelper.getUser(userUnderTest.getId());
        logger.info("User - " + getUser.toString() + " was retrieved");
        Assert.assertTrue(UserHelper.compareUser(getUser, userUnderTest));
        logger.info("Get user test passed");
    }

    @Test(groups = {"user", "crud_user"}, priority = 2)
    public void checkCreateAndUpdateDate() {
        logger.info("Start check user create and update date test execution");
        User getUser = UserHelper.getUser(userUnderTest.getId());
        logger.info("User - " + getUser.toString() + " was retrieved");
        Assert.assertEquals(getUser.getCreatedAt(), getUser.getUpdateAt());
        logger.info("Check user create and update date test passed");
    }

    @Test(groups = {"user", "crud_user"}, priority = 3)
    public void checkUpdateUser() {
        logger.info("Start check update user test execution");
        User forUpdate = UserHelper.getUser(userUnderTest.getId());
        logger.info("Old user name - " + forUpdate.getName());
        forUpdate.setName("new test name");
        logger.info("New user name - " + forUpdate.getName());
        User afterUpdate = UserHelper.putUser(forUpdate);
        logger.info("User - " + afterUpdate.toString() + " was updated");
        Assert.assertEquals(afterUpdate.getName(), forUpdate.getName());
        logger.info("Check user update user test passed");
    }

    @Test(groups = {"user", "crud_user"}, priority = 4)
    public void checkDeleteUser() {
        logger.info("Start delete user test execution");
        UserHelper.deleteUser(userUnderTest);
        logger.info("User - " + userUnderTest.toString() + " was deleted");
        int responseCode = RequestHelper.doGetRequest("users/" + userUnderTest.getId()).jsonPath().getInt("code");
        Assert.assertEquals(responseCode, 404);
        logger.info("Delete user test execution passed");
    }

}