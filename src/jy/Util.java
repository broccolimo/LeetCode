package jy;

import org.junit.Test;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Util {
	/**
	 * @date 2017-04-17
	 * @problem Ñ¹ËõÍ¼Æ¬
	 */
	public void C_01(String oldPath, String newPath, int r1, int r2) throws IOException{
		File in = new File(oldPath);
		Image img = ImageIO.read(in);
		BufferedImage tag = new BufferedImage(r1, r2, BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(img.getScaledInstance(r1, r2, Image.SCALE_SMOOTH), 0, 0, null);
		FileOutputStream out = new FileOutputStream(newPath);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);
		out.close();
	}
	
	@Test
	public void test() throws IOException{
		//C_01("f:\\wall_1.jpg", "f:\\wall_3.jpg", 320, 160);
	}
}
