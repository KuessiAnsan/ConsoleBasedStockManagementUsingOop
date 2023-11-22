package org.example;

import org.example.article.Article;
import org.example.boutique.Shop;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(
            Set.of(
                    new Article(1L, "Book", BigInteger.valueOf(12000), 12),
                    new Article(2L, "Pen", BigInteger.valueOf(15000), 15),
                    new Article(3L, "Coke", BigInteger.valueOf(2000), 21),
                    new Article(4L, "Paper", BigInteger.valueOf(8000), 40),
                    new Article(5L, "Food", BigInteger.valueOf(1000), 1),
                    new Article(6L, "Water", BigInteger.valueOf(12500), 17),
                    new Article(7L, "Ginger", BigInteger.valueOf(1250), 540)
                )
        );
        System.out.println("Welcome to STOCKY - The awesome console based Stock Management Program");
        boolean again = false;
        do{
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("What do you want to do ?");
                System.out.println("""
                    1- Search an article
                    2- Add an article
                    3- Delete an article
                    4- Modify an article
                    5- Display all the articles
                    """);
                int option = 0;
                int searchCriteria = 0;
                option = scanner.nextInt();
                Scanner sc = new Scanner(System.in);
                switch (option) {
                    case 1 -> {
                        System.out.println("""
                            1- By name
                            2- By reference
                            3- By price interval
                            """);
                        searchCriteria = scanner.nextInt();
                        switch (searchCriteria) {
                            case 1 -> {
                                System.out.println("Enter the name of the article:");
                                String name = sc.nextLine();
                                Article article = shop.searchArticleByName(name);
                                if(article != null){
                                    article.display();
                                }else{
                                    System.out.println("Article requested could'nt be found");
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter the reference of the article:");
                                Long reference = sc.nextLong();
                                Article article = shop.searchArticleByReference(reference);
                                if(article != null){
                                    article.display();
                                }else{
                                    System.out.println("Article requested could'nt be found");
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter the first price:");
                                BigInteger firstPrice = sc.nextBigInteger();
                                System.out.println("Enter the second price:");
                                BigInteger secondPrice = sc.nextBigInteger();
                                List<Article> foundArticles = shop.searchArticlesByPriceInterval(firstPrice, secondPrice);
                                if(foundArticles.isEmpty()){
                                    System.out.println("No articles found in the provided interval");
                                }else{
                                    System.out.println("List of articles found : " );
                                    for (Article article:
                                            foundArticles) {
                                        System.out.println(article);
                                    }
                                }
                            }
                            default -> System.out.println("Enter something !!!");
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter the reference of the article");
                        Long reference = sc.nextLong();
                        System.out.println("Enter the name of the article");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        System.out.println("Enter the price of the article");
                        BigInteger price = sc.nextBigInteger();
                        System.out.println("Enter the quantity of the article");
                        Integer quantity = sc.nextInt();
                        if (shop.addNewArticle(new Article(reference, name, price, quantity))){
                            System.out.println("Article added successfully");
                        }else {
                            System.out.println("Article couldn't be added");
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter the reference of the article:");
                        Long reference = sc.nextLong();
                        if (shop.removeArticleByReference(reference)){
                            System.out.println("Article removed successfully");
                        }else {
                            System.out.println("Article couldn't be removed");
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter the reference of the article:");
                        Long reference = sc.nextLong();
                        Article article = shop.searchArticleByReference(reference);
                        if(article == null){
                            System.out.println("Article not found");
                        }else {
                            System.out.println("Enter the name of the article");
                            Scanner scanner1 = new Scanner(System.in);
                            String name = scanner1.nextLine();
                            System.out.println("Enter the price of the article");
                            BigInteger price = sc.nextBigInteger();
                            System.out.println("Enter the quantity of the article");
                            Integer quantity = sc.nextInt();
                            if(shop.modifyArticleByReference(reference, new Article(null, name, price, quantity))){
                                System.out.println("Article modified successfully");
                            }else {
                                System.out.println("Error while modifying");
                            }
                        }
                    }
                    case 5 -> shop.displayAllArticles();
                    default -> System.out.println("Enter something !!");
                }
            } catch(InputMismatchException e){
                System.out.println("You have to enter correct data");
            }  finally {
                System.out.println();
                System.out.println("Do you want to continue ?");
                try {
                    again = scanner.nextBoolean();
                }catch(InputMismatchException e){
                    System.out.println("You have to enter correct data");
                    again = true;
                }
            }
        }while (again);
    }
}