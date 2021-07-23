package com.tunaPasta13.activity;
/**
 * 
 * @author kris@krislq.com
 *
 */
public class Snow {
	Coordinate coordinate;
	int speed;
	
	public Snow(int x, int y, int speed){
		coordinate = new Coordinate(x, y);
		System.out.println("Speed:"+speed);
		this.speed = speed;
		if(this.speed == 0) {
			this.speed =1;
		}
	}
	
}
