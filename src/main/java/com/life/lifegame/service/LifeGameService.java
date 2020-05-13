package com.life.lifegame.service;

import com.life.lifegame.processor.LifeGameProcessor;
import com.life.lifegame.processor.Structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LifeGameService {

	@Autowired
	LifeGameProcessor lifeGameProcessor;
	/*
	 * This Service method provokes the execution of calculations of Game of Life from the processor
	 * @param structure
	 * @return structure
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	public Structure calculateSequentStructure(Structure structure) {
		return lifeGameProcessor.execute(structure);
	}
}
