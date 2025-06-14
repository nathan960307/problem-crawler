package com.nathan.problemcrawler;

import com.nathan.problemcrawler.dto.ProblemDto;
import com.nathan.problemcrawler.entity.Problem;
import com.nathan.problemcrawler.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public void save(ProblemDto dto) {

        boolean exists = problemRepository.existsByUrl(dto.getUrl());
        if (exists) {
            return; // 혹은 예외 던지기
        }
        Problem entity = Problem.builder()
                .site(dto.getSite())
                .title(dto.getTitle())
                .url(dto.getUrl())
                .level(dto.getLevel())
                .tags(dto.getTags())
                .tier(dto.getTier())
                .isActive(true)
                .build();

        problemRepository.save(entity);
    }
}
