package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    WebDriver driver;

    public  <T> T GetPage(Class<T> cls) throws IllegalAccessException, InstantiationException {
        T page = (T) cls.newInstance();
        PageFactory.initElements(driver, page);
        return page;
    }

    public LoginPage LoginP() throws InstantiationException, IllegalAccessException
    {
        return (GetPage(LoginPage.class));
    }

}
