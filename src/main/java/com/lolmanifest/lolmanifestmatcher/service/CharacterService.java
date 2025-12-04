package com.lolmanifest.lolmanifestmatcher.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lolmanifest.lolmanifestmatcher.model.LolCharacter;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CharacterService {

    private List<LolCharacter> characterCache = new ArrayList<>();

    @PostConstruct
    public void loadCharactersFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/data/lol_characters.json");

            if (inputStream == null) {
                System.err.println("HATA: /data/lol_characters.json dosyası bulunamadı!");
                return;
            }

            this.characterCache = mapper.readValue(inputStream, new TypeReference<List<LolCharacter>>() {
            });

            System.out.println(this.characterCache.size() + " LoL karakteri başarıyla yüklendi.");

        } catch (Exception e) {
            System.err.println("Karakter verisi yüklenirken kritik bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<LolCharacter> getLolCharacters() {
        return this.characterCache != null ? this.characterCache : Collections.emptyList();
    }

    public LolCharacter findByName(String name) {
        return getLolCharacters().stream()
                .filter(character -> character.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}