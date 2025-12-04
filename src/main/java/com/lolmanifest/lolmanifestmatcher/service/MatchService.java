package com.lolmanifest.lolmanifestmatcher.service;

import com.lolmanifest.lolmanifestmatcher.model.ManifestGirl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MatchService {

    public static class MatchResult {
        private final ManifestGirl girl;
        private final String reason;

        public MatchResult(ManifestGirl girl, String reason) {
            this.girl = girl;
            this.reason = reason;
        }

        public ManifestGirl getGirl() {
            return girl;
        }

        public String getReason() {
            return reason;
        }
    }

    private final ManifestGirl hilal = new ManifestGirl("Hilal", "Karizmatik Vokal & Lider", "/images/hilal.png");
    private final ManifestGirl mina = new ManifestGirl("Mina", "Ritim Gitar & Grubun Neşesi", "/images/mina.png");
    private final ManifestGirl zoktay = new ManifestGirl("Zoktay", "Solo Gitar & Asi Ruh", "/images/zoktay.png");
    private final ManifestGirl lidya = new ManifestGirl("Lidya", "Bas Gitar & Cool Duruş", "/images/lidya.png");
    private final ManifestGirl esin = new ManifestGirl("Esin", "Klavye & Melodik Deha", "/images/esin.png"); // Varsayılan esin.png
    private final ManifestGirl sueda = new ManifestGirl("Sueda", "Bateri & Sarsılmaz Ritim", "/images/sueda.png");

    private final Map<String, MatchResult> matchRules = new HashMap<>();
    private final Random random = new Random();

    @PostConstruct
    private void initializeMatchRules() {
        addRule(hilal, "Liderlik ruhun ve sahnedeki büyüleyici duruşunla, grubun vokalisti ve yüzü sen olurdun!", "Ashe", "Kai'Sa", "Leona", "Samira", "Sivir");

        addRule(mina, "Yaydan pozitif enerji ve neşeyle, grubun ritim gitaristi olarak herkesin modunu yükseltirdin!", "Lux", "Seraphine", "Neeko", "Yuumi", "Zoe");

        addRule(zoktay, "Kurallara sığmayan asi ruhun ve yeteneğinle, en çılgın ve unutulmaz gitar sololarına imza atardın!", "Jinx", "Xayah", "Katarina", "Akali", "Qiyana");

        addRule(lidya, "Sahnedeki cool ve gizemli duruşunla, bas gitarın derin notalarıyla müziğe o sarsılmaz temeli sen atardın.", "Morgana", "Vex", "Aphelios", "Kayn", "Gwen");

        addRule(esin, "Yaratıcı ve sihirli dokunuşlarınla, klavyenin başında grubun en akılda kalıcı ve uyumlu melodilerini sen yaratırdın.", "Sona", "Lulu", "Nami", "Irelia", "Orianna");

        addRule(sueda, "Sarsılmaz gücün ve odaklanmanla, baterinin başında grubun ritmini belirleyen o güçlü ve net vuruşları sen yapardın.", "Vi", "Illaoi", "Rell", "Sejuani", "Poppy");
    }

    private void addRule(ManifestGirl girl, String reason, String... championNames) {
        MatchResult result = new MatchResult(girl, reason);
        for (String championName : championNames) {
            matchRules.put(championName, result);
        }
    }

    public MatchResult findMatch(String lolCharacterName) {
        MatchResult result = matchRules.get(lolCharacterName);

        if (result == null) {
            result = getRandomMatch(lolCharacterName);
        }

        if (result.getGirl().getName().equals("Esin")) {
            return handleEsinRandomImage(result);
        }

        return result;
    }

    private MatchResult getRandomMatch(String lolCharacterName) {
        int hashCode = lolCharacterName.hashCode();
        int choice = Math.abs(hashCode % 6); // 6 grup üyesi var

        switch (choice) {
            case 0:
                return new MatchResult(hilal, "Sen bu olay yaşanırken orda mıydın? Çok oradaymış gibi ağlıyorsun...");
            case 1:
                return new MatchResult(mina, "Enerjin o kadar bulaşıcı ki, en beklenmedik anlarda bile gruba neşe katıyorsun! Demek ki biraz da Yıldız Tilbe'ye benziyorsun");
            case 2:
                return new MatchResult(zoktay, "Uff Zoktay çıktı be!");
            case 3:
                return new MatchResult(lidya, "Gizemli ve cool tavrınla, sen grubun en merak edilen üyesi olurdun.");
            case 4:
                return new MatchResult(esin, "Yaratıcılığın sınır tanımıyor, klavyeyle yepyeni müzik türleri icat ederdin! Ayrıca tramvaylarla aran iyi gibi duruyor!");
            default:
                return new MatchResult(sueda, "Kararlılığın ve gücünle, sen grubun ritmini belirleyen davulcu olurdun! Bu kızı tanımıyorum şaka yok.");
        }
    }

    private MatchResult handleEsinRandomImage(MatchResult originalResult) {
        ManifestGirl esinWithRandomImage = new ManifestGirl(
                originalResult.getGirl().getName(),
                originalResult.getGirl().getRole(),
                originalResult.getGirl().getImageUrl()
        );

        double chance = random.nextDouble();

        if (chance > 0.7) {
            if (random.nextBoolean()) {
                esinWithRandomImage.setImageUrl("/images/esin1.png");
            } else {
                esinWithRandomImage.setImageUrl("/images/esin2.png");
            }
        }
        return new MatchResult(esinWithRandomImage, originalResult.getReason());
    }
}