package application;

import javafx.fxml.FXML;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import application.Main;

public class Controller {
	TileMapMV tilemap = new TileMapMV(16);
    public void boatBtnClicked(){
        System.out.println("testing");
       // setBoat(); /*   to set boat on map when button clicked      */
    }


    public void axeBtnClicked(){
        System.out.println("testing2");
        //setAxe(); /*   to set axe on map when button clicked      */
    }
    
    @FXML
    GridPane grid;
    
    @FXML
    public void initialize() {
		
		for (int i = 0; i < 40; i++) {
			ColumnConstraints colConstraints = new ColumnConstraints();
			colConstraints.setPercentWidth(2.5);
			grid.getColumnConstraints().add(colConstraints);
		}

		for (int i = 0; i < 40; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(2.5);
			grid.getRowConstraints().add(rowConstraints);
		}

		tilemap.loadTiles("/Tilesets/testtileset.gif");
		tilemap.loadMap("/Maps/testmap.map");
				
		for (int row = 0; row < 40; row++) {
			for (int col = 0; col < 40; col++) {
				
				tilemap.GenerateTileImage(grid, row, col);
			}

		}
		

}
}
