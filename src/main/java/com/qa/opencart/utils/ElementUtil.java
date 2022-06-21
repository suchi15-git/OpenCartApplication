package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private static WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public static WebElement getElement(By string) {
		return driver.findElement(string);
	}

	public static void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public static void doClick(By locator) {
		getElement(locator).click();
	}
	public static By getBy(String locatorType, String locatorValue) {
//		By locator = By.id(locatorValue);
//		return locator;
		By locator = null;

		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		default:
			System.out.println("Please pass correct locator...");
		}

		return locator;
	}

	public boolean doDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void getAttributeValue(By locator, String attributeName) {
		getElement(locator).getAttribute(attributeName);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public void clickOnElementFromSpecificSection(By locator, String value) {
		List<WebElement> footers = getElements(locator);
	}
	
	public  void getAllElementsText(By locator) {
		List<WebElement> eleList= getElements(locator);
		
		for(WebElement e:eleList) {
			String linktext = e.getText();
			System.out.println(linktext);
		}
	}
	
	public int getElementsListCount(By locator) {
		int count = getElements(locator).size();
		return count;
	}
	
	public ArrayList<String> getElementsTextList(By locator) {
		ArrayList<String> eleTextList = new ArrayList<String>();
		List<WebElement> elements = getElements(locator);
		
		for (WebElement e:elements) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	// Dropdown utils//

	public void doSelectDropdownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public int getDropdownElementCount(By locator) {
		return getElements(locator).size();
	}

	public void selectDropdownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String countri = e.getText();
			System.out.println(countri);

			if (countri.contains(value)) {
				e.click();
				break;
			}
		}

	}

	public void printDropdownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsCountry = select.getOptions();

		for (WebElement e : optionsCountry) {
			String countri = e.getText();
			System.out.println(countri);
		}
	}
	
	public List<String> getSelectDropdownOptionsList(By locator){
		List<String> valuesList = new ArrayList<>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsCountry = select.getOptions();

		for (WebElement e : optionsCountry) {
			String countri = e.getText();
			valuesList.add(countri);
		}
		return valuesList;
		
	}
	
	public void selectValueFromDropdown(By locator, String value) {
		List<WebElement> countryoptions = getElements(locator);
		System.out.println(countryoptions.size());
		// Thread.sleep(5000);

		for (int i = 0; i < countryoptions.size(); i++) {
			String textCountry = countryoptions.get(i).getText();
			System.out.println(textCountry);

			if (textCountry.contains(value)) {
				countryoptions.get(i).click();
				break;
			}
		}
	}
	

	public  void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public  void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	public String doGetElementText(By locator) {
		return driver.findElement(locator).getText();
	}
	/**********WAIT UTILS*******************************/
	
	/* An expectation for checking that an element is present on the DOM of the page
	 * This does not necessarily mean that an element is visible
	 * @parameter locator
	 * @parameter timeout
	 * @return
	 */
	public static WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	/* An expectation for checking that an element is visible on the DOM of the page and visible
	 * Visibility means that this element not only displayed
	 * but also has height and width greater than 0
	 * @parameter locator
	 * @parameter timeout
	 * @return
	 */

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public static String waitForTitleContains(String titleFraction,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForTitleIs(String title,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleContains(title))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForTitleURLIs(String URL,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.urlToBe(URL))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	public String waitForURLContains(String URLFraction,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.urlContains(URLFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
}
