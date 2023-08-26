package kageflo.services;

import kageflo.entities.Article;
import kageflo.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Article> result = articleRepository.findAll();
        return result;
    }
}
