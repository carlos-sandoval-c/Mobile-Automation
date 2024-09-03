package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsScreen extends BaseScreen {
    private static final String TITLE_TXT = "UiSelector().textContains(\"Form components\")";
    private static final String FORM_TEXT_INPUT = "UiSelector().resourceId(\"RNE__Input__text-input\")";
    private static final String FORM_INPUT_VIEWER = "input-text-result";
    private static final String SWITCH_FORM_BTN = "switch";
    private static final String SWITCH_STATUS_TXT = "switch-text";
    private static final String DROPDOWN_BTN = "UiSelector().resourceId(\"android_touchable_wrapper\")";
    private static final String DROPDOWN_TEXT_FIELD = "UiSelector().resourceId(\"text_input\")";
    private static final String DROPDOWN_MODAL = "com.wdiodemoapp:id/select_dialog_listview";
    private static final String DROPDOWN_MODAL_OPTION_LIST = "//android.widget.ListView[@resource-id=\"com.wdiodemoapp:id/select_dialog_listview\"]/*";
    private static final String ACTIVE_BTN = "button-Active";
    private static final String INACTIVE_BTN = "button-Inactive";
    private static final String ACTIVE_BTN_MODAL_OK_BTN = "//android.widget.Button[@resource-id=\"android:id/button1\"][@text=\"OK\"]";

    @AndroidFindBy(uiAutomator = FormsScreen.TITLE_TXT)
    private WebElement title;
    @AndroidFindBy(uiAutomator = FormsScreen.FORM_TEXT_INPUT)
    private WebElement textInput;
    @AndroidFindBy(accessibility = FormsScreen.FORM_INPUT_VIEWER)
    private WebElement inputViewer;
    @AndroidFindBy(accessibility = FormsScreen.SWITCH_FORM_BTN)
    private WebElement switchBtn;
    @AndroidFindBy(accessibility = FormsScreen.SWITCH_STATUS_TXT)
    private WebElement switchStatusTxt;
    @AndroidFindBy(uiAutomator = FormsScreen.DROPDOWN_BTN)
    private WebElement dropdownBtn;
    @AndroidFindBy(uiAutomator = FormsScreen.DROPDOWN_TEXT_FIELD)
    private WebElement dropdownTxt;
    @AndroidFindBy(id = FormsScreen.DROPDOWN_MODAL)
    private WebElement dropdownModal;
    @AndroidFindBy(xpath = FormsScreen.DROPDOWN_MODAL_OPTION_LIST)
    private List<WebElement> dropdownOptionList;
    @AndroidFindBy(accessibility = FormsScreen.ACTIVE_BTN)
    private WebElement activeBtn;
    @AndroidFindBy(accessibility = FormsScreen.INACTIVE_BTN)
    private WebElement inactiveBtn;
    @AndroidFindBy(xpath = FormsScreen.ACTIVE_BTN_MODAL_OK_BTN)
    private WebElement closeModalActiveBtn;

    public FormsScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return this.title.isDisplayed();
    }

    public void setTextInInputField(String txt) {
        if (txt == null || txt.isEmpty())
            return;

        super.waitElementIsDisplayed(this.textInput);
        this.textInput.sendKeys(txt);
    }

    public String getTextFromInputViewer() {
        super.waitElementIsDisplayed(this.inputViewer);
        return this.inputViewer.getText();
    }

    public void tapOnSwitch() {
        super.waitElementIsClickable(this.switchBtn);
        this.switchBtn.click();
    }

    public String getSwitchStatusText() {
        super.waitElementIsDisplayed(this.switchStatusTxt);
        return this.switchStatusTxt.getText();
    }

    public void tapOnDropdown() {
        super.waitElementIsClickable(this.dropdownBtn);
        this.dropdownBtn.click();
    }

    public String getDropdownTxt() {
        super.waitElementIsDisplayed(this.dropdownTxt);
        return this.dropdownTxt.getText();
    }

    public boolean isModalDropdownDisplayed() {
        return this.dropdownModal.isDisplayed();
    }

    public String tapOnDropdownSection(int index) throws ArrayIndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index >= this.dropdownOptionList.size())
            throw new ArrayIndexOutOfBoundsException("FormsScreen - TapOnDropdownSection: Invalid index");

        super.waitElementsAreDisplayed(this.dropdownOptionList);
        WebElement element = this.dropdownOptionList.get(index);
        if (element == null)
            throw new NullPointerException("FormsScreen - TapOnDropdownSection: Invalid element selected");

        String optionTxt = element.getText();
        element.click();
        return optionTxt;
    }

    public boolean isInactiveBtnCheckable() {
        super.waitElementIsDisplayed(this.inactiveBtn);
        return this.inactiveBtn.getAttribute("checkable").equalsIgnoreCase("true");
    }

    public void tapOnActiveBtn() {
        super.waitElementIsDisplayed(this.activeBtn);
        this.activeBtn.click();
    }

    public boolean isActiveModalDisplayed() {
        return this.closeModalActiveBtn.isDisplayed();
    }

    public void tapOnCloseModalActiveBtn() {
        super.waitElementIsClickable(this.closeModalActiveBtn);
        this.closeModalActiveBtn.click();
    }
}
