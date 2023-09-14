package com.automated.pazarama.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.automated.pazarama.Base.BaseTest.getDriver;

public class MainPage {
    public MainPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(css = ".flex.h-17.relative.z-40 li")
    public static List<WebElement> MAINPAGE_NAVITEMS_CATEGORIES;

    @FindBy(css = ".flex.h-17.relative.z-40 li a span")
    public static List<WebElement> MAINPAGE_NAVITEMS_CATEGORIES_TEXT;

    @FindBy(css = "a[class=\"text-gray-600 text-xxs font-normal hover:text-blue-500 hover:underline\"]")
    public static List<WebElement> MAINPAGE_SUBITEMS_CATEGORIES_TEXT;

    @FindBy(css = "div[class='mb-10'] span[class='inline-block align-middle relative']")
    protected static WebElement PAZARAMA_BOTTOM_LOGO;

    @FindBy(css = ".nuxt-link-active.text-blue-500.text-base.font-semibold")
    protected static WebElement PAZARAMA_TOP_LOGO;

    @FindBy(css = "[class=\"svg text-gray-300  svg--heart absolute top-2.5 right-3 z-20 hover:text-gray-400\"]")
    protected static List<WebElement> ITEMSHEARTBUTTON;

    @FindBy(css = "[class*='line-clamp-2 text-gray-600 h-9 text-xs']")
    protected static List<WebElement> HEARTEDITEMTEXT;

    @FindBy(css = "a[href='/hesabim/begendiklerim']")
    protected static WebElement FAVORITESTBUTTON;

    @FindBy(css = ".text-gray-500.w-full.whitespace-nowrap.overflow-hidden.text-left.text-ellipsis.font-semibold.text-sm")
    protected static WebElement PRICERANKING;

    @FindBy(css = ".multiselect__content-wrapper ul li[role='option']")
    protected static List<WebElement> MULTISELECTDROPDOWN;

    @FindBy(css = "[class='leading-tight font-semibold text-huge text-gray-600']")
    protected static List<WebElement> PRICELISTOFPRODUCT;

}
