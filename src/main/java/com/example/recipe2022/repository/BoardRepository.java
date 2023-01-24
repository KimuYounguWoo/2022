package com.example.recipe2022.repository;


import com.example.recipe2022.data.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Optional<Board> findById(int id);
    @Modifying
    @Query("update Board p set p.viewCnt = p.viewCnt + 1 where p.id = :id")
    int updateCount(int id);

    Page<Board> findByUseYNAndTitleContainingAndContentsContaining(Character useYN, String title, String contents, Pageable pageable);

//    Page<Board> findByUseYN(Pageable pageable, Boolean useYN);


    Page<Board> findByRecommendGreaterThanEqual(Pageable pageable, int number);
}