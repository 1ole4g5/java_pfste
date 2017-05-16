package ru.stqa.jpfste.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.jpfste.mantis.model.UserData;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void changePass(UserData user) {
        selectUserById(user.getId());
        resetPassword();
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void selectUserById(int id) {
        click(By.cssSelector(String.format("a[href$='edit_page.php?user_id=%s']", id)));
    }

    public void logout() {
        wd.get(app.getProperty("web.baseUrl") + "/logout_page.php");
    }
}