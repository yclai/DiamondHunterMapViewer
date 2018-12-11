package application;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {
	MapViewerModel model = new MapViewerModel();
	TileMapMV tilemap = new TileMapMV(16);
   

    
    
    @FXML
    GridPane grid;
    
    @FXML
    public void initialize() {

		model.loadMap(grid);

}
    public void axeBtnClicked(){
        System.out.println("testing2");
        model.AxeCaptureMouseClick(grid); /*   to set axe on map when button clicked      */
    }
    
    public void boatBtnClicked(){
        System.out.println("testing");
        model.BoatCaptureMouseClick(grid);
    }

}
