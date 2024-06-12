package org.example.androidnewsbackend.service;

import lombok.AllArgsConstructor;
import org.example.androidnewsbackend.dto.KategorijaDTO;
import org.example.androidnewsbackend.dto.NewsDTO;
import org.example.androidnewsbackend.model.News;
import org.example.androidnewsbackend.model.NewsKategorija;
import org.example.androidnewsbackend.repository.CategoryRepository;
import org.example.androidnewsbackend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class KategorijaImpl implements  CategoryService{
    private CategoryRepository categoryRepository;
    @Override
    public List<KategorijaDTO> findAll() {
        return categoryRepository.findAll().stream().map(this::convertKategorijaToKategorijaDTO).toList();
    }

    private KategorijaDTO convertKategorijaToKategorijaDTO(NewsKategorija newsKategorija){
        return  new KategorijaDTO(newsKategorija.getNaziv());
    }
}
