package org.example.androidnewsbackend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.androidnewsbackend.model.NewsKategorija;
import org.example.androidnewsbackend.model.UserInfo;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDTO {

    private String naslov;
    private String tekst;
    private String slika;
    private Date datum;


}
