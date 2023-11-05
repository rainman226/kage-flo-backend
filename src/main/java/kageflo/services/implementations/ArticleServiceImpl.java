package kageflo.services.implementations;

import kageflo.entities.Article;
import kageflo.repositories.ArticleRepository;
import kageflo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void addArticle(Article article){
        articleRepository.save(article);
    }

    @Override
    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getById(int id){
        return articleRepository.findById(id);
    }
}
