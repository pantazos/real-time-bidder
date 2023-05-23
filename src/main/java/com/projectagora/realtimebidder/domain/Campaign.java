package com.projectagora.realtimebidder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Campaign {
    private String id;
    private String name;
    private Double price;
    private String adCreative;
    private List<String> targetedCountries = new ArrayList<>();
    private List<String> targetedLocations = new ArrayList<>();
}
