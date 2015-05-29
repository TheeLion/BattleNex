package com.battlenex.world;

public class WorldObject {
	
	public int x, y, height, id, type, face;
	
	public WorldObject() {
	
	}
	
	public WorldObject(int id, int x, int y, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.height = height;
	}
	
	public WorldObject(int x, int y, int h, int id, int type, int face) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.id = id;
		this.type = type;
		this.face = face;	
	}
	
}