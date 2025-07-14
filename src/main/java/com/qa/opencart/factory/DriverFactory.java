package com.qa.opencart.factory;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DriverFactory {

    WebDriver driver;
    Properties prop;

    OptionsManager optionsManager;
    static ThreadLocal<WebDriver>  tlDriver = new ThreadLocal<>();


    /**
     * This method is used to initialize the driver basis of given browsername
     * @param prop
     * @return it returns driver
     */
    public WebDriver initDriver(Properties prop){
        String browserName = prop.getProperty("browser");
        System.out.println("browser name is "+ browserName);

        optionsManager = new OptionsManager(prop);

        switch (browserName.toLowerCase().trim()){
            case "chrome" :
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "firefox" :
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "edge" :
                 tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            default:
                System.out.println(AppError.INVALID_BROWSER_MSG +browserName+ " is invalid");
                throw  new BrowserException("Invalid Browser "+browserName);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    /**
     * This method is used to initialize the properties from the config file
     * @return properties object
     */
    public Properties initProp() {
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");
        System.out.println("running tests on env : " + envName);
        try {
            if (envName == null) {
                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right env name " + envName);
                        throw new FrameworkException("Invalid env name " + envName);
                }
            }
            prop.load(ip);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;

    }
}
