package com.plapro.beans;

import java.util.ArrayList;
import java.util.List;

public class Chart {
	private List<String> x;
	private List<Integer> y;
	private int n;
	public Chart(){
		x = new ArrayList<String>();
		y = new ArrayList<Integer>();
		n = 0;
	}
	public List<String> getX() {
		return x;
	}
	public void setX(List<String> x) {
		this.x = x;
	}
	public List<Integer> getY() {
		return y;
	}
	public void setY(List<Integer> y) {
		this.y = y;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public void addNewValue(String x, int y) {
		this.x.add(x);
		this.y.add(y);
		n++;
	}
	@Override
	public String toString() {
		return "Chart [x=" + x + ", y=" + y + ", n=" + n + "]";
	}
}
