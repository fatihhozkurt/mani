package com.lolmanifest.lolmanifestmatcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // 'tags' gibi kullanmadığımız alanları görmezden gel
public class ChampionValue {
    // JSON'da id sayısal olduğu için int veya long kullanabiliriz.
    private long id;
    private String name;
    private String blurb; // Bu alanı 'title' olarak kullanacağız
}