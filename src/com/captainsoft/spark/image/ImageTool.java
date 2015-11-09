package com.captainsoft.spark.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;

/**
 * Support for images. Static methods only here.
 *
 * @author mathias fringes
 */
public final class ImageTool {

    // fields
		
	private static Toolkit tk;	

    // static

	static {
		tk = Toolkit.getDefaultToolkit();       
	}

    // constructors
	
	private ImageTool() {
		super();
	}

    // public
	
	public static Image createImage(byte[] imageData) throws IOException {
		return createImage(imageData, 0, 0);
	}
	
	public static Image createImage(byte[] imageData, int width, int height) throws IOException {		
		if (width != 0 || height != 0) {
			Image i = ImageIO.read(new MemoryCacheImageInputStream(new ByteArrayInputStream(imageData)));
			return i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} else {
			return ImageIO.read(new MemoryCacheImageInputStream(new ByteArrayInputStream(imageData)));
		}
	}

	public static Image createFiltered(Image image, ImageFilter filter) {		
		return tk.createImage(new FilteredImageSource(image.getSource(), filter));		
	}

}
