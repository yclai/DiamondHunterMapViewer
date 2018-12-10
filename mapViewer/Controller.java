package application;

import javafx.fxml.FXML;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;

public class Controller {
	MapViewerModel model = new MapViewerModel();
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

		model.loadMap(grid);

}
}
