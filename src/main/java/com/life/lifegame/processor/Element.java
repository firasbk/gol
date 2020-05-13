package com.life.lifegame.processor;
/*
 * This class holds the structure of an element consisting of x and y coordinates
 * @author Firas Bou Karroum <firas.boukarroum@gmail.com>
 */
public class Element {

	private int x;
	private int y;

	public Element() {

	}

	public Element(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Element))
			return false;
		if (obj == this)
			return true;
		Element element = (Element) obj;

		return this.x == element.getX() && this.y == element.getY();
	}
}
