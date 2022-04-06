package com.rubypaper.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Board;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long>{

}
