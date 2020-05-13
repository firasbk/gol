package com.life.lifegame.controller.rest;

import java.util.List;

import com.life.lifegame.entity.BenchmarkEntry;
import com.life.lifegame.processor.Structure;
import com.life.lifegame.service.BenchmarkService;
import com.life.lifegame.service.LifeGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/lifegame")
public class LifeGameController {
	private final static Logger log = LoggerFactory.getLogger(LifeGameController.class);

	@Autowired
	private LifeGameService lifeGameService;

	@Autowired
	private BenchmarkService benchmarkService;
	/*
	 * This method is the main Rest controller method used to calculate the generations of the game
	 * @param Structure object
	 * @return Structure
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	@ResponseBody
	@RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Structure calculate(@RequestBody Structure structure) {
		List<BenchmarkEntry> benchmarkList = benchmarkService.findAll();
		log.info("NUMBER OF BENCHMARK ENTRIES: " + benchmarkList.size());

		long startTime = System.nanoTime();
		Structure calculatedStructure = lifeGameService.calculateSequentStructure(structure);
		long duration = System.nanoTime() - startTime;
		log.info("DURATION  IS : " + duration);
		benchmarkService.saveBenchmarkEntry(calculatedStructure.getElements().size(), duration);

		return calculatedStructure;
	}
}
