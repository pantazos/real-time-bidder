package com.projectagora.realtimebidder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Device {
    private OS mobileOS;
    private Geolocation geolocation;
}
