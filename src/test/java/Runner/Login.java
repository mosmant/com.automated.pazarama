package Runner;

import com.automated.pazarama.Utilities.StepInit ;
import org.testng.annotations.Test;

public class Login extends StepInit {

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
}
