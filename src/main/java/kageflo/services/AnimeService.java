package kageflo.services;

import kageflo.entities.Anime;
import kageflo.entities.User;
import kageflo.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnime(boolean sorted){
        List<Anime> animes;
        if(sorted) animes = animeRepository.findAllByOrderByTitleAsc();
        else animes = animeRepository.findAll();
        return animes;
    }

    public List<Anime> getAnime(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Anime> animePage = animeRepository.findAll(pageable);
        return animePage.getContent();
    }

    public List<Anime> searchAnimeByTitleIgnoreCase(String keyword){
        String lowercaseKeyword = keyword.toLowerCase();
        List<Anime> animeList = animeRepository.searchAnimeByTitleIgnoreCase(lowercaseKeyword);
        return animeList;
    }

    public List<Anime> getAnimeByFields(Integer id,
                                        String title,
                                        String startDate,
                                        String type,
                                        Integer episodes,
                                        String status,
                                        String source,
                                        String studio){
        Specification<Anime> spec = Specification.where(null);

        if(id != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id));
        }

        if(title != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title));
        }

        if(startDate != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("startDate"), startDate));
        }

        if(type != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
        }

        if(episodes != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("episodes"), episodes));
        }

        if(status != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
        }

        if(source != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("source"), source));
        }

        if(studio != null){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("studio"), studio));
        }

        List<Anime> animeList = animeRepository.findAll(spec);

        if (animeList.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return animeRepository.findAll(spec);
    }

    public void addAnime(Anime anime) {
        animeRepository.save(anime);
    }
}
