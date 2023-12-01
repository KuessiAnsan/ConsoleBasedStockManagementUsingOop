package org.example.article;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;


public class ArticleTest {
    @Test
    public void isArticleBetweenPrices_shouldReturnTrueWhenPriceInRange() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        assertTrue(article.isArticleBetweenPrices(new BigInteger("40"), new BigInteger("60")));
    }


    @Test
    public void isArticleBetweenPrices_shouldReturnFalseWhenPriceBelowRange() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("30"))
                .quantity(5)
                .build();

        assertFalse(article.isArticleBetweenPrices(new BigInteger("40"), new BigInteger("60")));
    }

    @Test
    public void isArticleBetweenPrices_shouldReturnFalseWhenPriceAboveRange() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("70"))
                .quantity(3)
                .build();

        assertFalse(article.isArticleBetweenPrices(new BigInteger("40"), new BigInteger("60")));
    }

    @Test
    public void toString_shouldReturnFormattedString() {
        Article article = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        String expectedString = "Article{reference=1, name='Test Article', price=50, quantity=10}";
        assertEquals(expectedString, article.toString());
    }

    @Test
    public void equals_shouldReturnTrueForSameReference() {
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

        assertTrue(article1.equals(article2));
    }

    @Test
    public void equals_shouldReturnFalseForDifferentReferences() {
        Article article1 = Article.builder()
                .reference(1L)
                .name("Test Article")
                .price(new BigInteger("50"))
                .quantity(10)
                .build();

        Article article2 = Article.builder()
                .reference(2L)
                .name("Another Article")
                .price(new BigInteger("60"))
                .quantity(8)
                .build();

        assertFalse(article1.equals(article2));
    }
}