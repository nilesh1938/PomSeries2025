package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver  driver;
    private ElementUtil eleUtil;
    private Map<String, String> productMap = new LinkedHashMap<>();

    private By productHeader = By.tagName("h1");
    private By metaData= By.xpath("//div[@class='col-sm-4']//ul[1]/li");
    private By priceData= By.xpath("//div[@class='col-sm-4']//ul[2]/li");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getproductHeader(){
       String productHeaderValue = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
       return productHeaderValue;
    }

    private void getMetadata(){
        List<WebElement> metaDataDetails= eleUtil.getElements(metaData);

        for (WebElement meta: metaDataDetails){
            System.out.println(meta.getText());
            String[] metaData = meta.getText().split(":");
            productMap.put(metaData[0].trim(), metaData[1].trim());
        }
    }

    private void getPricedata(){
        List<WebElement> priceList= eleUtil.getElements(priceData);
        String price = priceList.get(0).getText().trim();
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productPrice", price);
        productMap.put("exTaxPrice", exTaxPrice);
    }

    public Map<String, String> getProductData(){
        productMap.put("productHeader", getproductHeader());
        getMetadata();
        getPricedata();
        System.out.println(productMap);
        return productMap;
    }
}
