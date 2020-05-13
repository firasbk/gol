package com.life.lifegame.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LifeGameProcessor implements Processor<Structure> {
	private final static Logger log = LoggerFactory.getLogger(LifeGameProcessor.class);

	@Override
	public Structure execute(Structure structure) {
		Structure calculatedStructure = updateElementsAndNeighbors(structure);
		return calculatedStructure;
	}
    /*
     * This method is the main method for updating the cells and their neighbors
     * @param structure
     * @return structure
     * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
     */
	private Structure updateElementsAndNeighbors(Structure structure) {
		List<Element> newElements = new ArrayList<>();

		int edgeLength = structure.getEdgeLength();
		boolean[][] structureArray = new boolean[edgeLength][edgeLength];
		boolean[][] revivedElements = new boolean[edgeLength][edgeLength];

		structure.getElements().forEach(e -> markElementOnArray(e.getX(), e.getY(), structureArray));

		structure.getElements().forEach(e -> {
			if (shouldSurvive(e, structureArray)) {
				newElements.add(e);
			}
			reviveNeighbors(e, structureArray, revivedElements, newElements);
		});

		Structure newStructure = new Structure();
		newStructure.setEdgeLength(edgeLength);
		newStructure.setElements(newElements);
		return newStructure;
	}
	/*
	 * This method is responsible for reviving of neighbors for an element
	 * @param element
	 * @param boolean array structure
	 * @param boolean array revive elements
	 * @return void
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	private void reviveNeighbors(Element element, boolean[][] structureArray, boolean[][] revivedElements,
	        List<Element> newElements) {
		int x = element.getX();
		int y = element.getY();
		int edgeLength = structureArray.length;
		assert x >= 0 && x < edgeLength && y >= 0 && y < edgeLength;

		reviveElement(x - 1, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x - 1, y, structureArray, revivedElements, newElements);
		reviveElement(x - 1, y + 1, structureArray, revivedElements, newElements);
		reviveElement(x, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x, y + 1, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y - 1, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y, structureArray, revivedElements, newElements);
		reviveElement(x + 1, y + 1, structureArray, revivedElements, newElements);
	}
	/*
	 * This method revives the element with its corresponding coordinates
	 * @param int x coordinate
	 * @param int y ordinate
	 * @param boolean structured array
	 * @param boolean array of revived elements
	 * @return void
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	private void reviveElement(int x, int y, boolean[][] structureArray, boolean[][] revivedElements,
	        List<Element> newElements) {
		int edgeLength = structureArray.length;
		if (x >= 0 && x < edgeLength && y >= 0 && y < edgeLength) {
			if (!revivedElements[x][y]) {
				if (3 == neighborsNumber(x, y, structureArray)) {
					revivedElements[x][y] = true;
					newElements.add(new Element(x, y));
				}
			}
		}
	}
	/*
	 * This method is used to mark the element if true or false
	 * @param int x coordinate
	 * @param int y ordinate
	 * @param boolean structured array
	 * @return void
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	private void markElementOnArray(int x, int y, boolean[][] structureArray) {
		try {
			structureArray[x][y] = true;
		} catch (IndexOutOfBoundsException exception) {
			log.error("Improper element on list.", exception.getMessage());
		}
	}
	/*
	 * This method decides if the element should live or die
	 * @param Element element
	 * @param boolean structured array
	 * @return void
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	private boolean shouldSurvive(Element element, boolean[][] structureArray) {
		int n = neighborsNumber(element.getX(), element.getY(), structureArray);
		if (2 == n || 3 == n) {
			return true;
		}
		return false;
	}
	/*
	 * This method is used to calculate the neighbors for an element
	 * @param int x coordinate
	 * @param int y ordinate
	 * @param boolean structured array
	 * @return int number of neighbors
	 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
	 */
	private int neighborsNumber(int x, int y, boolean[][] structureArray) {

		int edgeLength = structureArray.length;
		assert x >= 0 && x < edgeLength && y >= 0 && y < edgeLength;

		int neighborsNumber = 0;

		if (x > 0) {
			if (structureArray[x - 1][y]) {
				neighborsNumber++;
			}
			if (y > 0 && structureArray[x - 1][y - 1]) {
				neighborsNumber++;
			}
			if (y < edgeLength - 1 && structureArray[x - 1][y + 1]) {
				neighborsNumber++;
			}
		}
		if (y > 0) {
			if (structureArray[x][y - 1]) {
				neighborsNumber++;
			}
		}
		if (y < edgeLength - 1) {
			if (structureArray[x][y + 1]) {
				neighborsNumber++;
			}
		}
		if (x < edgeLength - 1) {
			if (structureArray[x + 1][y]) {
				neighborsNumber++;
			}
			if (y > 0 && structureArray[x + 1][y - 1]) {
				neighborsNumber++;
			}
			if (y < edgeLength - 1 && structureArray[x + 1][y + 1]) {
				neighborsNumber++;
			}
		}
		return neighborsNumber;
	}
}
