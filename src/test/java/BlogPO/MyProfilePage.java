package BlogPO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyProfilePage extends Page{

    String PAGE_URL = "https://wordpress.com/me";

    @FindBy(how = How.CSS, using = ".me-sidebar__signout-button")
    WebElement signoutButton;
    @FindBy(how = How.ID, using = "first_name")
    WebElement firstName;
    @FindBy(how = How.ID, using = "last_name")
    WebElement lastName;
    @FindBy(how = How.ID, using = "display_name")
    WebElement displayName;
    @FindBy(how = How.ID, using = "description")
    WebElement description;
    @FindBy(css = "button[type='submit']")
    WebElement saveProfileButton;
    @FindBy(css = ".notice__text")
    WebElement savedSuccessfullyBar;
    @FindBy(css = ".notice__dismiss")
    WebElement savedSuccessfullyBarClose;


    public void navigate() {
        navigateToPageUrl(PAGE_URL);
    }

    public boolean isSaveProfileButtonActive(){
        return saveProfileButton.isEnabled();
    }

    public void fillFirstNameField(String data){
        firstName.clear();
        firstName.sendKeys(data);
    }

    public void clickSaveProfileButton(){
        saveProfileButton.click();
    }

    public boolean isSavedSuccessfullyBarAppears(){
        return savedSuccessfullyBar.isDisplayed();
    }

    public String getTextSavedSuccessfullyBar(){
        return savedSuccessfullyBar.getText();
    }

    public void closeSavedSuccessfullyBar(){
        savedSuccessfullyBarClose.click();
    }



}
