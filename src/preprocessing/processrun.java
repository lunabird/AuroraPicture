package preprocessing;


/**
 * ���顢Ԥ���������߳�
 */



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.awt.geom.AffineTransform;
//import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;



public class processrun implements Runnable
{
	
	
	public static int noiseR=1137,noiseG=546,noiseV=594,LimRayleighR=4000,LimRayleighG=4000,LimRayleighV=4000,Rx0=256,Ry0=257,Gx0=261,Gy0=257,Vx0=257,Vy0=255;
    public static float RK= (float) 0.5159,GK=(float) 1.0909,VK=(float) 1.5280,rotatedegree=(float) -61.1;
    private  String savetype=".bmp";
    private static File[] array;
   
   
    private static void tree(File f, int level) {       //�ݹ��ȡĿ¼�е������ļ���
    	  
    	  String preStr = "";
    	  for(int i=0; i<level; i++) {
    	   preStr += "    ";
    	  }
    	  
    	 array = f.listFiles();
    	  for(int i=0; i<array.length; i++) {
    	   System.out.println(preStr + array[i].getName()); //�ļ�������array������
    	  if(array[i].isDirectory()) {
    	    tree(array[i], level + 1);
    	   }
    	  }
    }
    	 
    	
   
	
	public void run() {
		
		float I, K=0.0f;

		String fileName;
		int noise=0,LimRayleigh=1,x0=0,y0=0;
		int r=220;
		
		for(int k=0;k<array.length;k++)
		{
			BufferedImage bi = new BufferedImage(512, 512,BufferedImage.TYPE_INT_RGB);   //���ڴ�������512x512��ͼ�񻺳�����TYPE_INT_RGB��ʾһ��ͼ�񣬸�ͼ������������ص� 8 λ RGB ��ɫ
			
			Graphics g = bi.getGraphics();
			
			
			fileName=array[k].getName();    //��ȡ�ļ���
		
			if(fileName.charAt(0) == 'E' && fileName.charAt(1) == 'N' ){
				if (fileName.charAt(8) == 'R') {   //�ж��ļ�������һ������
					
					noise = noiseR;
					LimRayleigh = LimRayleighR;					
					x0=Rx0;
					y0=Ry0;
					K = RK;
				} else if (fileName.charAt(8) == 'G') {
					noise = noiseG;
					LimRayleigh = LimRayleighG;					
					x0=Gx0;
					y0=Gy0;
					K = GK;
				} else if (fileName.charAt(8) == 'V') {
					noise = noiseV;
					LimRayleigh =LimRayleighV;					
					x0=Vx0;
					y0=Vy0;
					K = VK;
				}
			}
				
				else{	
				if (fileName.charAt(7) == 'R') {   //�ж��ļ�������һ������
					
					noise = noiseR;
					LimRayleigh = LimRayleighR;					
					x0=Rx0;
					y0=Ry0;
					K = RK;
				} else if (fileName.charAt(7) == 'G') {
					noise = noiseG;
					
					LimRayleigh = LimRayleighG;					
					x0=Gx0;
					y0=Gy0;
					K = GK;
				} else if (fileName.charAt(7) == 'V') {
					noise = noiseV;
					LimRayleigh =LimRayleighV;					
					x0=Vx0;
					y0=Vy0;
					K = VK;
				}
				}
			
				try {
					int result[][] = DataRead.GetData("d:\\auroramatlab\\N20061227G"+"\\"+fileName);   //����img��ʽ�ļ�����
					
					for (int i= 0; i <= 511; i++){
						for (int j = 0; j <= 511; j++) 
						{											
							int p =result[j][i];  //���ж����ļ��ֽڵ�ת��						
							I =(p-noise)*K;
							I = (I/LimRayleigh);
							
							if(Math.pow((j-y0),2)+Math.pow((i-x0),2)>=Math.pow(r,2)) //�뾶rԲ�����������������ɫ
							I=0;
							else
							{if(I<0)
								I=0;
							if(I>1)
								I=1;
							}
							
							
							
							g.setColor(new Color(I, I, I));   //�˴�Ϊѡ�򻭵����ɫ��R=G=B=0Ϊ��ɫ��R=G=B=1Ϊ��ɫ
							g.drawLine(i,j , i, j);    //�ڣ�i,j�������������ɫ����
																																
				}
					
				
				
				
					}		
		}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedImage img =new BufferedImage(512 ,512,BufferedImage.TYPE_INT_RGB); //��ͼ�����-61.1�ȵ���ʱ����ת
				BufferedImage img1 =new BufferedImage(440 ,440,BufferedImage.TYPE_INT_RGB);
				AffineTransform transform = new AffineTransform ();
				//transform.translate(x0, y0); 
			     transform.rotate(rotatedegree*Math.PI/180,x0,y0);
			     //transform.translate(-x0, -y0); 
			     
				
			        AffineTransformOp op = new AffineTransformOp(transform,null);
			        op.filter(bi, img); 
			        img1=img.getSubimage( x0-r,  y0-r,  440,  440);
			        
			        bi.flush();
			        img.flush();
			         Regroup.waveband =  fileName.substring(7,8);   //���飬����ͼƬ�Ĳ���
		             Regroup.ReadPreInfo(fileName);
		             try {
						Regroup.StringPreBuffer();  //����info�ĵ����洢�ļ�·�������Ե���Ϣ
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 			 Regroup.createDir("d:\\auroramatlab"+"\\"+"N"+Regroup.date);  //�����µ��ļ��е����ֺ�·��
		 		     Regroup.createDir("d:\\auroramatlab"+"\\"+"N"+Regroup.date+"\\"+Regroup.hour);
		 		     Regroup.createDir("d:\\auroramatlab"+"\\"+"N"+Regroup.date+"\\"+Regroup.hour+"\\"+Regroup.waveband);
		 		     fileName = fileName.replace(".img", "");
		 		     File file1 = new File("d:\\auroramatlab"+"\\"+"N"+Regroup.date+"\\"+Regroup.hour+"\\"+Regroup.waveband+"\\"+"N"+Regroup.date+Regroup.waveband+Regroup.time+savetype);
		 		     if (!file1.exists())
						try {
						file1.createNewFile();
						ImageIO.write(img1, "bmp", file1);   //��������ͼƬдΪbmp��ʽ��������ͼƬ������·��
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						
						Miniature.saveImageAsJpg("d:\\auroramatlab"+"\\"+"N"+Regroup.date+"\\"+Regroup.hour+"\\"+Regroup.waveband+"\\"+"N"+Regroup.date+Regroup.waveband+Regroup.time+savetype,"d:\\auroramatlab"+"\\"+"mini"+"\\"+"mini"+"N"+Regroup.date+Regroup.waveband+Regroup.time+savetype,128,128);//����128x128��������ͼ
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
	}
	        
		
	}
	
	
	

	public static void main(String[] args) throws IOException{  //���������г������
		File f = new File("d:/auroramatlab/N20061227G");
		System.out.println(f.getName());
		tree(f, 1);
		processrun rs = new processrun();

		rs.run();
		System.out.println("done");
		/* 
		 * ��bmpͼƬֱ����ʾ����
		 * final String fileName ="d:/auroramatlab/N20061207/03/G/N20061207G030220.bmp"; //������ĳ����Լ���bmpͼƬ��·��
	        
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run(){
	                new ShowBmp(fileName).setVisible(true);
	            }
	        });*/
        
	}
}


