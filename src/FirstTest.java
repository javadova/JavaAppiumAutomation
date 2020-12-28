import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    @Test
    public void testFindText(){
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cannot find expected text"
        );
    }

    @Test
    public void testCheckCancelSearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        assertTrue("Find results less than 2", MainPageObject.getCountElement(
                By.id("org.wikipedia:id/page_list_item_container"),
                "elements not found",
                5) >= 2);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present of the page",
                5
        );

    }

    @Test
    public void testSaveTwoArticleAndDeleteOne(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String name_of_search_topic = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                name_of_search_topic,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open more option",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it!' tip overlay",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input of name articles folder",
                5
        );
        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button X to close",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                name_of_search_topic,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find 'Programming language' topic",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open more option",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button X to close",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe and find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='programming language']"),
                "Article 'Programming language' has also been deleted",
                5
        );

        String title_expected = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "Cannot find article title JavaScript",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='programming language']"),
                "Article 'Programming language' has also been deleted",
                5
        );

        String title_result = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title JavaScript",
                15
        );
        assertEquals(
                "Article title " + title_result + "cannot expected 'JavaScript'",
                title_expected,
                title_result
        );
    }

    @Test
    public void testAssertArticleTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic",
                5
        );
        String title_locator = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";

        MainPageObject.assertElementPresent(
                By.xpath(title_locator),
                "We not found title " + search_line
        );
    }
}
