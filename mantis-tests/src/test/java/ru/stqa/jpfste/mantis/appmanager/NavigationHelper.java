package ru.stqa.jpfste.mantis.appmanager;


public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager app) {
		super(app);
	}

	public void manageUserPage() {
		wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
	}
}