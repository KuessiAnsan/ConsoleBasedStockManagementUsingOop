package org.example.boutique;

import junit.framework.TestCase;
import org.example.article.Article;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ShopTest{

    @Test
    public void addNewArticle_shouldAddNewArticle() {
        Article articleToAdd = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Shop shop = new Shop(new HashSet<>());

        assertTrue(shop.addNewArticle(articleToAdd));
        assertTrue(shop.containsArticleAlready(articleToAdd));
    }

    @Test
    public void addNewArticle_shouldNotAddDuplicateArticle() {
        Article article1 = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Article article2 = Article.builder()
                .reference(1L)
                .name("Another Article")
                .price(new BigInteger("60"))
                .quantity(8)
                .build();

        Shop shop = new Shop(new HashSet<>());

        assertTrue(shop.addNewArticle(article1));
        assertFalse(shop.addNewArticle(article2));
    }

    @Test
    public void searchArticleByReference_shouldReturnCorrectArticle() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Set<Article> articles = new HashSet<>();
        articles.add(article);

        Shop shop = new Shop(articles);

        assertEquals(article, shop.searchArticleByReference(1L));
    }

    @Test
    public void removeArticleByReference_shouldRemoveArticle() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Set<Article> articles = new HashSet<>();
        articles.add(article);

        Shop shop = new Shop(articles);

        assertTrue(shop.removeArticleByReference(1L));
        assertFalse(shop.containsArticleAlready(article));
    }

    @Test
    public void modifyArticleByReference_shouldModifyArticle() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Set<Article> articles = new HashSet<>();
        articles.add(article);

        Shop shop = new Shop(articles);

        Article newArticle = Article.builder()
                .reference(1L)
                .name("Modified Article")
                .price(new BigInteger("60"))
                .quantity(5)
                .build();

        assertTrue(shop.modifyArticleByReference(1L, newArticle));
        assertEquals(newArticle, shop.searchArticleByReference(1L));
    }

    @Test
    public void searchArticleByName_shouldReturnCorrectArticle() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Set<Article> articles = new HashSet<>();
        articles.add(article);

        Shop shop = new Shop(articles);

        assertEquals(article, shop.searchArticleByName("Test Article"));
    }

    @Test
    public void searchArticlesByPriceInterval_shouldReturnArticlesInInterval() {
        Article article1 = Article.builder()
                .reference(1L)
                .name("Article 1")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Article article2 = Article.builder()
                .reference(2L)
                .name("Article 2")
                .price(new BigInteger("60"))
                .quantity(8)
                .build();

        Article article3 = Article.builder()
                .reference(3L)
                .name("Article 3")
                .price(new BigInteger("70"))
                .quantity(12)
                .build();

        Set<Article> articles = new HashSet<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        Shop shop = new Shop(articles);

        List<Article> result = shop.searchArticlesByPriceInterval(new BigInteger("55"), new BigInteger("65"));
        assertEquals(1, result.size());
        assertTrue(result.contains(article2));
    }
}