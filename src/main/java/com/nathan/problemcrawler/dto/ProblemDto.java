package com.nathan.problemcrawler.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemDto {
    private String site;
    private String title;
    private String url;
    private Integer level;
    private String tags;
    private String tier;
}
