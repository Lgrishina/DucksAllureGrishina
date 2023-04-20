import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageOblects.MainPage;
import pageOblects.RubberDucksPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

//@Listeners(Listener.class)

public class BaseTest {

    protected String base_URL = "https://litecart.stqa.ru";
    protected WebDriver webDriver;
    protected MainPage mainPage;
    protected RubberDucksPage rubberDucksPage;
    Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        //это для того чтобы прикрутить ноду для запуска на хабе
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setBrowserName(CHROME);
////        caps.setVersion("112");
////        caps.setPlatform(Platform.LINUX);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.0.105:4444"), caps);

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        WebDriverRunner.setWebDriver(webDriver);
        mainPage = new MainPage(webDriver);
        rubberDucksPage = new RubberDucksPage(webDriver);

        mainPage = new MainPage(webDriver);
        rubberDucksPage = new RubberDucksPage(webDriver);
    }

    @BeforeTest
    public void beforeTest() throws MalformedURLException {

//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setPlatformName("Windows 11");
//        browserOptions.setBrowserVersion("latest");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "selenium-build-HE67Z");
//        sauceOptions.put("name", "<your test name>");
//        browserOptions.setCapability("sauce:options", sauceOptions);

//
//        URL url = new URL("https://oauth-liud.gre-c8522:7284f7dd-6dc5-49c3-8a38-0b7f74186c60@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//        RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);


        logger.info("Before test started");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        WebDriverRunner.setWebDriver(webDriver);
        mainPage = new MainPage(webDriver);
        rubberDucksPage = new RubberDucksPage(webDriver);
        logger.info("Before test ended");
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Before method deleting cookies");
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        logger.info("Openning " + base_URL);
        open(base_URL);
    }

    @AfterClass
    public void afterTest() {
        logger.info("Tests ended");
//        WebDriverRunner.getWebDriver().quit();
        webDriver.quit();
    }

    private static void executeCommand(String command) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}