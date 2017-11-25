import java.awt.Color;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewPic extends JPanel {


	ImageIcon back;
	ImageIcon forsee;
	Image temp;
	BufferedImage img;//����Ʈ�� �ִ� ������ �̹����� �̹��� ���۷� ����
	public List<JPanel>page=new ArrayList<JPanel>();
	JPanel firstpage = new JPanel();
	int currentpage;
	int maxpage = 0;
	
	
	public ViewPic(String path, String subject) {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 550);
		this.setVisible(true);
		setLayout(null);
		
		firstpage.setSize(800, 500);
		firstpage.setVisible(true);
		firstpage.setBackground(Color.PINK);
		firstpage.setLayout(null);
		page.add(firstpage);
		
		
		List<File> piclist = getImgFileList(path);
		System.out.println("���ϰ���" + piclist.size());
		
		for(int index=0;index<piclist.size();index++)
		{
			try {
			    img = ImageIO.read(piclist.get(index));
			} catch (IOException e) {
			}
			temp = (Image)img;
			forsee = new ImageIcon(temp);
			back = new ImageIcon(temp.getScaledInstance(160, 120, java.awt.Image.SCALE_FAST));//�����ܿ� ����
			createpage(index%16, index/16);
		}

		JButton previous = new JButton("\uACFC\uBAA9\uBCF4\uAE30");
		
		previous.setFont(new Font("���� ����", Font.PLAIN, 13));
		previous.setFocusPainted(false);
		previous.setBorderPainted(false);
		previous.setBounds(12, 508, 97, 30);
		previous.setBackground(new Color(220, 220, 220));
		add(previous);
		
		JButton delete = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		
		delete.setFont(new Font("���� ����", Font.PLAIN, 13));
		delete.setFocusPainted(false);
		delete.setBorderPainted(false);
		delete.setBounds(688, 508, 97, 30);
		delete.setBackground(new Color(220, 220, 220));
		add(delete);
		
		JButton before = new JButton("\uC774\uC804");
		before.setFont(new Font("���� ����", Font.PLAIN, 13));
		before.setFocusPainted(false);
		before.setBorderPainted(false);
		before.setBounds(300, 508, 97, 30);
		before.setBackground(new Color(220, 220, 220));
		before.setEnabled(false);//ù������ �̹Ƿ�
		add(before);
		

		JButton next = new JButton("\uB2E4\uC74C");
		next.setFont(new Font("���� ����", Font.PLAIN, 13));
		next.setFocusPainted(false);
		next.setBorderPainted(false);
		next.setBounds(410, 508, 97, 30);
		next.setBackground(new Color(220, 220, 220));
		if(maxpage==currentpage)//�������� �ϳ��ۿ� ������
			next.setEnabled(false);
		add(next);
		
		//����������
				before.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						remove(page.get(currentpage));
						currentpage-=1;
						add(page.get(currentpage));
						
						if(currentpage<=0)//������������ �������� ó���������̸� ��Ȱ��ȭ
							before.setEnabled(false);
						else
							before.setEnabled(true);
						if(currentpage>=maxpage)
							next.setEnabled(false);
						else
							next.setEnabled(true);
						revalidate();
						repaint();
					}
				});
		//����������
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(page.get(currentpage));
				currentpage+=1;
				add(page.get(currentpage));
				if(currentpage>=maxpage)
					next.setEnabled(false);
				else
					next.setEnabled(true);
				if(currentpage<=0)//������������ �������� ó���������̸� ��Ȱ��ȭ
					before.setEnabled(false);
				else
					before.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		
		//������ư
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("subject "+subject+" previous button clicked!");
				Subjects temp = new Subjects();
				removeAll();
				add(temp);
				revalidate();
				repaint();
			}
		});
		//������ư
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic refresh = new ViewPic(path, subject);
				removeAll();
				add(refresh);
				revalidate();
				repaint();
			}
		});
		add(page.get(0));
		currentpage=0;
	}
	
	
	public static List<File> getImgFileList(String path){        
        
        return getImgFileList(new File(path));
    }    

    public static List<File> getImgFileList(File file){        
            
        List<File> resultList = new ArrayList<File>(); //�̹��� ������ ������ ����Ʈ ����
        
        //������ �̹��������� ���� ���� ������� �� ����Ʈ ��ȯ.
        //System.out.println("�������� ����: "+file.exists());
        if(!file.exists()) return resultList;
        
        File[] list = file.listFiles(new FileFilter() { //���ϴ� ���ϸ� �������� ���� FileFilter����
            
            String strImgExt = "jpg|jpeg"; //����� �̹���Ÿ��
            
            @Override
            public boolean accept(File pathname) {                            
                
                //System.out.println(pathname);
                boolean chkResult = false;
                if(pathname.isFile()) {
                    String ext = pathname.getName().substring(pathname.getName().lastIndexOf(".")+1);
                    //System.out.println("Ȯ����:"+ext);
                    chkResult = strImgExt.contains(ext.toLowerCase());        
                    //System.out.println(chkResult +" "+ext.toLowerCase());
                } else {
                    chkResult = true;
                }
                return chkResult;
            }
        });        
        
        for(File f : list) {            
            if(f.isDirectory()) {
                //�����̸� �̹�������� �������� ����޼��带 ���ȣ��
                resultList.addAll(getImgFileList(f));                 
            }else {            
                //������ �ƴϰ� �����̸� ����Ʈ(resultList)�� �߰�
                resultList.add(f);
            }
        }            
        return resultList; 
    }
    
    public void createpage(int index, int pagei)//index = index%16, pagei = index/16
    {
    	
    	if(pagei>maxpage)//���ο� �������� ���
    	{
    		System.out.println("new page created");
    		JPanel newpage = new JPanel();//��� ����Ʈ �� ������
    		newpage.setSize(800, 500);
    		newpage.setVisible(true);
    		newpage.setBackground(Color.PINK);
    		newpage.setLayout(null);
    		maxpage= pagei;
    		page.add(newpage);
    	}
    	JPanel article = new JPanel();
		article.setBounds(56 + (index % 4) * 172, 10 + (index / 4) * 130, 160, 120);
		article.setVisible(true);
		JLabel picLabel = new JLabel(back);
		picLabel.getIcon();
		article.add(picLabel);
		JLabel rawpic = new JLabel(forsee);
		picLabel.getIcon();
		article.add(rawpic);
		article.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//@SuppressWarnings("unused")
				@SuppressWarnings("unused")
				Viewer popup = new Viewer((ImageIcon)rawpic.getIcon());
				System.out.println("pop up requetsted");
				/*
				JDialog dialog = new JDialog();
				dialog.setUndecorated(true);
				JLabel label = new JLabel(back);
				dialog.add( label );
				dialog.pack();
				dialog.setVisible(true);*/
				
				
			}
		});
		//��ƼŬ Ŭ���̺�Ʈ
		page.get(maxpage).add(article);//������ �������� �ִ� �гο� �����г� �߰�
    }

}