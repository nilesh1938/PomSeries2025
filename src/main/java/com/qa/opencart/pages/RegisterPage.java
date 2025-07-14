package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");

    private By subscribeYes = By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']");
    private By subscribeNo = By.xpath("//input[@value='0']");

    private By agreeCheckBox= By.name("agree");
    private By continueBtn = By.xpath("//input[@value='Continue']");

    private By successMsg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public RegisterPage(WebDriver driver) {
        this.driver= driver;
        eleUtil= new ElementUtil(driver);
    }

    public boolean userRegistration(String firstName, String lastName,String email,
                                    String telephone, String password, String subscribe ){

        eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT)
                .sendKeys(firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.confirmPassword, password);

        if (subscribe.equalsIgnoreCase("yes")){
            eleUtil.doClick(subscribeYes);
        }
        else {
            eleUtil.doClick(subscribeNo);
        }
        eleUtil.doClick(agreeCheckBox);
        eleUtil.doClick(continueBtn);
        String successMsg = eleUtil.waitForElementVisible(this.successMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
        System.out.println(successMsg);
        if (successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)){
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);
            return  true;
        }
        else {
            return false;
        }
    }
}
