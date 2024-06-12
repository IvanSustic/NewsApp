package org.example.androidnewsbackend.repository;

import org.example.androidnewsbackend.model.News;
import org.example.androidnewsbackend.model.NewsKategorija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByKategorijaNaziv(String kategorija);
    List<News> findByDatum(Date datum);
    Optional<News> findByNaslov(String naslov);
}
