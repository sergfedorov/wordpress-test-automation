package BlogPO;

import BlogTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//
public class Pages {

    // Take the PO class as input parameter, initialize its elements and return the PO
    public static  <T> T GetPage(Class<T> cls) {
        T page = null;
        try {
            page = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PageFactory.initElements(BaseTest.driver, page);
        return page;
    }

    public static LoginPage LoginP() {
        return (GetPage(LoginPage.class));
    }

    public static DashboardPage DashboardP() {
        return (GetPage(DashboardPage.class));
    }

    public static PostsPage PostsP() {
        return (GetPage(PostsPage.class));
    }

    public static EditorPage EditorP() {
        return (GetPage(EditorPage.class));
    }

    public static ViewPostPage ViewPostP() {
        return (GetPage(ViewPostPage.class));
    }

    public static PublishedPostPage PublishedPostP() {
        return (GetPage(PublishedPostPage.class));
    }


}