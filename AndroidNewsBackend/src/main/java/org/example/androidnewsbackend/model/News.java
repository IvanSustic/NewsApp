package org.example.androidnewsbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "naslov")
    private String naslov;
    @Column(name = "tekst")
    private String tekst;
    @Column(name = "slika")
    private String slika;
    @Column(name = "datum")
    private Date datum;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private NewsKategorija kategorija;

}
