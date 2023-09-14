package com.automated.pazarama.Steps;

import com.automated.pazarama.Pages.LoginPage ;
import com.automated.pazarama.Utilities.* ;
import org.testng.Assert;

import static com.automated.pazarama.Base.BaseTest.getDriver;
import static com.automated.pazarama.Utilities.Reuse.*;

public class LoginPageSteps extends LoginPage {

    public void goTo_PazaramaPage() {
        info("Kullanici Browser'i acar ve " + ConfigReader.getProperty("URL") + " sayfasina gider.");
        getDriver().get(ConfigReader.getProperty("URL"));
    }

    public void Login_PazaramaPage(){
        info("Kullanici 'Giriş Yap / Üye Ol ' butonuna basar.");
        clickElement(SIGNIN);

        info("Kullanici 'E-Posta adresiniz' yazan texbox'i doldurur.");
        clickElement(SIGNIN_EMAIL_TEXBOX);
        sendKeysAsACharacters(SIGNIN_EMAIL_TEXBOX,ConfigReader.getProperty("EMAIL"));

        info("Kullanici 'Şifreniz' yazan texbox'i doldurur.");
        clickElement(SIGNIN_PASS_TEXBOX);
        sendKeysAsACharacters(SIGNIN_PASS_TEXBOX,ConfigReader.getProperty("PASS"));

        info("Kullanici gerekli bilgilerin girişini yapar ve 'DEVAM' butonuna basar.");
        clickElement(SIGNIN_SUBMIT_BUTTON);
    }

    public void verify_LoggedIn(){
        info("Kullanici LOGIN oldugunu dogrular.");
        String theNameBroughtfullName = getTextFromElement(MAINPAGE_FULLNAME_TEXTAREA);
        String existingFullName = ConfigReader.getProperty("NAMES")+" "+ConfigReader.getProperty("SNAME");
        Assert.assertEquals(theNameBroughtfullName,existingFullName);
    }

}
