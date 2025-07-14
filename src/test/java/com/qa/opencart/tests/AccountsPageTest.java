package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {


    @BeforeClass
    public void accountSetup(){
         accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accountPageTitleTest(){
        String actTitle= accountPage.getAccountPageTitle();
        Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
    }

    @Test
    public void isLogoutLinkExist(){
        Assert.assertTrue(accountPage.isLogoutLinkExist());
    }
    @Test
    public void accountPageHeadersCountTest(){
        Assert.assertEquals(accountPage.getAccountPageHeadersCount(),AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
    }

    @Test
    public void accountPageHeadersTest(){
        List<String> actualHeadersList= accountPage.getAccountPageHeaders();
        Assert.assertEquals(actualHeadersList, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
    }

    @DataProvider
    public Object[][] getSearchKey(){
        return  new Object[][]{
                {"macbook", 3},
                {"imac", 1},
                {"samsung", 2}
        };
    }

    @Test(dataProvider ="getSearchKey")
    public  void searchCountTest(String searchKey, int searchCount){
       resultPage= accountPage.doSearch(searchKey);
       Assert.assertEquals(resultPage.getSearchResultsCount(), searchCount);
    }

    @DataProvider
    public Object[][] getSearchData(){
        return new Object[][]{
                {"macbook", "MacBook Pro"},
                {"macbook","MacBook Air"},
                {"imac","iMac"}
        };
    }

    @Test(dataProvider ="getSearchData")
    public  void searchHeaderTest(String searchKey, String productName){
        resultPage= accountPage.doSearch(searchKey);
        productInfoPage= resultPage.selectProduct(productName);
       Assert.assertEquals(productInfoPage.getproductHeader(),productName);
    }
}
