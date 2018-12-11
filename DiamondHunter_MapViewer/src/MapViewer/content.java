// Loads and splits all sprites on start up.
// The sprites can easily be accessed as they
// are public and static.

package MapViewer;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class content {

	public static BufferedImage[][] ITEMS = load("/Sprites/items.gif", 16, 16);
	public static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(content.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
	

	}

