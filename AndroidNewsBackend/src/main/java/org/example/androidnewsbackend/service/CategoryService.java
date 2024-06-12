package org.example.androidnewsbackend.service;

import org.example.androidnewsbackend.dto.KategorijaDTO;

import java.util.List;

public interface CategoryService {
    List<KategorijaDTO> findAll();
}
