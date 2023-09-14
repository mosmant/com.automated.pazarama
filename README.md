![](src/test/resources/faladdin.png)

# PAZARAMA QA Task

- This project was built on;
  - Build management tool -> Maven 
  - Language -> Java
  - Assertion tool -> TestNG
  - Model -> Page Object Model
  - Report -> extent report with base64
  
# Structure
- In base;
  - Reuse -> where ready and reuseable methods are saved
  ```java
  public class Reuse extends StepInit{

    private static final Logger LOGGER = LogManager.getLogger(Reuse.class);

    public static WebElement waitVisibleByLocator(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Web element is not visible!");
        }
        return element;
    }
  }
  ```
  - BaseTest -> where platform and application are set to driver before All
  ```java
  public class BaseTest {

   @Parameters({"platformVersion", "deviceName", "appPackage", "appActivity", "appWaitForLaunch"})
    @BeforeClass(alwaysRun = true)
    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver setDriver(String browser) {

        if (driver == null) {
            browser = browser == null ? ConfigReader.getProperty("browser") : browser;

            switch (browser) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    options.addArguments("--start-maximized");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--acceptInsecureCerts");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-notifications");
                    //options.setExperimentalOption("w3c", false);
                    //options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private-window");
                    firefoxOptions.addArguments("--headless");

                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                default:
                    driver = new ChromeDriver();
            }
        }
  }
  ```

- In extentReports;
  - ExtentManager -> It helps us to create meta-data of the report
  - ExtentTestManager -> It includes report methods that help us to generate report details while tests run

- In listener;
  - AnnotationTransformer -> It analysis tests results
  - Listener -> Includes methods which decide test run situations and get information
  - Retry -> Runs with AnnotationTransformer and re-runs the failed tests

- In pages
  - AccountPage
  - ExplorePage
  - HomePage
  - LoginTypes

- In utilities
  - DriverThread -> Helps us to run tests with multi devices

- In test
  - Login -> tests are called and run this class
  ```java
    @Test(description = "https://www.pazarama.com web sitesine giriş yapılır.Login olunabildiği doğrulanır", groups = {"a:Osman", "t:Case"})
    public void goToPazaramaPage() throws InterruptedException {
        loginPageSteps.goTo_PazaramaPage();
        loginPageSteps.Login_PazaramaPage();
        loginPageSteps.verify_LoggedIn();
        mainPageSteps.select_RandomCategories();
        mainPageSteps.sorting_Price();
        mainPageSteps.get_ReferrerPolicy();
        mainPageSteps.verify_PriceUI();
    }
  ```
- testRunner
  - Runner.xml -> runs the tests
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

<suite name="Suite_1">

  <listeners>
    <listener class-name="com.automated.pazarama.Listener.ExtentITestListenerClassAdapter" />
  </listeners>

  <test name="Study Case" >
    <parameter name="browser" value="chrome"/>
    <classes>
      <class name="Runner.Login" >
      </class>
    </classes>
  </test>
</suite>
```