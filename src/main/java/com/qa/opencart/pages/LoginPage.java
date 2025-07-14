package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By emailTextBox= By.id("input-email");
    private By passwordTextBox= By.id("input-password");
    private By loginBtn= By.xpath("//input[@value='Login']");
    private By forgotPwdLink= By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
    private By registerLink = By.linkText("Register");

    public LoginPage(WebDriver driver){
        this.driver= driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getLoginPageTitle(){
        String title = eleUtil.waitForTitleContainsAndReturn(
                                AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
        System.out.println("login page title: "+title);
        return title;
    }

    public String getLoginPageURL(){
        String url = eleUtil.waitForURLContainsAndReturn(
                                AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
        System.out.println("login page url: "+url);
        return url;
    }

    public boolean isForgotPwdLinkExist(){
        return  eleUtil.isElementDisplayed(forgotPwdLink);
    }

    public AccountsPage doLogin(String email, String password){
        eleUtil.waitForElementVisible(emailTextBox, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(email);
        eleUtil.doSendKeys(passwordTextBox, password);
        eleUtil.doClick(loginBtn);
//        String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_LONG_TIME_OUT);
//        System.out.println("Account page title "+title);
        return new AccountsPage(driver);
    }

    public RegisterPage navigateToRegisterPage(){
        eleUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }


}
