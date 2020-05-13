package com.life.lifegame.service;

import java.util.List;

import com.life.lifegame.entity.BenchmarkEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.life.lifegame.repository.BenchmarkRepository;

@Service
public class BenchmarkService {

	@Autowired
	BenchmarkRepository benchmarkRepository;

	public void saveBenchmarkEntry(int elementsNumber, long nanoTime) {
		benchmarkRepository.save(new BenchmarkEntry(elementsNumber, nanoTime));
	}

	public List<BenchmarkEntry> findAll() {
		return (List<BenchmarkEntry>) benchmarkRepository.findAll();
	}

	public List<BenchmarkEntry> findByElementsNumber(int elementsNumber) {
		return benchmarkRepository.findByElementsNumber(elementsNumber);
	}
}
