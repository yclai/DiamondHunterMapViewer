package MapViewer;

import MapViewer.Drawing;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import com.neet.DiamondHunter.GameState.PlayState;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import MapViewer.TileMapMV;
import javafx.scene.layout.Pane;

public class MapViewerModel {
	int COLNUM = 40;
	int ROWNUM = 40;

	// save map in this array
	int[][] map;

	// save the temporary position
	int[] temp_axe = new int[2];
	int[] temp_boat = new int[2];
	Drawing Draw = new Drawing();
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
	public void AxeCaptureMouseClick (GridPane grid)
	{
		//first, set pane on every grid that can place axe
		for (int row = 0; row < ROWNUM; row++) {
			for (int col = 0; col < COLNUM; col++) {
			
				//show warning when clicked on player or diamond
				if (isDiamondOrPlayer(row,col)==true)
					ShowWarningForNonGrassPane (grid,"Do not put item on diamond/player",row,col);
				else if (map[row][col]==1 ||map[row][col]==2||map[row][col]==3)
					PaneAxeClickEffect (grid, row, col); 
				else
				//show warning when clicked on non-grass pane
					ShowWarningForNonGrassPane (grid,"You can only put item on grass.",row,col);
			}
	}
	}
		
	public void PaneAxeClickEffect (GridPane grid,int row,int col)
	{
		Pane pane= new Pane();
		grid.add(pane, col, row);
		//when that pane is clicked to set axe
		pane.setOnMouseClicked(e->{
			Controller.axeSet=1;
			//WritePositionToFile ("../DiamondHunter_MapViewer/bin/Maps/Axe.txt",col,row);
			PlayState.axe[0]=row;
			PlayState.axe[1]=col;
			//if axe is previously placed before, clear the previous one
			if (temp_axe[0]!=0 && temp_axe[1]!=0)
			{
				ClearPreviousAxe (grid,temp_axe[0],temp_axe[1]);
			}
			temp_axe[0]=row;
			temp_axe[1]=col;
			//if axe is placed on a boat, replace boat with the axe
			if (temp_boat [0]==temp_axe[0] && temp_boat[1]==temp_axe[1])
			{
				ClearPreviousBoat (grid,temp_boat[0],temp_boat[1]);
				Controller.boatSet=0;
			}
			Draw.drawAxe(grid,row,col,0);
			ShowMessage ("Successfully set axe!");
			
		}	
				);
		
	}
	public void BoatCaptureMouseClick (GridPane grid)
	{
		//set pane on every grid that can place boat
		for (int row = 0; row < ROWNUM; row++) {
			for (int col = 0; col < COLNUM; col++) {
				
				//show warning when clicked on a player or diamond
				if (isDiamondOrPlayer(row,col)==true)
					ShowWarningForNonGrassPane (grid,"Do not put item on diamond/player",row,col);
				else if (map[row][col]==1 ||map[row][col]==2||map[row][col]==3)
					PaneBoatClickEffect (grid, row, col);
				else
				//show warning when clicked on a non-grass grid
					ShowWarningForNonGrassPane (grid,"You can only put item on grass.",row,col);
			}
	}
	}
	
	//same method with that of axe
	public void PaneBoatClickEffect (GridPane grid,int row,int col)
	{
		Pane pane= new Pane();
		grid.add(pane, col, row);
		
		pane.setOnMouseClicked(e->{
			Controller.boatSet=1;
			//WritePositionToFile ("../DiamondHunter_MapViewer/bin/Maps/Boat.txt",col,row);
			
			PlayState.boat[0]=row;
			PlayState.boat[1]=col;
			
			if (temp_boat[0]!=0 && temp_boat[1]!=0)
			{
				ClearPreviousBoat (grid,temp_boat[0],temp_boat[1]);
			}
			temp_boat[0]=row;
			temp_boat[1]=col;
			if (temp_boat [0]==temp_axe[0] && temp_boat[1]==temp_axe[1])
			{
				ClearPreviousAxe (grid,temp_axe[0],temp_axe[1]);
				Controller.axeSet=0;
			}
				Draw.drawBoat(grid,row,col,0);
			
			ShowMessage ("Successfully set Boat!");
			
		}	
				);
		
	}
	
	//show warning message when clicked on a pane that is 
	//not supposed to be clicked on. 
	public void ShowWarningForNonGrassPane (GridPane grid,String message,int row, int col){
		Pane pane = new Pane();
		grid.add (pane,col,row);
		
		pane.setOnMouseClicked(e->{
			ShowMessage (message);
		});
	}
	
	//writing the column and row index into a txt file.
	public void WritePositionToFile(String path, int row, int col)
	{
		try{
            File file = new File(path);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Write in file
            bw.write(Integer.toString(col));
            bw.newLine();
            bw.write(Integer.toString(row));
            // Close connection
            bw.close(); 
        }
        catch(Exception e){
            System.out.println(e);
        }
	}

	//a method to show alert message
	public static void ShowMessage(String Message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(Message);
		alert.showAndWait();
	}
	
	public void ClearPreviousAxe (GridPane grid, int row, int col)
	{
		//clear an axe then redraw that tile.
		Draw.drawAxe (grid,row,col,1);
		tileMap.GenerateTileImage(grid, row, col);
	}
	
	public void ClearPreviousBoat (GridPane grid, int row, int col)
	{
		//clear a boat then redraw that tile.
		Draw.drawBoat (grid,row,col,1);
		tileMap.GenerateTileImage(grid, row, col);
	}
	
	//check whether that location has player or diamond
	public boolean isDiamondOrPlayer (int row, int col)
	{
		if (row==20&&col==20)
			return true;
		if (row==12&&col==36)
			return true;
		if (row==28&&col==4)
			return true;
		if (row==4&&col==34)
			return true;
		if (row==28&&col==19)
			return true;
		if (row==35&&col==26)
			return true;
		if (row==38&&col==36)
			return true;
		if (row==27&&col==28)
			return true;
		if (row==20&&col==30)
			return true;
		if (row==14&&col==25)
			return true;
		if (row==4&&col==21)
			return true;
		if (row==9&&col==14)
			return true;
		if (row==4&&col==3)
			return true;
		if (row==20&&col==14)
			return true;
		if (row==13&&col==20)
			return true;
		if (row==20&&col==20)
			return true;
		if (row==17&&col==17)
			return true;
		return false;	
	}
	
}

