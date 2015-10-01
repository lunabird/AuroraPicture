package newkeogram;
import java.awt.*;
import javax.swing.*;
public class MyFrame extends JFrame{
	public MyFrame() {
	    Container container = this.getContentPane();   //相当于定义一个 顶级容器一样，在把获取的值，附给顶级容器。对于要向顶层容器（例如JFrame)上添加其他组件或者容器(如JPanel)，就需要调用这个方法。  
	    container.setLayout(new BorderLayout());  //用东西南北和中央来控制的布局方式
	    SinPanel sinPanel = new SinPanel();
	    container.add(sinPanel, BorderLayout.CENTER); //将sinpanel放在container面板中央

	    this.setTitle("Showkeogram");
	    this.setSize(new Dimension(1400,700));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //退出

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //获取主显示器屏幕大小,分辨率大小
	    Dimension frameSize = this.getSize();  //窗口大小
	    this.setLocation((screenSize.width - frameSize.width) / 2,
	                     (screenSize.height - frameSize.height) / 2);
	    this.setVisible(true);
	    
	    
	    
	    
	  }

	  public static void main(String[] args) {
	    MyFrame myFrame = new MyFrame();
	    
	  }
}
