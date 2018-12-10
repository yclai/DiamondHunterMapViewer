package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import application.TileMapMV;

public class MapViewerModel {
	int COLNUM = 40;
	int ROWNUM = 40;

	// save map in this array
	int[][] map;

	// save the temporary position
	int[] temp_axe = new int[2];
	int[] temp_boat = new int[2];

	TileMapMV tileMap = new TileMapMV(16);

	public void loadMap(GridPane grid) {

			tileMap.loadTiles("/Tilesets/testtileset.gif");
			tileMap.loadMap("/Maps/testmap.map");
			map = tileMap.getMap();

			for (int row = 0; row < ROWNUM; row++) {
				for (int col = 0; col < COLNUM; col++) {
					
					tileMap.GenerateTileImage(grid, row, col);
				}

			}
		}
	}

