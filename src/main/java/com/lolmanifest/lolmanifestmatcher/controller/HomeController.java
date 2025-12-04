package com.lolmanifest.lolmanifestmatcher.controller;

import com.lolmanifest.lolmanifestmatcher.model.LolCharacter;
import com.lolmanifest.lolmanifestmatcher.service.CharacterService;
import com.lolmanifest.lolmanifestmatcher.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    private final CharacterService characterService;
    private final MatchService matchService;
    private final Random random = new Random();
    private final List<String> songs = List.of("manifest.mp3", "manifest2.mp3", "manifest3.mp3");

    public HomeController(CharacterService characterService, MatchService matchService) {
        this.characterService = characterService;
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String anasayfa(Model model) {
        List<LolCharacter> characters = characterService.getLolCharacters();
        model.addAttribute("karakterler", characters);
        return "index";
    }

    @GetMapping("/loading/{characterName}")
    public String loadingPage(@PathVariable String characterName, Model model) {
        String finalUrl = "/match/" + characterName;

        model.addAttribute("redirectUrl", finalUrl);
        model.addAttribute("quote", "Hesaplanıyor...");

        return "loading";
    }

    @GetMapping("/match/{characterName}")
    public String getMatchResult(@PathVariable String characterName, Model model) {
        LolCharacter selectedCharacter = characterService.findByName(characterName);
        MatchService.MatchResult matchResult = matchService.findMatch(characterName);

        model.addAttribute("secilenKarakter", selectedCharacter);
        model.addAttribute("eslesenKiz", matchResult.getGirl());
        model.addAttribute("eslesmeNedeni", matchResult.getReason());

        String selectedSong = songs.get(random.nextInt(songs.size()));
        model.addAttribute("songFileName", selectedSong);

        return "result";
    }
}