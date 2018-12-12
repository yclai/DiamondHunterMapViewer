/**
 * @author zc
 * This class is used to initialise UI elements
 */


package MapViewer;
import com.neet.DiamondHunter.Main.Game;
import MapViewer.MapViewerModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {
	MapViewerModel model = new MapViewerModel();
	TileMapMV tilemap = new TileMapMV(16);
    public static int boatSet=0;
    public static int axeSet=0;

    
    
    @FXML
    GridPane grid;
    
    @FXML
    public void initialize() {
		model.loadMap(grid);
		MapViewerModel.drawPlayer(grid);
		MapViewerModel.populateDiamond(grid);
}
    public void axeBtnClicked(){
        model.AxeCaptureMouseClick(grid); //   to set axe on map when button clicked      
       
    }
    
    public void boatBtnClicked(){
        model.BoatCaptureMouseClick(grid);//   to set boat on map when button clicked  
        
        
    }

    /**
     * Launch game when play button clicked
     * after checking if baot and axe set
     */
    
    public void playBtnClicked(){
    	if( (boatSet==0 && axeSet==0) || (boatSet==1 && axeSet==0)  || (boatSet==0 && axeSet==1)  ) {
           MapViewerModel.ShowMessage("Please set both of the boat and axe");
                
    	}else {
    	 Game.runGame();
    	}
    }
}