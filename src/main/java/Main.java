import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Please enter your username and your password you use in order to enter the college website");
        String username = scanner.nextLine();
        String userPassword = scanner.nextLine();
        WebDriver driver = initAndReturnDriver();
        signInToMoodle(driver, username, userPassword);
        WebElement chosenCourse= printCoursesListAndReturnChosenCourse(driver);
        chosenCourse.click();
        logOutFromMoodle(driver);
        driver.close();
   }catch (Exception e){
           // e.printStackTrace();
        }


    }
    public static WebDriver initAndReturnDriver() {
        System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER_LOCATION);
        WebDriver driver = new ChromeDriver();
        driver.get(Constants.COLLEGE_WEBSITE_LINK);
        driver.manage().window().maximize();
        return driver;
    }

    public static void signInToMoodle (WebDriver driver, String username, String password) {
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
        public static WebElement printCoursesListAndReturnChosenCourse(WebDriver driver){
        Scanner scanner =new Scanner(System.in);
        int userChoice=0;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> coursesList = driver.findElements(By.className("multiline"));
            for (int i = 0; i < coursesList.size(); i++) {
                System.out.println(i + "--> " + coursesList.get(i).getText());
            }
            try {
                System.out.println("please choose course from the list you like to enter to (enter only the number of the course!)");
                userChoice=scanner.nextInt();

            }catch (InputMismatchException e){
                e.printStackTrace();
            }


            return coursesList.get(userChoice);

        }

}

