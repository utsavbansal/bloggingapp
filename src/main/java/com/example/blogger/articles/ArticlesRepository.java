package com.example.blogger.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticlesRepository extends JpaRepository<ArticleEntity,Integer> {
    Optional<ArticleEntity> findBySlug(String slug);

    // create a custome query
    //@Query("select a from articles a where a.heading like %?1")
    //List<ArticleEntity> findByHeading(String heading);

    List<ArticleEntity> findByHeadingContainsIgnoreCase(String heading);

}
