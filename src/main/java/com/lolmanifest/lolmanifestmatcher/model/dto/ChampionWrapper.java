package com.lolmanifest.lolmanifestmatcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionWrapper {
    private ChampionValue value;
}