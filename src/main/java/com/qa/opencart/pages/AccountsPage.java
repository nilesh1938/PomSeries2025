package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By logoutLink = By.linkText("Logout");
    private By headers = By.cssSelector("div#content h2");
    private By search = By.name("search");
    private By searchIcon = By.cssSelector("div#search button");

    public AccountsPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getAccountPageTitle(){
        String title = eleUtil.waitForTitleContainsAndReturn(
                AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
        System.out.println("Account page title: "+title);
        return title;
    }

    public  boolean isLogoutLinkExist(){
        return  eleUtil.isElementDisplayed(logoutLink);
    }

    public int getAccountPageHeadersCount(){
        return  eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
    }

    public List<String> getAccountPageHeaders() {
        List<WebElement> headerList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        List<String> headerValueList = new ArrayList<>();

        for (WebElement ele : headerList) {
            String header = ele.getText();
            headerValueList.add(header);
        }
    return headerValueList;
    }

    public ResultPage doSearch(String searchKey){
        WebElement searchElement = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
        searchElement.clear();
        searchElement.sendKeys(searchKey);
        eleUtil.doClick(searchIcon);
        return new ResultPage(driver);

    }


}
