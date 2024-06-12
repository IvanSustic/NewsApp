package org.example.androidnewsbackend.service;

import org.example.androidnewsbackend.dto.KategorijaDTO;
import org.example.androidnewsbackend.dto.NewsDTO;

import java.util.Date;
import java.util.List;

public interface NewsService {
    List<NewsDTO> dohvatiSveVijesti();
    List<NewsDTO> dohvatiVijestiPoKategoriji(KategorijaDTO kategorijaDTO);
    NewsDTO dohvatiVijest(String naslov);
    NewsDTO spremiVijest(NewsDTO newsDTO, String kategorija);
    List<NewsDTO> dohvatiSveVijestiPoDatumu(Date datum);
   NewsDTO changeNews(NewsDTO newsDTO, String naslov, String kategorija);
}
