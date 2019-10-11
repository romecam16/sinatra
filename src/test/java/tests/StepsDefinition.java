package tests;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepsDefinition {

    public WebDriver driver;

    @Given("usuario abre la aplicacion")
    public void usuario_abre_la_aplicacion() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://songs-by-sinatra.herokuapp.com");

    }

    @When("usuario diligencia las credenciales")
    public void usuario_diligencia_las_credenciales() {
        WebElement loginLink = driver.findElement(By.xpath("//a[@href='/login']"));
        loginLink.click();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));

        usernameField.sendKeys("frank");
        passwordField.sendKeys("sinatra");
        loginButton.click();

    }

    @Then("usuario debe estar en el home page")
    public void usuario_debe_estar_en_el_home_page() throws Exception {

        String url = driver.getCurrentUrl();
        if (url.equals("https://songs-by-sinatra.herokuapp.com/")) {
            System.out.println("Test passed");
        } else {
            throw new Exception("URL no es homepage" + url);
        }
    }

    @And("usuario visualiza el mensaje de bienvenida")
    public void usuario_visualiza_el_mensaje_de_bienvenida() throws Exception {

        WebElement loggedInMessage = driver.findElement(By.id("flash"));
        if (loggedInMessage.getText().equals("You are now logged in as frank"))
            System.out.println("Test Passed");
        else {
            throw new Exception("Mensaje de bienvenida no està presente");
        }


    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
