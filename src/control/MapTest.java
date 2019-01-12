package control;

import model.Map;

public class MapTest {


	public static void main(String[] args) {
		
		Map map = new Map("map1.txt");
		
		System.out.println(map.getBlocked(9, 1));

	}

}
