package newkeogram;
import java.awt.*;
import javax.swing.*;
public class MyFrame extends JFrame{
	public MyFrame() {
	    Container container = this.getContentPane();   //�൱�ڶ���һ�� ��������һ�����ڰѻ�ȡ��ֵ��������������������Ҫ�򶥲�����������JFrame)��������������������(��JPanel)������Ҫ�������������  
	    container.setLayout(new BorderLayout());  //�ö����ϱ������������ƵĲ��ַ�ʽ
	    SinPanel sinPanel = new SinPanel();
	    container.add(sinPanel, BorderLayout.CENTER); //��sinpanel����container�������

	    this.setTitle("Showkeogram");
	    this.setSize(new Dimension(1400,700));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�˳�

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //��ȡ����ʾ����Ļ��С,�ֱ��ʴ�С
	    Dimension frameSize = this.getSize();  //���ڴ�С
	    this.setLocation((screenSize.width - frameSize.width) / 2,
	                     (screenSize.height - frameSize.height) / 2);
	    this.setVisible(true);
	    
	    
	    
	    
	  }

	  public static void main(String[] args) {
	    MyFrame myFrame = new MyFrame();
	    
	  }
}
