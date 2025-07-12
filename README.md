# Mercedes A-Class Search Automation (Selenium + Bing)

Automated script using Selenium WebDriver to:

- Search for "Mercedes A Class" on Bing
- Scroll through search results
- Find the first result mentioning "engine" or "horsepower"
- Return:
  - Index of the result (e.g., result #4)
  - Number of paginations needed

## Why Bing?
Google blocks automated requests with CAPTCHA. Bing allows smooth testing of logic without interruption.

## Tech Stack
- Java
- Maven
- Selenium WebDriver
- WebDriverManager

## How to Run
1. Clone the repo
2. Run via IntelliJ or use: `mvn exec:java`
