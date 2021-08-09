import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WebDriver driver = initAndReturnDriver();
        System.out.println("Please enter your username and your password you use in order to enter the college website");
       String username=scanner.nextLine();
        String userPassword=scanner.nextLine();
        signInToMoodle(driver, username, userPassword);
        try {
            List<WebElement> list=driver.findElements(By.className("multiline"));
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + "--> " + list.get(i).getText());
            }

        } catch (Exception ex) {
             ex.printStackTrace();

        }

    }

    public static WebDriver initAndReturnDriver() {
        System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER_LOCATION);
        WebDriver driver = new ChromeDriver();
        driver.get(Constants.COLLEGE_WEBSITE_LINK);
        driver.manage().window().maximize();
        return driver;
    }

    public static void signInToMoodle(WebDriver driver, String username, String password) {
        try {
            WebElement personalInformationElement = driver.findElement(By.cssSelector(Constants.PERSONAL_INFORMATION_HREF_LINK));
            personalInformationElement.click();
            WebElement usernameElement = driver.findElement(By.id(Constants.USERNAME_ELEMENT_ID));
            usernameElement.sendKeys(username);
            WebElement passwordElement = driver.findElement(By.id(Constants.PASSWORD_ELEMENT_ID));
            passwordElement.sendKeys(password);
            WebElement enterButtonElement = driver.findElement(By.id(Constants.ENTER_BUTTON_ELEMENT_ID));
            enterButtonElement.click();
            WebElement moodleSystemElement = driver.findElement(By.cssSelector(Constants.MOODLE_BUTTON_ELEMENT_HREF_LINK));
            moodleSystemElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void logOutFromMoodle(WebDriver driver) {
        try {
            WebElement menuForUserButtonElement = driver.findElement(By.id(Constants.MENU_OPTIONS_BUTTON_ID));
            menuForUserButtonElement.click();
            List<WebElement> listOfATagNames = driver.findElements(By.tagName("a"));
            for (WebElement aElement : listOfATagNames) {
                if (aElement.getAttribute("href").contains("logout")) {
                    aElement.click();
                }
            }
            WebElement exitButtonElement = driver.findElement(By.cssSelector(Constants.LOG_OUT_ELEMENT_HREF_LINK));
            exitButtonElement.click();
        } catch (Exception ex) {
           WebElement exitButtonElement = driver.findElement(By.cssSelector(Constants.LOG_OUT_ELEMENT_HREF_LINK));
            exitButtonElement.click();
        }

    }

    public static void returnCardTextClassElement(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.className("container-fluid"));
        List<WebElement> divs = elements.get(Constants.CONTAINER_FLUID_P0_CLASS_ELEMENT).findElements(By.tagName("div"));
       WebElement element=elements.get(Constants.CONTAINER_FLUID_P0_CLASS_ELEMENT).findElement(By.className("card-text content mt-3"));
        System.out.println(element.getAttribute("class"));
    }

    public static void workFunction(WebDriver driver) {
       StringBuilder stringBuilder=new StringBuilder();
        List<WebElement> elements = driver.findElements(By.className("container-fluid"));
        List<WebElement> divs = elements.get(Constants.CONTAINER_FLUID_P0_CLASS_ELEMENT).findElements(By.tagName("div"));
        for (WebElement div : divs) {
            String currentClassName = div.getAttribute("class");
            if (currentClassName.equals("card-text content mt-3")) {
                for (WebElement element : div.findElements(By.tagName("div"))) {
                    List<WebElement> list1 = element.findElements(By.tagName("div"));
                    for (int j = 0; j < list1.size();j++) {
                        String roleAttribute = list1.get(j).getAttribute("role");
                        if (roleAttribute != null) {
                           if (roleAttribute.equals("listitem")) {
                                 WebElement currentWebElement = list1.get(j).findElements(By.className("d-flex")).get(0);
                                 stringBuilder.append(currentWebElement.findElements(By.tagName("div")).get(4).getText());


                    }





                        }


                    }
                }


            }

        }
        System.out.println("the text is " +stringBuilder);

    }

    public static void potentailFunction(WebDriver driver) {
        WebElement containerFluid = driver.findElement(By.className("container-fluid"));
        List<WebElement> divs = containerFluid.findElements(By.tagName("div"));
        WebElement regionMainId = divs.get(9).findElement(By.id("region-main-box"));
        List<WebElement> sections = regionMainId.findElements(By.tagName("section"));
        List<WebElement> hasBlockMb3Class = sections.get(Constants.HAS_BLOCKS_MB3_CLASS_).findElements(By.tagName("div"));
        WebElement cardText = hasBlockMb3Class.get(Constants.CARD_TEXT_CONTENT_CLASS);
        System.out.println(cardText.getText());
        }

}

