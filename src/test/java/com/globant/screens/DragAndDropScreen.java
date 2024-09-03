package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DragAndDropScreen extends BaseScreen {
    private static final String TITLE = "UiSelector().text(\"Drag and Drop\")";
    private static final String DRAG_AND_DROP_TARGET = "//android.view.ViewGroup[@content-desc=\"Drag-drop-screen\"]/android.widget.ImageView";
    private static final String RENEW_BTN = "renew";
    private static final String DRAG_OPTION_LIST = "//android.view.ViewGroup[contains(@content-desc, \"drag\")]";

    @AndroidFindBy(uiAutomator = DragAndDropScreen.TITLE)
    private WebElement titleTxt;
    @AndroidFindBy(xpath = DragAndDropScreen.DRAG_AND_DROP_TARGET)
    private WebElement dragAndDropTarget;
    @AndroidFindBy(accessibility = DragAndDropScreen.RENEW_BTN)
    private WebElement renewBtn;
    @AndroidFindBy(xpath = DragAndDropScreen.DRAG_OPTION_LIST)
    private List<WebElement> dragOptionList;

    public boolean isTitleDisplayed() {
        return this.titleTxt.isDisplayed();
    }

    public boolean isDropTargetDisplayed() {
        return this.dragAndDropTarget.isDisplayed();
    }

    public boolean isRenewBtnDisplayed() {
        return this.renewBtn.isDisplayed();
    }

    public boolean dragOptionsAreDisplayed() {
        return this.dragOptionList.stream().allMatch(WebElement::isDisplayed);
    }

    public DragAndDropScreen(AndroidDriver driver) {
        super(driver);
    }
}
