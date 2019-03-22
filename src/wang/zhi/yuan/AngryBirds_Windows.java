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
	
	private JPanel backpane = null;							//��������	
	private CardLayout layout = null;						//���ַ�ʽ
	private AngryBirds_Lodin p_1 = null;
	private AngryBirds_Chosen p_2 = null;
	private AngryBirds_Plays p_3 = null; 					
	private AngryBirds_Victory p_4 = null; 					//��Ҫ�л��Ľ���
	private JPanel p_5 = null;								//�հ׽���
	private AngryBirds_Mouse mouse;							//�������
	
	public AngryBirds_Windows() throws IOException
	{
		super("Angry Birds");
		layout = new CardLayout();							//��Ƭ�����޼��
		backpane = new JPanel(layout); 						// JPanel�Ĳ��ֹ��������ó�CardLayout
		
		p_1 = new AngryBirds_Lodin(false);
		p_2 = new AngryBirds_Chosen(false);
		p_3 = new AngryBirds_Plays(false);
		p_4 = new AngryBirds_Victory(false);
		p_5 = new JPanel();
		
		/**��������*/
		backpane.add(p_1,"p1");								
		backpane.add(p_2,"p2");	
		backpane.add(p_3,"p3");	
		backpane.add(p_4,"p4");
		backpane.add(p_5,"blank");
		
        this.getContentPane().add(backpane);					//���ر�������
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//����Ĭ�Ϲر��¼�
        this.setSize(1600, 900);								//����Ĭ�ϴ�С
        this.setResizable(false);								//���ô��ڲ��ɸ���
        this.setLocationRelativeTo(null);						//���ô��ھ���
        this.setVisible(true);									//���ô��ڿɼ�	
        
    	/**�������*/
    	java.net.URL classUrl = this.getClass().getResource("");  			
        Image imageCursor = Toolkit.getDefaultToolkit().getImage(classUrl);  
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageCursor,new Point(0, 0), "cursor"));  
	}
	
	/**���ü��ؽ����¼�
	 * 
	 * �ȼ��ص�һ��������¼�����
	 * �����̼߳����¼��������������л����ڶ������棬���صڶ��������¼�
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
