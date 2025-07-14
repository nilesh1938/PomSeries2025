package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoSetup(){
        accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void productHeaderTest(){
        productInfoPage=accountPage.doSearch("macbook").selectProduct("MacBook Pro");
        Assert.assertEquals(productInfoPage.getproductHeader(), "MacBook Pro");
    }

    @Test
    public void productInfoTest(){
        productInfoPage=accountPage.doSearch("macbook").selectProduct("MacBook Pro");
        Map<String, String> actualProductMap= productInfoPage.getProductData();
//        softAssert.assertEquals(actualProductMap.get("Brand"),"Apple");
//        softAssert.assertEquals(actualProductMap.get("Product Code"),"Product 18");
//        softAssert.assertEquals(actualProductMap.get("Reward Points"),"800");
//        softAssert.assertEquals(actualProductMap.get("Availability"),"Out Of Stock");
//        softAssert.assertEquals(actualProductMap.get("productPrice"),"$2,000.00");
//        softAssert.assertEquals(actualProductMap.get("exTaxPrice"),"$2,000.00");
//        softAssert.assertAll();

        Map<String, String> expectedProductMap = Map.of(
                "Brand", "Apple",
                "Product Code", "Product 18",
                "Reward Points", "800",
                "Availability", "Out Of Stock",
                "productPrice", "$2,000.00",
                "exTaxPrice", "$2,000.00"
        );

        expectedProductMap.forEach((key, expectedValue) ->
                softAssert.assertEquals(actualProductMap.get(key), expectedValue, "Mismatch for " + key)
        );

        softAssert.assertAll();

    }
}
