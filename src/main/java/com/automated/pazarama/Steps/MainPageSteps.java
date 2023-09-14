package com.automated.pazarama.Steps;

import com.automated.pazarama.Pages.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v116.network.Network;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

import static com.automated.pazarama.Utilities.Reuse.*;

public class MainPageSteps extends MainPage {

    public void select_RandomCategories() throws InterruptedException {
        info("Kullanici Menüde rastgele bir kategoriye gider. Gidilen kategori ismi info olarak yazdırılır.");

        Random rnd = new Random();

        int i = rnd.nextInt(MAINPAGE_NAVITEMS_CATEGORIES.size());
        info(getTextFromElement(MAINPAGE_NAVITEMS_CATEGORIES_TEXT.get(i)));
        hoverElement(MAINPAGE_NAVITEMS_CATEGORIES.get(i), 5);

        int j = rnd.nextInt(MAINPAGE_SUBITEMS_CATEGORIES_TEXT.size());
        info(getTextFromElement(MAINPAGE_SUBITEMS_CATEGORIES_TEXT.get(j)));
        clickElement(MAINPAGE_SUBITEMS_CATEGORIES_TEXT.get(j));

        Thread.sleep(2000);

        int k = rnd.nextInt(ITEMSHEARTBUTTON.size());
        clickWithJS(ITEMSHEARTBUTTON.get(k));
        String heartedItem = getTextFromElement(HEARTEDITEMTEXT.get(k));

        clickWithJS(FAVORITESTBUTTON);
        Wait(3000);
        String favoriteItem = getTextFromElement(HEARTEDITEMTEXT.get(0));
        info("Kullanici rastgele kategorideki urunu begenir. Favoriler ile karsilastirir ve bir onceki sayfaya doner.");
        Assert.assertEquals(heartedItem, favoriteItem);

        getDriver().navigate().back();
    }

    public void sorting_Price() {
        info("Kullanici ayni sayfada fiyat siralamasi dropdown'da artan fiyata göre sıralama seçeneğini seçer.");
        clickElement(PRICERANKING);
        Wait(3000);
        info(getTextFromElement(MULTISELECTDROPDOWN.get(1)));
        clickElement(MULTISELECTDROPDOWN.get(1));
        Wait(5000);
    }

    public void verify_PriceUI() {
        List<Double> prices = new ArrayList<>();

        for (int i = 0; i < PRICELISTOFPRODUCT.size(); i++) {
            System.out.println(PRICELISTOFPRODUCT.get(i).getText());
            double price = Double.parseDouble(PRICELISTOFPRODUCT.get(i).getText().replace("TL", "").replace(",", ".").replace(" ", "").trim());
            prices.add(price);
            System.out.println(price);
        }
        info("Kullanici sayfada fiyata göre sıralama yapilip yapilmadgini dogrular.");
        info("FIYAT LISTESI = " + prices);
        isListInAscendingOrder(prices);
    }

    private static void isListInAscendingOrder(List<Double> prices) {
        List<Double> prices1 = prices.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        Assert.assertEquals(prices,prices1);

    }

    public static void get_ReferrerPolicy() {
        info("Atilan istegin headers’inda 'Referrer Policy' parametresinin 'no-referrer-when-downgrade' oldugu dogrulanir");
        DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
            String referrerPolicy = (String) requestWillBeSent.getRequest().getHeaders().get("Referrer-Policy");
            System.out.println("Referrer Policy: " + referrerPolicy);
            Assert.assertEquals(referrerPolicy, "no-referrer-when-downgrade");
        });
    }

}

