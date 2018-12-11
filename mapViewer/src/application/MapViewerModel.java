package application;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import application.TileMapMV;
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
			WritePositionToFile ("../mapViewer/bin/application/Axe.txt",col,row);
			
			if (temp_axe[0]!=0 && temp_axe[1]!=0)
			{
				ClearPreviousAxe (grid,temp_axe[0],temp_axe[1]);
			}
			drawAxe(grid,row,col,0);
			temp_axe[0]=row;
			temp_axe[1]=col;
			
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
			WritePositionToFile ("../mapViewer/bin/application/Boat.txt",col,row);
			//IF TEMP!=0,THEN CLEAR LAST
			if (temp_boat[0]!=0 && temp_boat[1]!=0)
			{
				ClearPreviousBoat (grid,temp_boat[0],temp_boat[1]);
			}
			drawBoat(grid,row,col,0);
			temp_boat[0]=row;
			temp_boat[1]=col;
			
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
            bw.write(row);
            bw.write(col);
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

