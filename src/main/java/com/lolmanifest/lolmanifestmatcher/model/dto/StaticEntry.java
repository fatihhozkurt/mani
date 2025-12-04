package com.lolmanifest.lolmanifestmatcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaticEntry {
    @JsonProperty("champions") // JSON'daki anahtar adıyla eşleştir
    private List<ChampionWrapper> champions;
}