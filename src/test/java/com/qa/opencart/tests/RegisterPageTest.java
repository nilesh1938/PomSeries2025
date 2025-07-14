package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

    @BeforeClass
    public void regSetup(){
        registerPage = loginPage.navigateToRegisterPage();
    }

    public String getRandomEmail(){
        return "uiAutomation"+System.currentTimeMillis()+"@open.com";
    }
@DataProvider
    public Object[][] getRegisterData(){
        return new Object[][]{
                {"Venna","automation","9889898955", "veena@1202","yes"},
                {"Raj","automation","9889898955", "veena@1202","yes"}
        };
    }
    @Test(dataProvider ="getRegisterData" )
    public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe){
        Assert.assertTrue(registerPage.userRegistration(firstName, lastName,getRandomEmail(),
                telephone,password,subscribe));

    }
}
