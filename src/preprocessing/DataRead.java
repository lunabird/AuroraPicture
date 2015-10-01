/**
 * ��ȡԭIMG��ʽ��ͼ������
 */
package preprocessing;


import java.io.RandomAccessFile;



public class DataRead {
	public static int[][] GetData(String FileName) throws Exception {
		RandomAccessFile rFile = new RandomAccessFile(FileName, "r");  //�����ȡ��
		
		int[][] result = new int[512][512];
		long point = rFile.length() - 524288;  //524288=512*512*2������ָ����ļ���β512*512*2�ֽڴ���Ȼ���ȡһ��512x512�ľ���
		rFile.seek(point);  // file.seek(int���͵Ĳ���)��ʾ���ļ��ĵڼ���λ�ÿ�ʼ����
	    for(int i=0;i<512;i++)
	    	for(int j=0;j<512;j++)
	    	{
	    		result[i][j]=getByte(rFile.readByte(),rFile.readByte());  //��ȡ�ֽ���Ϣ
	    		//System.out.println(result[i][j]);
	    	}
	
		rFile.close();//�ر���
		return result;
		
	}
	
	public static int getByte(byte a,byte b){
		return (int) ((b& 0xFF)*256+ (a & 0xFF));	 //byte��ת����int��
		
	}
	
}
