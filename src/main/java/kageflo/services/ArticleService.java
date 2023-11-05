package kageflo.services;

import kageflo.entities.Article;
import kageflo.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    void addArticle(Article article);

    List<Article> getAll();

    Optional<Article> getById(int id);
}
