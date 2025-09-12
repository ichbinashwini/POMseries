package com.qa.opencart.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptutil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptutil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//grey
		for (int i = 0; i < 7; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// grey
		}
	}

	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

}
