package newkeogram;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

//import newkeogram.ChooseKeodata;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SinPanel extends JPanel {
	public static File[] array;

	public static void tree(File f, int level) { // 递归获取目录中的所有文件名

		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "    ";
		}
		array = f.listFiles();
		// System.out.println(array);
		for (int i = 0; i < array.length; i++) {
			// System.out.println(preStr + array[i].getName()); //文件名存在array数组中

			if (array[i].isDirectory()) {
				tree(array[i], level + 1);
			}
		}

	}

	// public Image getImage(){
	// int w=10;
	// int h=256;
	// int[] srcPixArray=new int[w*h];
	// for(int i=0;i<h;i++){
	// for(int j=0;j<w;j++)
	// {
	// srcPixArray[j+i*w]=(255 << 24) | (255-i << 16) | (255-i << 8 )|255-i;
	// }
	//
	// }
	// Image pic=createImage(new MemoryImageSource(w,h,srcPixArray,0,w));
	// return pic;
	//
	//
	// }

	public void paintComponent(Graphics g) {
		String fileName1, timestart, timeend, fileName2;

		super.paintComponent(g);

		Dimension panelSize = this.getSize();
		Location center = new Location(panelSize.width / 2,
				panelSize.height / 2);
		// int radius = (int)((Math.min(panelSize.width,
		// panelSize.height)/2)*0.9); //radius=297
		// System.out.println(radius);
		// 确定每个点的坐标
		int radius = 500;

		g.setColor(Color.black);
		/* 画坐标轴及刻度 */
		g.drawLine(center.x - radius, center.y + 220, center.x + radius,
				center.y + 220); // x轴的横线
		g.drawLine(center.x - radius, center.y - 220, center.x - radius,
				center.y + 220); // y轴竖线

		g.drawLine(center.x - radius, center.y, center.x - radius - 5, center.y);
		g.drawLine(center.x - radius, center.y + 220 / 3,
				center.x - radius - 5, center.y + 220 / 3);
		g.drawLine(center.x - radius, center.y + 220 * 2 / 3, center.x - radius
				- 5, center.y + 220 * 2 / 3);
		g.drawLine(center.x - radius, center.y + 220, center.x - radius - 5,
				center.y + 220); // 写M.S.-90
		g.drawLine(center.x - radius, center.y - 220, center.x - radius - 5,
				center.y - 220);
		g.drawLine(center.x - radius, center.y - 220 / 3,
				center.x - radius - 5, center.y - 220 / 3);
		g.drawLine(center.x - radius, center.y - 220 * 2 / 3, center.x - radius
				- 5, center.y - 220 * 2 / 3);

		g.drawLine(center.x - radius, center.y + 220, center.x - radius,
				center.y + 220 + 5);
		g.drawLine(center.x - radius * 3 / 4, center.y + 220, center.x - radius
				* 3 / 4, center.y + 220 + 5);
		g.drawLine(center.x - radius * 2 / 4, center.y + 220, center.x - radius
				* 2 / 4, center.y + 220 + 5);
		g.drawLine(center.x - radius / 4, center.y + 220,
				center.x - radius / 4, center.y + 220 + 5);
		g.drawLine(center.x, center.y + 220, center.x, center.y + 220 + 5);
		g.drawLine(center.x + radius / 4, center.y + 220,
				center.x + radius / 4, center.y + 220 + 5);
		g.drawLine(center.x + radius * 2 / 4, center.y + 220, center.x + radius
				* 2 / 4, center.y + 220 + 5);
		g.drawLine(center.x + radius * 3 / 4, center.y + 220, center.x + radius
				* 3 / 4, center.y + 220 + 5);
		g.drawLine(center.x + radius, center.y + 220, center.x + radius,
				center.y + 220 + 5);
		// g.drawLine(center.x -radius+315*7/4, center.y + 220,
		// center.x-radius+315*7/4, center.y + 220+5);

		// g.drawLine(center.x + radius, center.y, center.x + radius - 10,
		// center.y - 7); //x轴的箭头
		// g.drawLine(center.x + radius, center.y, center.x + radius - 10,
		// center.y + 7);
		// g.drawLine(center.x, center.y - radius, center.x - 7, center.y -
		// radius + 10); //y轴的箭头
		// g.drawLine(center.x, center.y - radius, center.x + 7, center.y -
		// radius + 10);
		// String[]
		// ylabel={"M.S.-90","      -60","      -30","        0","      30","      60","M.S.90"};
		// g.drawPolyline(x, y, 2*radius+1);

		g.setColor(Color.black);
		g.setFont(new Font("ScanSerif", Font.BOLD, 11)); // 设置字体
		g.drawString("M.S.-90", center.x - radius - 40, center.y + 220);
		g.drawString(" -60", center.x - radius - 40, center.y + 220 * 2 / 3);
		g.drawString(" -30", center.x - radius - 40, center.y + 220 / 3);
		g.drawString(" 0", center.x - radius - 40, center.y);
		g.drawString(" 30", center.x - radius - 40, center.y - 220 / 3);
		g.drawString(" 60", center.x - radius - 40, center.y - 220 * 2 / 3);
		g.drawString("M.N.90", center.x - radius - 40, center.y - 220);

		File f = new File("d:/auroramatlab/N20041111");
		// System.out.println(f.getName());
		tree(f, 1);

		fileName1 = array[0].getName();

		fileName2 = array[array.length - 1].getName();
		// System.out.println(fileName2);
		timestart = fileName1.substring(10, 12) + ":"
				+ fileName1.substring(12, 14) + ":"
				+ fileName1.substring(14, 16);

		timeend = fileName2.substring(10, 12) + ":"
				+ fileName2.substring(12, 14) + ":"
				+ fileName2.substring(14, 16);// System.out.println(time);

		g.drawString(timestart, center.x - radius - 20, center.y + 235);
		g.drawString(timeend, center.x + radius - 20, center.y + 235);

		File rs = new File("d:\\auroradatabase" + "\\" + "dest" + "\\"
				+ "1234.bmp");
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(rs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g.drawImage(img1, center.x - radius, center.y - 220, radius * 2,
				220 * 2, this);
		/* 画colorbar */
		// Image image1=getImage();
		// g.drawRect(center.x+radius+5, center.y-220, 10,220*2);
		// g.drawImage(image1, center.x+radius+6, center.y-220+1,9, 220*2-1,
		// this);
		//

	}

}
