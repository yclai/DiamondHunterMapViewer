package MapViewer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.neet.DiamondHunter.Entity.Diamond;

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
				
				if (map[row][col]==1 ||map[row][col]==2||map[row][col]==3)
				PaneAxeClickEffect (grid, row, col);
				else
				//else set pane to show warning when clicked
				ShowWarningForNonGrassPane (grid,"You can only put item on grass.",row,col);
			}
	}
	}
		
	public void PaneAxeClickEffect (GridPane grid,int row,int col)
	{
		Pane pane= new Pane();
		grid.add(pane, col, row);
		
		pane.setOnMouseClicked(e->{
			WritePositionToFile ("../DiamondHunter_MapViewer/bin/Maps/Axe.txt",col,row);
			
			if (temp_axe[0]!=0 && temp_axe[1]!=0)
			{
				ClearPreviousAxe (grid,temp_axe[0],temp_axe[1]);
			}
			temp_axe[0]=row;
			temp_axe[1]=col;
			if (temp_boat [0]==temp_axe[0] && temp_boat[1]==temp_axe[1])
			{
				ClearPreviousBoat (grid,temp_boat[0],temp_boat[1]);
				Controller.boatSet=0;
			}
			drawAxe(grid,row,col,0);
			ShowMessage ("Successfully set axe!");
			
		}	
				);
		
	}
	public void BoatCaptureMouseClick (GridPane grid)
	{
		//first, set pane on every grid that can place axe
		for (int row = 0; row < ROWNUM; row++) {
			for (int col = 0; col < COLNUM; col++) {
				
				if (map[row][col]==1 ||map[row][col]==2||map[row][col]==3)
				PaneBoatClickEffect (grid, row, col);
				else
				//else set pane to show warning when clicked
				ShowWarningForNonGrassPane (grid,"You can only put item on grass.",row,col);
			}
	}
	}
		
	public void PaneBoatClickEffect (GridPane grid,int row,int col)
	{
		Pane pane= new Pane();
		grid.add(pane, col, row);
		
		pane.setOnMouseClicked(e->{
			WritePositionToFile ("../DiamondHunter_MapViewer/bin/Maps/Boat.txt",col,row);
			//IF TEMP!=0,THEN CLEAR LAST
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
				drawBoat(grid,row,col,0);
			
			ShowMessage ("Successfully set Boat!");
			
		}	
				);
		
	}
	
	public void ShowWarningForNonGrassPane (GridPane grid,String message,int row, int col){
		Pane pane = new Pane();
		grid.add (pane,col,row);
		
		pane.setOnMouseClicked(e->{
			ShowMessage (message);
		});
	}
	
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

	public static void drawAxe(GridPane grid,int row,int col,int clear) {
		ImageView axe_tile = new ImageView();
		grid.add(axe_tile, col, row);
        Image axeImg = SwingFXUtils.toFXImage(content.ITEMS[1][1], null);
        axe_tile.setImage(axeImg);
        if (clear==1)
        {
        	axe_tile.setImage(null);
        }
	}
	
	
	public static void drawBoat(GridPane grid,int row,int col,int clear) {
		ImageView boat_tile = new ImageView();
		grid.add(boat_tile, col, row);
        Image boatImg = SwingFXUtils.toFXImage(content.ITEMS[1][0], null);
        boat_tile.setImage(boatImg);
        if (clear==1)
        {
        	boat_tile.setImage(null);
        }
	}
	public static void drawPlayer (GridPane grid)
	{
		ImageView player_tile = new ImageView();
		grid.add(player_tile, 17, 17);
		Image playerImage = SwingFXUtils.toFXImage(content.PLAYER[0][0], null);
		player_tile.setImage(playerImage);
	}
	
	public static void drawDiamond (GridPane grid,int row,int col)
	{
		ImageView diamond_tile = new ImageView();
		grid.add(diamond_tile, col, row);
		Image diamondImage = SwingFXUtils.toFXImage(content.DIAMOND[0][0], null);
		diamond_tile.setImage(diamondImage);
	}
	
	public static void populateDiamond (GridPane grid)
	{

		drawDiamond(grid,20, 20);
		drawDiamond(grid,12, 36);
		drawDiamond(grid,28, 4);
		drawDiamond(grid,4, 34);
		drawDiamond(grid,28, 19);
		drawDiamond(grid,35, 26);
		drawDiamond(grid,38, 36);
		drawDiamond(grid,27, 28);
		drawDiamond(grid,20, 30);
		drawDiamond(grid,14, 25);
		drawDiamond(grid,4, 21);
		drawDiamond(grid,9, 14);
		drawDiamond(grid,4, 3);
		drawDiamond(grid,20, 14);
		drawDiamond(grid,13, 20);
	}
	public static void ShowMessage(String Message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(Message);
		alert.showAndWait();
	}
	
	public void ClearPreviousAxe (GridPane grid, int row, int col)
	{
		
		drawAxe (grid,row,col,1);
		tileMap.GenerateTileImage(grid, row, col);
	}
	
	public void ClearPreviousBoat (GridPane grid, int row, int col)
	{
		drawBoat (grid,row,col,1);
		tileMap.GenerateTileImage(grid, row, col);
	}
	
	
}

