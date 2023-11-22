package org.example.boutique;

import lombok.*;
import org.example.article.Article;
import java.math.BigInteger;
import java.util.*;



@NoArgsConstructor
@Getter
@Setter
public class Shop {


    private Set<Article> articles;

    public Shop(Set<Article> articles) {
        this.articles = articles;
    }


    //Method to add a new article in our collection (We use Set because of Uniqueness and equals and hashcode usage
    public Boolean addNewArticle(Article articleToAdd){
        boolean isArticleAdded = false;
        if (!containsArticleAlready(articleToAdd)){
            List<Article>mutableList = new ArrayList<>(articles);
            isArticleAdded = mutableList.add(articleToAdd);
            articles = Set.copyOf(mutableList);
        }
        return isArticleAdded;
    }


    //Method that check if our collection already have the particular article with the id or not
    public Boolean containsArticleAlready(Article articleToAdd){
        return this.articles.contains(articleToAdd);
    }


    //Method to search article by a provided reference
    public Article searchArticleByReference(Long referenceToSearch){
        return articles.stream().filter(article -> article.getReference().equals(referenceToSearch)).toList().get(0);
    }


    //Remove a specific article based on a provided reference
    public boolean removeArticleByReference(Long referenceToSearch){
        List<Article> mutableArticles = new ArrayList<>(articles);
        boolean removed = mutableArticles.removeIf(article -> article.getReference().equals(referenceToSearch));
        articles = Set.copyOf(mutableArticles);
        return removed;
    }


    //Modify a specific article based on a provided reference
    public Boolean modifyArticleByReference(Long referenceToSearch, Article newArticle){
        Article articleToBeModified = searchArticleByReference(referenceToSearch); //We check if the article is present;
        boolean isArticleModified = false;
        if(articleToBeModified != null){
            Article modifiedArticle = Article.builder()
                    .reference(articleToBeModified.getReference())
                    .name(newArticle.getName())
                    .price(newArticle.getPrice())
                    .quantity(newArticle.getQuantity())
                    .build();
            if (removeArticleByReference(articleToBeModified.getReference())){
                if(addNewArticle(modifiedArticle)){
                    isArticleModified = true;
                }
            };
        }
        return isArticleModified;
    }

    //Search and Get Article based on a provided reference
    public Article searchArticleByName(String name){
        return articles.stream().filter(article -> article.getName().equals(name)).toList().get(0);
    }

    //Search and Get Articles which prices are in the interval
    public List<Article> searchArticlesByPriceInterval(BigInteger firstPrice, BigInteger secondPrice){
        return articles.stream().filter(article -> article.isArticleBetweenPrices(firstPrice, secondPrice)).toList();
    }

    //Display all articles
    public void displayAllArticles(){
        System.out.println("List of articles found : " );
        for (Article article:
                articles) {
            article.display();
        }
    }
}
