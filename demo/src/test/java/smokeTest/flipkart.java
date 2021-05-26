package smokeTest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class flipkart {
	private WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		//to avoid illegal state exception we need to set path of driver executables
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 driver=new ChromeDriver();//to launch/open browser
		 driver.manage().window().maximize();//to maximize browser window
		 driver.get("https://www.flipkart.com/");//to launch web application
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//for synchronization purpose
	}
	
	@Test
	public void login() throws InterruptedException
	{
//2.close login popup       
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
//3.search mobile in search box
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Mobile");
	//2.1 click on search button
		driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
//4.mouseover on electronics
		WebElement target=driver.findElement(By.xpath("//span[.='Electronics']"));
		//4.1 to perform mouseover action we need to create object for Actions class
		Actions a=new Actions(driver);
		a.moveToElement(target).perform();
		Thread.sleep(5000);

//5.select any brand from mobile brand list
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div/div[1]/a[4]")).click();
		
//6.select any mobile
		driver.findElement(By.xpath("//img[@alt='SAMSUNG Galaxy M12 (Blue, 128 GB)']")).click();
		Thread.sleep(5000);
      //6.1 to move control to new tab we have to use getWindowHandles() of webdriver interface
		
		String parenthandle=driver.getWindowHandle();//address of parent window
		Set<String> Handles=driver.getWindowHandles();//address of all window
		
		//6.2advanced for loop to read address of all windows one by one
		for(String s:Handles)
		{
			if(!s.equals(parenthandle))//to remove parent handle
				driver.switchTo().window(s);
				
		}
		
		
		
		
//7.select any different color from color filter
		driver.findElement(By.xpath("(//div[@class='_2C41yO'])[1]")).click();
		
//8.select any different storage from the storage filter
		driver.findElement(By.xpath("//a[.='64 GB']")).click();
		Thread.sleep(5000);
		
//9.click on "Add to cart" button
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
		
//10.print total amount
		String Totalamount=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[4]/div/span/div/div/span")).getText();
		System.out.println(Totalamount);
		
//11.click on 'Remove'Button
		driver.findElement(By.xpath("(//div[@class='_3dsJAO'])[1]")).click();
		
		
	}
	
	@AfterMethod
	public void cleanup()
	{
		driver.quit();
		
	}
	
	
}
