package com.automated.pazarama.Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import static com.automated.pazarama.ExtentReports.ExtentTestManager.getTest;

import static org.testng.Assert.assertTrue;

public class Reuse extends StepInit {
    private static final Logger LOGGER = LogManager.getLogger(Reuse.class);

    public static WebElement waitVisibleByLocator(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Web element is not visible!");
        }
        return element;
    }

    public static void waitUntilURLContains(String url) {
        webDriverWait.until(ExpectedConditions.urlContains(url));
    }

    public static void waitUntilURLEquals(String url) {
        webDriverWait.until(ExpectedConditions.urlToBe(url));
    }

    public static void waitForPresenceOfElement(By by) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static Boolean priceUpdateExist() {
        List<WebElement> el = getDriver().findElements((By.cssSelector("div.blockUI.blockMsg.blockPage")));
        return el.size() > 0;
    }



    public static WebElement waitInVisibilityOfElement(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Web element is visible!");
        }
        return element;
    }

    public static void waitVisibleByLocator(By element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            LOGGER.error("Web element is not visible!");
        }
    }

    public static WebElement waitUntilTextToBePresentInElement(WebElement element, String text) {
        try {
            webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            LOGGER.error("Web element is not editable!");
        }
        return element;
    }

    public static boolean isClickable(WebElement webElement) {
        try {
            if (webElement != null) {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException | TimeoutException | ElementNotInteractableException var2) {
            return false;
        }
    }

    public static WebElement waitClickableByOfElement(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            LOGGER.error("Web element is not clickable!");
        }
        return element;
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            LOGGER.info("Waiting for page to load...");
            webDriverWait.until(expectation);
        } catch (Throwable error) {
            LOGGER.error("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    public static String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }

    public static void hoverElement(WebElement webElement, int second) {
        action.moveToElement(webElement).pause(Duration.ofSeconds(second)).perform();
    }

    public static String strValidEmail = getSaltString() + "@email.com";

    public static String checkHexCodeElementWithValue(WebElement webElement, String cssValue) {
        String element = waitVisibleByLocator(webElement).getCssValue(cssValue);
        return Color.fromString(element).asHex();
    }

    public static String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static void checkStatusNetwork() throws IOException {
        LOGGER.info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");
        getTest().info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");

        HttpURLConnection cn = (HttpURLConnection) new URL(getDriver().getCurrentUrl()).openConnection();

        cn.setRequestMethod("HEAD");
        cn.connect();
        Integer c = cn.getResponseCode();

        LOGGER.info(getDriver().getCurrentUrl() + " Http status code: " + c);
        getTest().info(getDriver().getCurrentUrl() + " Http status code: " + c);

        Assert.assertFalse(c.toString().startsWith("4") || c.toString().startsWith("5"), c + "Invalid Link " + getDriver().getCurrentUrl());
        cn.disconnect();
    }

    public static WebElement getButtonWithText(String buttonName) {
        return getDriver().findElement(By.xpath("//button[normalize-space()='" + buttonName + "']"));
    }

    public static void checkStatusNetworkFor300() throws IOException {
        LOGGER.info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");
        getTest().info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");

        HttpURLConnection cn = (HttpURLConnection) new URL(getDriver().getCurrentUrl()).openConnection();

        cn.setRequestMethod("HEAD");
        cn.connect();
        Integer c = cn.getResponseCode();

        LOGGER.info(getDriver().getCurrentUrl() + " Http status code: " + c);
        getTest().info(getDriver().getCurrentUrl() + " Http status code: " + c);

        Assert.assertFalse(c.toString().startsWith("2") || c.toString().startsWith("4") || c.toString().startsWith("5"), c + "Invalid Link " + getDriver().getCurrentUrl());
        cn.disconnect();
    }

    public static void imgCheckStatusNetwork(String imgURL) throws IOException {
        LOGGER.info("Kullanıcı sayfanın img'in Network status'üne bağlanıyor.");
        getTest().info("Kullanıcı sayfanın img'in Network status'üne bağlanıyor.");
        try {
            HttpURLConnection cn = (HttpURLConnection) new URL(getDriver().getCurrentUrl()).openConnection();

            cn.setRequestMethod("HEAD");
            cn.connect();
            Integer c = cn.getResponseCode();
            LOGGER.info(imgURL + " Http status code: " + c);
            getTest().info(imgURL + " Http status code: " + c);

            Assert.assertFalse(c.toString().startsWith("4") || c.toString().startsWith("5"), c + "Broken pic " + imgURL);
            cn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkStatusNetworkNotAssertion() throws IOException {
        LOGGER.info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");
        getTest().info("Kullanıcı sayfanın Http Network status'üne bağlanıyor.");

        HttpURLConnection cn = (HttpURLConnection) new URL(getDriver().getCurrentUrl()).openConnection();

        cn.setRequestMethod("HEAD");
        cn.connect();
        Integer c = cn.getResponseCode();

        LOGGER.info(getDriver().getCurrentUrl() + " Http status code: " + c);
        getTest().info(getDriver().getCurrentUrl() + " Http status code: " + c);

        cn.disconnect();
    }

    public static String getTextFromElement(WebElement element) {
        return waitVisibleByLocator(element).getText();
    }

    public static WebElement setTextToElement(WebElement webElement, String text) {
        WebElement element = waitVisibleByLocator(webElement);
        element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), text);
        return element;
    }

    public static boolean isDisplayElement(WebElement webElement) {
        return waitVisibleByLocator(webElement).isDisplayed();
    }

    public static boolean isEnableElement(WebElement webElement) {
        return waitVisibleByLocator(webElement).isEnabled();
    }

    public static String getAttributeElement(WebElement webElement, String attribute) {
        return waitVisibleByLocator(webElement).getAttribute(attribute);
    }

    public static String getCssValueElement(WebElement webElement, String cssValue) {
        return waitVisibleByLocator(webElement).getCssValue(cssValue);
    }

    public static void imgCheckStatusNetworkHttp(String imgURL) throws IOException {
        LOGGER.info("Kullanıcı sayfanın img'in Network status'üne bağlanıyor.");
        getTest().info("Kullanıcı sayfanın img'in Network status'üne bağlanıyor.");
        try {
            HttpURLConnection cn = (HttpURLConnection) new URL(getCurrentURL())
                    .openConnection();

            cn.setRequestMethod("HEAD");
            cn.connect();
            Integer c = cn.getResponseCode();
            LOGGER.info(imgURL + " Http status code: " + c);
            getTest().info(imgURL + " Http status code: " + c);

            Assert.assertFalse(c.toString().startsWith("4") || c.toString().startsWith("5"), c + "Broken pic " + imgURL);
            cn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assertContains(Object actual, Object... expected) {
        assertTrue(Arrays.asList(expected).contains(actual), "text did not match");
    }

    public static void resultTakeScreenShot(String scenarioName) {
        takeScreenShot(scenarioName, false);
    }

    public static void errorMessage(String scenarioName, String message) {
        takeScreenShot(scenarioName, true);
        Assert.fail(message);
    }

    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("d MMMMM yyyy", new Locale("tr"));
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void clickElement(WebElement webElement) {
        WebElement element = waitVisibleByLocator(webElement);
        waitClickableByOfElement(element).click();
    }

    public static String getTabTitle() {
        return getDriver().getTitle();
    }

    public static String checkElementSize(WebElement webElement) {
        WebElement element = waitVisibleByLocator(webElement);
        return (element.getSize().getHeight() + "x" + element.getSize().getWidth());
    }

    public static String checkWebelementSize(WebElement webElement) {
        return (webElement.getSize().getHeight() + "x" + webElement.getSize().getWidth());
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void clickWithJSWithoutScroll(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript(command, element);
    }

    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript(command);
    }

    public static void cleanByJs(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].value = '';", element);
    }

    public static List<String> getDropdownValues(WebElement webElement) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)));
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public static void selectDropdownValue(WebElement webElement, int index) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)));
        select.selectByIndex(index);
    }

    public static String getFirstSelectedOption(WebElement webElement) {
        Select select = new Select(webDriverWait.until(ExpectedConditions.visibilityOf(webElement)));
        return select.getFirstSelectedOption().getText();
    }

    public static Select selectOpt(WebElement we) {
        return new Select(we);
    }

    public static String getAttr(String attName, WebElement webElement) {
        LOGGER.info("Element attribute is -> " + webElement.getAttribute(attName));
        return webElement.getAttribute(attName);
    }

    public static void switchToWindow() {
        String origin = getDriver().getWindowHandle();
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(origin)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public static void switchToMainWindow(String main) {
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(main)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }
        getDriver().switchTo().window(main);
    }

    public static String takeScreenShot(String methodName, boolean isFail) {

        try {
            String fail = isFail ? "FailTest" : "TestResult";
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
            String time = formatterDate.format(new Date());

            String screenshotLoc = System.getProperty("user.dir") + File.separator + "ScreenshotFile" + File.separator + fail + File.separator + time + "_" + methodName.replaceAll(" ", "") + ".png";

            File srcFiler = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(srcFiler, new File(screenshotLoc));
            return screenshotLoc;
        } catch (IOException e) {
            LOGGER.error("Error occurred in screenshot file operations!", e);

            Assert.fail(e.getMessage());

            return null;
        }
    }

    public static String fromTextToURL(String FromTextToURL) {
        String text = FromTextToURL;

        char[] trChar = new char[]{'İ', 'ı', 'ü', 'Ü', 'ç', 'Ç', 'Ğ', 'ğ', 'Ş', 'ş', 'ö', 'Ö', ' ', 'A', 'Y', ' '};
        char[] enChar = new char[]{'i', 'i', 'u', 'u', 'c', 'c', 'g', 'g', 's', 's', 'o', 'o', '-', 'a', 'y', ' '};
        for (int i = 0; i < trChar.length; i++) {
            text = text.replace(trChar[i], enChar[i]).toLowerCase();
        }
        return text;
    }

    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1) try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                y++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (WebDriverException we) {
                y++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Integer generateRandomNumber(int range) {
        return new Random().nextInt(range);
    }

    public static String getOSLanguage() {
        return System.getProperty("user.language");
    }

    public static String getValidEmail() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@email.com";
    }

    public static List<String> imageSizeList(List<WebElement> webElements, int count) {

        List<String> imageSizeListH = new ArrayList<>();
        List<String> imageSizeListW = new ArrayList<>();
        List<String> imagesSize = new ArrayList<>();

        for (int i = 0; i < webElements.size(); i++) {
            imageSizeListH.add(Integer.toString(webElements.get(i).getSize().getHeight()));
            imageSizeListW.add(Integer.toString(webElements.get(i).getSize().getWidth()));
            imagesSize.add(imageSizeListW.get(i) + "x" + imageSizeListH.get(i));


        }
        return imagesSize;
        //return Collections.singletonList(imageSizeListH + "x" + imageSizeListW);
    }

    public static void allDeleteReusable(WebElement element, String attribute) throws InterruptedException {
        LOGGER.info("Input alanındaki text'in tamamı silinir");
        getTest().info("Input alanındaki text'in tamamı silinir");
        while (!element.getAttribute(attribute).equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
            Thread.sleep(20);
        }
    }


    public static void Wait(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> void info(T message) {
        getTest().info(message.toString());
        LOGGER.info(message.toString());
    }

    public static void sendKeysAsACharacters(WebElement Element, String text) {
        for (int i = 0; i < text.length(); i++) {
            String character = String.valueOf(text.charAt(i));
            Wait(50);
            Element.sendKeys(character);
        }
    }
}