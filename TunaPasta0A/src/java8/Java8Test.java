package java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8Test {
    public static void main(String[] args) {
        //我们要在集合中查找包含“Java”标签的第一篇文章
        List<String> tags1 = new ArrayList();
        tags1.add("C++");
        tags1.add("C#");
        tags1.add("PHP");

        List<String> tags2 = new ArrayList();
        tags2.add("python");
        tags2.add("Java");

        List<Article> articles = new ArrayList();
        //No enclosing instance of type Java8Test is accessible. Must qualify the allocation with an enclosing instance of type Java8Test
        //(e.g. x.new A() where x is an instance of Java8Test).

        //内部类是动态的，也就是开头以public class开头。而主程序是public static class main。
        //在Java中，类中的静态方法不能直接调用动态方法。只有将某个内部类修饰为静态类，
        //然后才能够在静态类中调用该类的成员变量与成员方法。所以在不做其他变动的情况下，
        //最简单的解决办法是将public class改为public static class.

        articles.add(new Article("title1", "author1", tags1));
        articles.add(new Article("title2", "author2", tags2));

        //查找包含“Java”标签的第一篇文章。
        System.out.println("getFirstJavaArticleUseCycle==>" + getFirstJavaArticleUseCycle(articles));
        System.out.println("getFirstJavaArticleUseStream==>" + getFirstJavaArticleUseStream(articles).get());
//		System.out.println("getFirstJavaArticleUseStream==>"+getFirstJavaArticleUseStream(articles).orElse(null));

        //根据作者来把所有的文章分组
        System.out.println("groupByAuthorUseCycle==>" + groupByAuthorUseCycle(articles));
        System.out.println("groupByAuthorUseStream==>" + groupByAuthorUseStream(articles));

        //查找集合中所有不同的标签
        System.out.println("getDistinctTagsUseCycle==>" + getDistinctTagsUseCycle(articles));
        System.out.println("getDistinctTags==>" + getDistinctTags(articles));

    }

    //
    public static Article getFirstJavaArticleUseCycle(List<Article> articles) {
        for (Article article : articles) {
            if (article.getTags().contains("Java")) {
                return article;
            }
        }
        return null;
    }

    //
    public static Optional<Article> getFirstJavaArticleUseStream(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("Java"))
                .findFirst();
    }

    //
    public static Map<String, List<Article>> groupByAuthorUseCycle(List<Article> articles) {
        Map<String, List<Article>> result = new HashMap();
        for (Article article : articles) {
            if (result.containsKey(article.getAuthor())) {
                result.get(article.getAuthor()).add(article);
            } else {
                ArrayList<Article> articleAdd = new ArrayList<>();
                articleAdd.add(article);
                result.put(article.getAuthor(), articleAdd);
            }
        }
        return result;
    }

    //
    public static Map<String, List<Article>> groupByAuthorUseStream(List<Article> articles) {
        return articles.stream()
                .collect(Collectors.groupingBy(Article::getAuthor));
    }

    //
    public static Set<String> getDistinctTagsUseCycle(List<Article> articles) {
        Set<String> result = new HashSet<>();
        for (Article article : articles) {
            result.addAll(article.getTags());
        }
        return result;
    }

    //
    public static Set<String> getDistinctTags(List<Article> articles) {
        return articles.stream().flatMap(article -> article.getTags().stream()).collect(Collectors.toSet());
    }
}

//
class Article {
    private final String title;
    private final String author;
    private final List<String> tags;

    public Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "title=" + title + "author=" + author;
    }
}