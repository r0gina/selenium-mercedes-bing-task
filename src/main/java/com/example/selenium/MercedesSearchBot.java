package com.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MercedesSearchBot {

    private WebDriver driver;
    private int paginationCount = 0;
    private int resultIndex = -1;

    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://www.bing.com");

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Mercedes A Class");
            searchBox.submit();

            findEngineSpecInResults();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private void findEngineSpecInResults() throws InterruptedException {
        boolean found = false;

        while (!found) {
            Thread.sleep(2000);

            List<WebElement> results = driver.findElements(By.cssSelector("li.b_algo"));

            for (int i = 0; i < results.size(); i++) {
                String text = results.get(i).getText().toLowerCase();

                if (text.contains("engine") || text.contains("horsepower")) {
                    resultIndex = i + 1;
                    System.out.println(" Matching result text: " + results.get(i).getText());
                    found = true;
                    break;
                }
            }

            if (!found) {
                try {
                    WebElement nextButton = driver.findElement(By.cssSelector("a.sb_pagN"));
                    nextButton.click();
                    paginationCount++;
                } catch (Exception e) {
                    System.out.println("last page reached. No matching result found.");
                    return;
                }
            }
        }

        System.out.printf("Found Desired result at index #%d on page #%d (after %d paginations)%n",
                resultIndex, paginationCount + 1, paginationCount);
    }

    public static void main(String[] args) {
        new MercedesSearchBot().start();
    }
}
