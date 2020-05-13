package com.life.lifegame.repository;

import java.util.List;

import com.life.lifegame.entity.BenchmarkEntry;
import org.springframework.data.repository.CrudRepository;

public interface BenchmarkRepository extends CrudRepository<BenchmarkEntry, Long> {
	List<BenchmarkEntry> findByElementsNumber(int elementsNumber);
}
