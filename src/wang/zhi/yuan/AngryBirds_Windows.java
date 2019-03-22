package wang.zhi.yuan;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AngryBirds_Windows extends JFrame{
	
	private JPanel backpane = null;							//背景界面	
	private CardLayout layout = null;						//布局方式
	private AngryBirds_Lodin p_1 = null;
	private AngryBirds_Chosen p_2 = null;
	private AngryBirds_Plays p_3 = null; 					
	private AngryBirds_Victory p_4 = null; 					//需要切换的界面
	private JPanel p_5 = null;								//空白界面
	private AngryBirds_Mouse mouse;							//加载鼠标
	
	public AngryBirds_Windows() throws IOException
	{
		super("Angry Birds");
		layout = new CardLayout();							//卡片布局无间距
		backpane = new JPanel(layout); 						// JPanel的布局管理将被设置成CardLayout
		
		p_1 = new AngryBirds_Lodin(false);
		p_2 = new AngryBirds_Chosen(false);
		p_3 = new AngryBirds_Plays(false);
		p_4 = new AngryBirds_Victory(false);
		p_5 = new JPanel();
		
		/**两个界面*/
		backpane.add(p_1,"p1");								
		backpane.add(p_2,"p2");	
		backpane.add(p_3,"p3");	
		backpane.add(p_4,"p4");
		backpane.add(p_5,"blank");
		
        this.getContentPane().add(backpane);					//加载背景界面
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//设置默认关闭事件
        this.setSize(1600, 900);								//设置默认大小
        this.setResizable(false);								//设置窗口不可更改
        this.setLocationRelativeTo(null);						//设置窗口居中
        this.setVisible(true);									//设置窗口可见	
        
    	/**隐藏鼠标*/
    	java.net.URL classUrl = this.getClass().getResource("");  			
        Image imageCursor = Toolkit.getDefaultToolkit().getImage(classUrl);  
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageCursor,new Point(0, 0), "cursor"));  
	}
	
	/**设置加载界面事件
	 * 
	 * 先加载第一个界面的事件监听
	 * 开启线程监听事件触发，触发后切换到第二个界面，加载第二个界面事件
	 * @throws IOException 
	 * 
	 * */
	public void action() throws InterruptedException, IOException
	{
		p_1.run = true;
		String change = p_1.action();
		while(true)
		{
			//System.out.println(change);
			switch(change)
			{
			case "p_1":
				layout.show(backpane, "p1");
				p_1.run = true;
				change = p_1.action();
			break;
			
			case "p_2":
				//System.out.println("1");
				layout.show(backpane, "p2");
				p_2.run = true;
				change = p_2.action();
			break;
			
			case "p_3":	
				p_3.run = true;
				layout.show(backpane, "p3");
				change = p_3.action();
				layout.show(backpane, "blank");
				backpane.remove(p_3);
				p_3 = new AngryBirds_Plays(false);
				backpane.add(p_3,"p3");	
			break;
			
			case "p_4":
				layout.show(backpane, "p4");
				p_4.run = true;
				change = p_4.action();
			break;
		
			}
			
			Thread.sleep(1);
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		AngryBirds_Windows w = new AngryBirds_Windows();
		w.action();
	}
}
