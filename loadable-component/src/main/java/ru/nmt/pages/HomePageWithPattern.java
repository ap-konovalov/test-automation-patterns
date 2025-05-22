package ru.nmt.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

public class HomePageWithPattern extends LoadableComponent<HomePageWithPattern> {

    @FindBy(css = "[data-test-id='main-title']")
    private SelenideElement pageTitle;

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() {
        pageTitle.shouldBe(Condition.visible);
    }
}
