   package Swing;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.Login;



public class BasicFrame extends JFrame{
	
	DefaultTableModel dtm;
	JTable jTable;
	JScrollPane pane;
	JPanel topPane;
	GridBagConstraints gbc;
	GridBagLayout gbl;
	JButton signUpB;
	JButton master;
	
	//회원가입, 관리자 버튼 (첫 화면 )
	public  BasicFrame() {
		super("회원관리 프로그램 첫 페이지");
		
		FirstButton(); //버튼 설정 
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,300);
		this.setVisible(true);	
	}

	
	//회원가입, 관리자 버튼 모양,이벤트 
	private void FirstButton() {
		gbl=new GridBagLayout();
		this.setLayout(gbl);
		gbc=new GridBagConstraints();
		gbc.fill =GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		
		topPane=new JPanel();
		signUpB=new JButton("회원가입/수정/ 탈퇴");
		topPane.add(signUpB);
		ButtonSize(signUpB, 0,0, 1, 1); //크기 및 위치 매소드 만들어서 적용
		SignInEvent();
		 
		topPane=new JPanel();
		master=new JButton("관리자 페이지");
		topPane.add(master);
		ButtonSize(master, 1,0, 3,1); //크기 및 위치  
		MasterEvent();
		
		//버튼 만들기 위치설정 ok 	
	}
	
	
	
	
	//회원가입 버튼 이벤트 연결 
	public void SignInEvent() {
		this.signUpB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			JButton btn= (JButton) e.getSource();
		    System.out.println(btn.getText());  //콘솔에 출력 
		    
		    //이벤트 내용 
		    new SignIn();
		    
				
			}
		});
	}// FBEvent closs

	//관리자 버튼 이벤트 연결 
	private void MasterEvent() {
		this.master.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn= (JButton) e.getSource();
			    System.out.println(btn.getText()); //콘솔에 출력 
			    
			    //이벤트 내용
			    //new MasterTable();
			    new Login();
				
			}
		});
		
	}


	//gridbag 사이즈
	private void ButtonSize(JComponent c, int x, int y, int w, int h) {

		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		gbl.setConstraints(c, gbc);
		gbc.insets=new Insets(30,50, 30,50); //여백 설정
		this.add(c,gbc);
	}

	


}
