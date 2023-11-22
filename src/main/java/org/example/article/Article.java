package org.example.article;

import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Article {

    private Long reference;

    private String name;

    private BigInteger price;

    private Integer quantity;


    public Boolean isArticleBetweenPrices(BigInteger firstPrice, BigInteger secondPrice){
        if (((this.getPrice().compareTo(firstPrice) > 0) && (secondPrice.compareTo(this.getPrice()) > 0))){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Article{" +
                "reference=" + reference +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public void display(){
        System.out.println("Article found : " + this.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(reference, article.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
