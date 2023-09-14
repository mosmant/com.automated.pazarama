package com.automated.pazarama.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.automated.pazarama.Base.BaseTest.getDriver;
import static com.automated.pazarama.Utilities.Reuse.waitVisibleByLocator;

public class LoginPage  {
    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(css = "a[class='btn btn-sm !w-37 px-0 text-gray-500 text-left']")
    public static WebElement SIGNIN;

    @FindBy(css = "#Username")
    protected static WebElement SIGNIN_EMAIL_TEXBOX;

    @FindBy(css = "#Password")
    protected static WebElement SIGNIN_PASS_TEXBOX;

    @FindBy(css = "#submit-button")
    protected static WebElement SIGNIN_SUBMIT_BUTTON;

    @FindBy(css = "span[class='ml-2.5 overflow-hidden w-full whitespace-nowrap text-ellipsis']")
    protected static WebElement MAINPAGE_FULLNAME_TEXTAREA;

}
