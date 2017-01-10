package utils;

import gui.pages.*;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    private Pages(){}

    static LoginPage lp;

    // Take the PO class as input parameter, initialize its elements and return the PO
    public static  <T> T getPage(Class<T> cls) {
        T page = null;
        try {
            page = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PageFactory.initElements(Driver.getInstance(), page);
        return page;
    }

    // Lazy initialization of LoginPage
    public static LoginPage loginP() {
        if (lp == null){
            lp = getPage(LoginPage.class);
        }
        return lp;
    }

    public static DashboardPage dashboardP() {
        return (getPage(DashboardPage.class));
    }

    public static PostsPage postsP() {
        return (getPage(PostsPage.class));
    }

    public static EditorPage editorP() {
        return (getPage(EditorPage.class));
    }

    public static ViewPostPage viewPostP() {
        return (getPage(ViewPostPage.class));
    }

    public static PublishedPostPage publishedPostP() {
        return getPage(PublishedPostPage.class);
    }

    public static MyProfilePage myProfilePage() {
        return (getPage(MyProfilePage.class));
    }


}
