package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Ex5
    @Test
    public void testSaveTwoArticleAndDeleteOne(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String title_article_one = "British journalist";
        String title_article_two = "British archaeologist";
        String name_of_folder = "British famous people";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Dilys Powell");
        SearchPageObject.clickByArticleWithSubstring(title_article_one);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Humfry Payone");
        SearchPageObject.clickByArticleWithSubstring(title_article_two);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyListToFolderByName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(title_article_one);

        MyListsPageObject.waitForArticleAppearByTitle(title_article_two);
        String title_expected = MyListsPageObject.getArticleTitleMyList();
        MyListsPageObject.clickArticleByTitle(title_article_two);

        String title_result = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title " + title_result + "cannot expected",
                title_expected,
                title_result
        );
    }
}
