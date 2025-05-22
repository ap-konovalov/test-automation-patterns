package ru.nmt.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(css = "[data-test-id='main-title']")
    private SelenideElement pageTitle;

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }
}
