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
	BufferedImage img;//리스트에 있는 파일의 이미지를 이미지 버퍼로 저장
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
		System.out.println("파일갯수" + piclist.size());
		
		for(int index=0;index<piclist.size();index++)
		{
			try {
			    img = ImageIO.read(piclist.get(index));
			} catch (IOException e) {
			}
			temp = (Image)img;
			forsee = new ImageIcon(temp);
			back = new ImageIcon(temp.getScaledInstance(160, 120, java.awt.Image.SCALE_FAST));//아이콘에 넣음
			createpage(index%16, index/16);
		}

		JButton previous = new JButton("\uACFC\uBAA9\uBCF4\uAE30");//과목보기
		
		previous.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		previous.setFocusPainted(false);
		previous.setBorderPainted(false);
		previous.setBounds(12, 508, 97, 30);
		previous.setBackground(new Color(220, 220, 220));
		add(previous);
		
		JButton delete = new JButton("\uC0C8\uB85C\uACE0\uCE68");//새로고침
		
		delete.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		delete.setFocusPainted(false);
		delete.setBorderPainted(false);
		delete.setBounds(688, 508, 97, 30);
		delete.setBackground(new Color(220, 220, 220));
		add(delete);
		
		JButton before = new JButton("\uC774\uC804");//이전
		before.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		before.setFocusPainted(false);
		before.setBorderPainted(false);
		before.setBounds(300, 508, 97, 30);
		before.setBackground(new Color(220, 220, 220));
		before.setEnabled(false);//첫페이지 이므로
		add(before);
		

		JButton next = new JButton("\uB2E4\uC74C");//다음
		next.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		next.setFocusPainted(false);
		next.setBorderPainted(false);
		next.setBounds(410, 508, 97, 30);
		next.setBackground(new Color(220, 220, 220));
		if(maxpage==currentpage)//페이지가 하나밖에 없으면
			next.setEnabled(false);
		add(next);
		
		//이전페이지
				before.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						remove(page.get(currentpage));
						currentpage-=1;
						add(page.get(currentpage));
						
						if(currentpage<=0)//이전페이지를 눌렀을때 처음페이지이면 비활성화
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
		//다음페이지
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(page.get(currentpage));
				currentpage+=1;
				add(page.get(currentpage));
				if(currentpage>=maxpage)
					next.setEnabled(false);
				else
					next.setEnabled(true);
				if(currentpage<=0)//이전페이지를 눌렀을때 처음페이지이면 비활성화
					before.setEnabled(false);
				else
					before.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		
		//이전버튼
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
		//삭제버튼
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
            
        List<File> resultList = new ArrayList<File>(); //이미지 파일을 저장할 리스트 생성
        
        //지정한 이미지폴더가 존재 할지 않을경우 빈 리스트 반환.
        //System.out.println("파일존재 여부: "+file.exists());
        if(!file.exists()) return resultList;
        
        File[] list = file.listFiles(new FileFilter() { //원하는 파일만 가져오기 위해 FileFilter정의
            
            String strImgExt = "jpg|jpeg"; //허용할 이미지타입
            
            @Override
            public boolean accept(File pathname) {                            
                
                //System.out.println(pathname);
                boolean chkResult = false;
                if(pathname.isFile()) {
                    String ext = pathname.getName().substring(pathname.getName().lastIndexOf(".")+1);
                    //System.out.println("확장자:"+ext);
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
                //폴더이면 이미지목록을 가져오는 현재메서드를 재귀호출
                resultList.addAll(getImgFileList(f));                 
            }else {            
                //폴더가 아니고 파일이면 리스트(resultList)에 추가
                resultList.add(f);
            }
        }            
        return resultList; 
    }
    
    public void createpage(int index, int pagei)//index = index%16, pagei = index/16
    {
    	
    	if(pagei>maxpage)//새로운 페이지일 경우
    	{
    		System.out.println("new page created");
    		JPanel newpage = new JPanel();//어레이 리스트 내 페이지
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
		//아티클 클릭이벤트
		page.get(maxpage).add(article);//마지막 페이지에 있는 패널에 사진패널 추가
    }

}
