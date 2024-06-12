package org.example.androidnewsbackend.service;

import lombok.AllArgsConstructor;
import org.example.androidnewsbackend.dto.KategorijaDTO;
import org.example.androidnewsbackend.dto.NewsDTO;
import org.example.androidnewsbackend.model.News;
import org.example.androidnewsbackend.repository.CategoryRepository;
import org.example.androidnewsbackend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements  NewsService{
    private NewsRepository newsRepository;
    private CategoryRepository categoryRepository;
    @Override
    public List<NewsDTO> dohvatiSveVijesti() {
        return newsRepository.findAll().stream().map(this::convertNewsToNewsDTO).toList();
    }

    @Override
    public List<NewsDTO> dohvatiVijestiPoKategoriji(KategorijaDTO kategorijaDTO) {
        return newsRepository.findByKategorijaNaziv(kategorijaDTO.getNaziv()).stream().map(this::convertNewsToNewsDTO).toList();
    }

    @Override
    public NewsDTO dohvatiVijest(String naslov) {
        return convertNewsToNewsDTO(newsRepository.findByNaslov(naslov).get());

    }

    @Override
    public NewsDTO spremiVijest(NewsDTO newsDTO, String kategorija) {
        return convertNewsToNewsDTO(newsRepository.save(new News(1,newsDTO.getNaslov(),newsDTO.getTekst(),newsDTO.getSlika(),newsDTO.getDatum(),
                categoryRepository.findByNaziv(kategorija).get())));
    }

    @Override
    public List<NewsDTO> dohvatiSveVijestiPoDatumu(Date date) {
        return newsRepository.findByDatum(date).stream().map(this::convertNewsToNewsDTO).toList();

    }

    @Override
    public NewsDTO changeNews(NewsDTO newsDTO, String naslov, String kategorija) {
        Optional<News> review1 = newsRepository.findByNaslov(naslov);

       return convertNewsToNewsDTO(newsRepository.save(new News(review1.get().getId(),newsDTO.getNaslov(),newsDTO.getTekst(),newsDTO.getSlika(),newsDTO.getDatum(),
                categoryRepository.findByNaziv(kategorija).get())));
    }
    private NewsDTO convertNewsToNewsDTO(News news){
        return  new NewsDTO(news.getNaslov(),news.getTekst(),news.getSlika(),news.getDatum());
    }
}
