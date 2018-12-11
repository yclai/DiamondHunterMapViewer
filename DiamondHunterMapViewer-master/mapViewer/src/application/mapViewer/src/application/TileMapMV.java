package application;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class TileMapMV {

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	private Image TileImage;
	
	public TileMapMV(int tileSize) {
		this.tileSize = tileSize;
	}
	
	public void loadTiles(String s) {
		
		try {

			tileset = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize,0,tileSize,tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(
							col * tileSize,
							tileSize,
							tileSize,
							tileSize
						);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
		
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void GenerateTileImage (GridPane grid, int row, int col) {
	
		ImageView PTile = new ImageView();
		grid.add(PTile, col, row);
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		TileImage =SwingFXUtils.toFXImage( tiles [r][c].getImage(),null);
		PTile.setImage(TileImage);
	}
	
	
	
	public int getTileSize() { return tileSize; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	public int getIndex(int row, int col) {
		return map[row][col];
	}
	public int[][] getMap() {
		return map;
	}
}
	