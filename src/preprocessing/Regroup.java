package preprocessing;

/**
 * 重组类，解析原始图像格式，获取图像元数据。
 *
 * Created on 2008年7月10日, 下午12:58
 */
import java.io.*;

public class Regroup {

	static String date;
	static String time;
	static String hour;
	static String waveband;

	// static int count ;

	// 读取文件的头信息

	public static String ReadPreInfo(String filename) {
		try {
			FileReader fr = new FileReader("d:\\auroramatlab\\N20061227G"
					+ "\\" + filename); // 源文件
			BufferedReader br = new BufferedReader(fr);
			String read = br.readLine();
			// 判断是否采用了Comment工作模式
			br.skip(3 * read.length()); // 跳过3行，读第5行
			String read1 = br.readLine();
			// System.out.println(read1);
			int index_comment = read1.indexOf("[Comment]");

			int index = read.indexOf("Date"); // 获取时间、日期等信息
			date = read.substring(index + 6, index + 10)
					+ read.substring(index + 11, index + 13)
					+ read.substring(index + 14, index + 16);
			if (index_comment == -1) {
				int index1 = read.indexOf("Time");
				time = read.substring(index1 + 6, index1 + 8)
						+ read.substring(index1 + 9, index1 + 11)
						+ read.substring(index1 + 12, index1 + 14);
				hour = read.substring(index1 + 6, index1 + 8);
			} else {
				time = read1.substring(index_comment + 34, index_comment + 36)
						+ read1.substring(index_comment + 37,
								index_comment + 39)
						+ read1.substring(index_comment + 40,
								index_comment + 42);
				hour = read1.substring(index_comment + 34, index_comment + 36);
			}
			fr.close();
			br.close();
		}

		catch (IOException ie) {
			System.err.println(ie.getMessage());
		}
		return date + time;

	}

	// 创建文件夹，即新路径
	public static void createDir(String path) {

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

	}

	public static void StringPreBuffer() throws IOException // 生成info文档
	{
		String name = "N" + date + waveband + time + ".bmp";
		File file = new File("d:\\auroramatlab" + "\\info.txt");
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, true);
		StringBuffer sb = new StringBuffer();
		sb.append(name + "," + "1" + "," + "d:\\auroramatlab" + "\\\\\\\\"
				+ "N" + Regroup.date + "\\\\\\\\" + Regroup.hour + "\\\\\\\\"
				+ Regroup.waveband + "\\\\\\\\" + name + "," + Regroup.date
				+ "," + Regroup.time + "," + Regroup.waveband + ","
				+ "d:\\auroramatlab" + "\\\\\\\\" + "N" + Regroup.date
				+ "\\\\\\\\" + Regroup.hour + "\\\\\\\\" + Regroup.waveband
				+ "\\\\\\\\" + "mini" + name + "," + " " + "\r\n");

		out.write(sb.toString().getBytes("utf-8"));
		out.close();
	}

}
