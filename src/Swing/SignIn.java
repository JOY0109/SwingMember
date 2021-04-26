package Swing;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.MemberDao;
import DB.SqlParameter;


public class SignIn extends JDialog implements ActionListener{

	JPanel p;
	JPasswordField pfPwd;
	JTextField tfid, tfName, tfbirth, tfphone;
	JRadioButton BWom, Bman;
	JTextArea taComment;

	GridBagLayout gb;
	GridBagConstraints gbc;
	
	JButton btnInsert,btnCancle,btnUpdate,btnDelete;

	public SignIn() {
		this.setModal(true);
		initConponent();
	}

	private void initConponent() {
		this.setTitle("회원가입");
		
		gb=new GridBagLayout();
		this.setLayout(gb);
		gbc=new GridBagConstraints();
		gbc.fill =GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		
		JLabel jlId=new JLabel("  아이디");
		tfid=new JTextField(20);
		gbAdd(jlId, 0, 0, 1, 1);
		gbAdd(tfid, 1, 0, 3, 1);
		
		JLabel jlPW=new JLabel(" 비밀번호");
		pfPwd =new JPasswordField(20);
		gbAdd(jlPW, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);
		
		JLabel jlName=new JLabel("이름");
		tfName = new JTextField(20);
		gbAdd(jlName, 0, 2, 1, 1);
		gbAdd(tfName, 1, 2, 3, 1);
		
		JLabel jlPhone=new JLabel(" 전화번호");
		tfphone = new JTextField(20);
		gbAdd(jlPhone, 0, 3, 1, 1);
		gbAdd(tfphone, 1, 3, 3, 1);
		
		
		//성별 패널
		JLabel lblGender=new JLabel("성별");
		BWom =new JRadioButton("여",true);
		Bman =new JRadioButton("남",true);
		ButtonGroup group=new ButtonGroup(); //선택그룹 지정 - 묶을 생성 : 2개 중 하나만 선택 
		group.add(BWom);
		group.add(Bman);
		//화면에 추가 
		JPanel pGender=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.add(BWom);
		pGender.add(Bman);
		gbAdd(lblGender, 0, 4, 1, 1);
		gbAdd(pGender, 1, 4, 3, 1);
		
		
		JLabel jlbirth=new JLabel(" 생일");
		tfbirth = new JTextField(20);
		gbAdd(jlbirth, 0, 5, 1, 1);
		gbAdd(tfbirth, 1, 5, 3, 1);
		
		JLabel jlCm =new JLabel(" 코멘트(요구사항)");
		taComment =new JTextArea(5,20);//행열
		JScrollPane pane =new JScrollPane(taComment);
		gbAdd(jlCm, 0, 6, 1, 1);
		gbAdd(taComment, 1,6, 3, 1);
		
		
		//버튼들 
		JPanel pButton=new JPanel();
		btnInsert=new JButton("가입");
		btnUpdate=new JButton("수정");
		btnDelete=new JButton("탈퇴");
		btnCancle=new JButton("취소");
		pButton.add(btnInsert);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancle);
		gbAdd(pButton, 0, 11, 4, 1);
		
		//버튼 이벤트 
		this.btnInsert.addActionListener(this);
		this.btnCancle.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnUpdate.addActionListener(this);
		
		
		this.setSize(450,350);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();
		switch (btn.getText()) {
		case "가입":
			insertMember();
			break;
		case "수정": 
			updateMember();
			break;
		case "탈퇴": 
			deletMember(); break;
		case "취소":
			this.dispose();//현재 창 닫기 
			//System.exit(-1);//프로그램 강제종료
			break;

		}
		
	}
	
	//회원 탈퇴 처리 
	private void deletMember() {
		MemberDao mDao=new MemberDao();
		SqlParameter sp=getViewData();
		mDao.deletMem(sp); ////DAO에 매소드 만들어서 연결 
		System.out.println("탈퇴 완료");
		this.dispose(); //꺼짐
		
	}

	//회원정보 수정 처리
	private void updateMember() {
		MemberDao mDao=new MemberDao();
		SqlParameter sp=getViewData();
		boolean ok= mDao.updateMem(sp); //DAO에 매소드 만들어서 연결 
		System.out.println(ok);
		if(ok) {
			System.out.println("수정됐습니다");
			this.dispose();
		}
		else System.out.println("에러");	
	}

	//회원가입 처리 
	private void insertMember() {
	 MemberDao mDao=new MemberDao();
	 SqlParameter sp=getViewData(); //화면에서 입력받기
	 mDao.insertMember(sp);
	 System.out.println("가입완료");
	 this.dispose(); //꺼짐
		
	}
	
	
	//화면에서 입력 받은 내용 전부 여기에 담기
	//아이디, 비번, 이름, 전화번호,성별,생일,요구사항
	private SqlParameter getViewData() {
		SqlParameter sp=new SqlParameter();
		
		String userid= this.tfid.getText();
		sp.setUserid(userid);
		@SuppressWarnings("deprecation")
		String userpw=this.pfPwd.getText();
		sp.setUserpw(userpw);

		String username=this.tfName.getText();
		sp.setUsername(username);
		
		String phonNum=this.tfphone.getText();
		sp.setPhone(phonNum);
		
		String gender="";
		if (BWom.isSelected()) {gender="여";}
		if (Bman.isSelected()) {gender="남";}
		sp.setGender(gender);
		
		String birth=this.tfbirth.getText();
		sp.setBirth(birth);
		
		String coment=this.taComment.getText();
		sp.setCommets(coment);
		
		return sp;
	}

	
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		this.add(c, gbc);

	}

}// class close
