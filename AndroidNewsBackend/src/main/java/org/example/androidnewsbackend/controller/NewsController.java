package org.example.androidnewsbackend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.androidnewsbackend.dto.KategorijaDTO;
import org.example.androidnewsbackend.dto.NewsDTO;
import org.example.androidnewsbackend.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
@Slf4j
public class NewsController {
    private NewsService newsService;

    @GetMapping("/all")
    public List<NewsDTO> getAllNews() {

        log.info("Get all news called");

        return newsService.dohvatiSveVijesti();
    }


    @GetMapping("/date")
    public List<NewsDTO> getAllVozila() {

        log.info("Get all news called");

        return newsService.dohvatiSveVijesti();
    }

    @GetMapping("/kategorija/{kategorija}")
    public List<NewsDTO> getNewsKategorija(@PathVariable String kategorija) {

        log.info("Get kategorija news called");

        return newsService.dohvatiVijestiPoKategoriji(new KategorijaDTO(kategorija));
    }

    @GetMapping("/{naslov}")
    public NewsDTO getNews(@PathVariable String naslov) {

        log.info("Get kategorija news called");

        return newsService.dohvatiVijest(naslov);
    }

    @PostMapping("/add/{kategorija}")
    public NewsDTO saveVozilo(@RequestBody NewsDTO newsDTO,@PathVariable String kategorija){

        NewsDTO voziloDTO = newsService.spremiVijest(newsDTO,kategorija);

        return voziloDTO;
    }

    @PutMapping("/change/{naslov}/{kategorije}")
    public NewsDTO changeVozilo(@RequestBody NewsDTO review,@PathVariable String naslov,@PathVariable String kategorije){

        NewsDTO newsDTO = newsService.changeNews(review,naslov,kategorije);

        return newsDTO;
    }

}
