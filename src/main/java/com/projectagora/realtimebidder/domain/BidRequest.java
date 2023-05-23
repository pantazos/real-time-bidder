package com.projectagora.realtimebidder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BidRequest {
    private String id;
    private App app;
    private Device device;
}
