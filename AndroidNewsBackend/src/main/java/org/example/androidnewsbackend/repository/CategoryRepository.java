package org.example.androidnewsbackend.repository;

import org.example.androidnewsbackend.model.NewsKategorija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<NewsKategorija, Integer> {
    Optional<NewsKategorija> findById(Integer id);
    Optional<NewsKategorija> findByNaziv(String naziv);
}
