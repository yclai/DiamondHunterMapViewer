package MapViewer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Drawing {
	
	//method to draw an axe on the grid
	public void drawAxe(GridPane grid,int row,int col,int clear) {
		ImageView axe_tile = new ImageView();
		grid.add(axe_tile, col, row);
        Image axeImg = SwingFXUtils.toFXImage(content.ITEMS[1][1], null);
        axe_tile.setImage(axeImg);
        if (clear==1)
        {
        	axe_tile.setImage(null);
        }
	}
	
	//method to draw a boat on the grid
	public void drawBoat(GridPane grid,int row,int col,int clear) {
		ImageView boat_tile = new ImageView();
		grid.add(boat_tile, col, row);
        Image boatImg = SwingFXUtils.toFXImage(content.ITEMS[1][0], null);
        boat_tile.setImage(boatImg);
        if (clear==1)
        {
        	boat_tile.setImage(null);
        }
	}
	
	//method to initialise and draw a player on the grid
	public void drawPlayer (GridPane grid)
	{
		ImageView player_tile = new ImageView();
		grid.add(player_tile, 17, 17);
		Image playerImage = SwingFXUtils.toFXImage(content.PLAYER[0][0], null);
		player_tile.setImage(playerImage);
	}
	
	//method to initialise and draw a diamond on the grid
	public void drawDiamond (GridPane grid,int row,int col)
	{
		ImageView diamond_tile = new ImageView();
		grid.add(diamond_tile, col, row);
		Image diamondImage = SwingFXUtils.toFXImage(content.DIAMOND[0][0], null);
		diamond_tile.setImage(diamondImage);
	}
	
	public void populateDiamond (GridPane grid)
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
}
