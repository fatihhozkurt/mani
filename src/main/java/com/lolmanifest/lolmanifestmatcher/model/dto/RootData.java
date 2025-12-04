package com.lolmanifest.lolmanifestmatcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootData {
    @JsonProperty("data")
    private StaticEntry staticEntry;
}