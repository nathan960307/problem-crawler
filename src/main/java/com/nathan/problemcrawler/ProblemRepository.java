package com.nathan.problemcrawler;

import com.nathan.problemcrawler.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {


    boolean existsByUrl(String url);  // 문제 URL이 이미 있는지 확인용
}
