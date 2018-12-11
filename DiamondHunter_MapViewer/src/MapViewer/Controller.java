package MapViewer;
import com.neet.DiamondHunter.Main.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {
	MapViewerModel model = new MapViewerModel();
	TileMapMV tilemap = new TileMapMV(16);
    int boatSet=0;
    int axeSet=0;

    
    
    @FXML
    GridPane grid;
    
    @FXML
    public void initialize() {
		model.loadMap(grid);
}
    public void axeBtnClicked(){
        model.AxeCaptureMouseClick(grid); /*   to set axe on map when button clicked      */
        axeSet=1;
    }
    
    public void boatBtnClicked(){
        model.BoatCaptureMouseClick(grid);
        boatSet=1;
        
    }

    
    public void playBtnClicked(){
    
            Game.runGame();
                
    }
}
