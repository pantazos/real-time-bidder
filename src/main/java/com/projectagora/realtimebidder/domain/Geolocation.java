package com.projectagora.realtimebidder.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Geolocation {
    private Double latitude;
    private Double longitude;
    private String country;
}
