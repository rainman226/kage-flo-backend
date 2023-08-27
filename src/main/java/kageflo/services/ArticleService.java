package kageflo.services;

import kageflo.entities.Article;
import kageflo.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addArticle(Article article){
        articleRepository.save(article);
    }

    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    public Optional<Article> getById(int id){
        return articleRepository.findById(id);
    }
}
