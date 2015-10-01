/**
 * 读取原IMG格式的图像数据
 */
package preprocessing;


import java.io.RandomAccessFile;



public class DataRead {
	public static int[][] GetData(String FileName) throws Exception {
		RandomAccessFile rFile = new RandomAccessFile(FileName, "r");  //随机读取流
		
		int[][] result = new int[512][512];
		long point = rFile.length() - 524288;  //524288=512*512*2，设置指针距文件结尾512*512*2字节处，然后读取一个512x512的矩阵
		rFile.seek(point);  // file.seek(int类型的参数)表示从文件的第几个位置开始搜索
	    for(int i=0;i<512;i++)
	    	for(int j=0;j<512;j++)
	    	{
	    		result[i][j]=getByte(rFile.readByte(),rFile.readByte());  //获取字节信息
	    		//System.out.println(result[i][j]);
	    	}
	
		rFile.close();//关闭流
		return result;
		
	}
	
	public static int getByte(byte a,byte b){
		return (int) ((b& 0xFF)*256+ (a & 0xFF));	 //byte型转换成int型
		
	}
	
}
