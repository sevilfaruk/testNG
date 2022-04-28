package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utility.Highlighter;

public class DependsOnTests {
    WebDriver driver;

    @Test
    public void test1_SetUpChrome ()
    {
        System.setProperty("webdriver.chrome.driver",
                "//Users//faruksevil//Desktop//testNG//testNG//src//test//driver//chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        System.out.println("1. Set Up Chrome");
    }

    @Test (dependsOnMethods = "test1_SetUpChrome")
    public void test2_OpenOrangeHRM ()
    {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        System.out.println("2. Open OrangeHRM");
    }

    @Test (dependsOnMethods = "test2_OpenOrangeHRM")
    public void test3_SignIn ()
    {
        WebElement textUsername = driver.findElement(By.id("txtUsername"));
        Highlighter.highlightElement(driver, textUsername);
        textUsername.sendKeys("Admin");

        WebElement textPassword = driver.findElement(By.id("txtPassword"));
        Highlighter.highlightElement(driver, textPassword);
        textPassword.sendKeys("admin123");

        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        Highlighter.highlightElement(driver, buttonLogin);
        buttonLogin.click();

        System.out.println("3. Sign In");
    }

    @Test  (dependsOnMethods = "test3_SignIn")
    public void test4_SearchUser ()
    {
        WebElement menuAdmin = driver.findElement(By.id("menu_admin_viewAdminModule"));
        Highlighter.highlightElement(driver, menuAdmin);
        menuAdmin.click();

        WebElement textUserName = driver.findElement(By.id("searchSystemUser_userName"));
        Highlighter.highlightElement(driver, textUserName);
        textUserName.sendKeys("Admin");

        WebElement buttonSearch = driver.findElement(By.id("searchBtn"));
        Highlighter.highlightElement(driver, buttonSearch);
        buttonSearch.click();

        System.out.println("4. Search For User");
    }

    @Test  (dependsOnMethods = { "test2_OpenOrangeHRM", "test3_SignIn" } ) //multiple depends
    public void test5_SearchEmployee ()
    {
        WebElement menuPIM = driver.findElement(By.xpath("//*[@id=\"menu_pim_viewPimModule\"]/b"));
        Highlighter.highlightElement(driver, menuPIM);
        menuPIM.click();

        WebElement buttonSearch = driver.findElement(By.id("searchBtn"));
        Highlighter.highlightElement(driver, buttonSearch);
        buttonSearch.click();

        System.out.println("5. Search For Employee");
    }

    @Test  (dependsOnMethods = { "test2_OpenOrangeHRM", "test3_SignIn" } )
    public void test6_SearchCandidate ()
    {
        WebElement menuRecruitment = driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewRecruitmentModule\"]/b"));
        Highlighter.highlightElement(driver, menuRecruitment);
        menuRecruitment.click();

        WebElement buttonSearch = driver.findElement(By.id("btnSrch"));
        Highlighter.highlightElement(driver, buttonSearch);
        buttonSearch.click();

        System.out.println("6. Search For Candidate");
    }

    @Test  (dependsOnMethods = { "test2_OpenOrangeHRM", "test3_SignIn" } )
    public void test7_SignOut ()
    {
        WebElement linkWelcome = driver.findElement(By.id("welcome"));
        Highlighter.highlightElement(driver, linkWelcome);
        linkWelcome.click();

        WebElement linkLogout = driver.findElement(By.xpath("//div[@id='welcome-menu']/descendant::a[contains(@href,'logout')]"));
        Highlighter.highlightElement(driver, linkLogout);
        linkLogout.click();

        System.out.println("7. Sign Out");
    }

    @Test (dependsOnMethods = "test7_SignOut")
    public void close(){
        driver.quit();
        System.out.println("8. Close");

    }
}
