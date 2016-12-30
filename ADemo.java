import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat; 
import javax.swing.*; 

public class ADemo extends JFrame implements
 ActionListener,Runnable{  
  JButton cancel=new JButton("取消");  
  JButton submit=new JButton("发送");  
  JTextArea text1=new JTextArea(); 
  JTextArea text2=new JTextArea(); 
  JTextArea text4=new JTextArea();  
  JTextArea text3=new JTextArea("A："); 
  
  String a=""; 
  static int press=0;        
  static String x="";       
  String t=""; 

public ADemo() {  
 this.setLayout(null);   
 text1.setBackground(Color.white);  
 text1.setForeground(Color.black); 
 text2.setBackground(Color.white); 
 text2.setForeground(Color.black);  
 text4.setBackground(Color.red); 
 text3.setForeground(Color.BLUE); 
 JLabel label=new JLabel(new ImageIcon("image2/b.jpg"));      
 text3.setBounds(10,5,50,15);  
 text2.setBounds(5,250,290,120); 
 text1.setBounds(5,25,290,215);  
 text4.setBounds(1, 244, 298, 2);   
 cancel.setBounds(60,380,60,30);   
 submit.setBounds(180,380,60,30);   
 label.setBounds(303,2,194,456);       
 text1.setEditable(false); 
 text3.setEditable(false); 
 cancel.addActionListener(this); 
 submit.addActionListener(this); 
 this.add(text3);
 this.add(text2); 
 this.add(text1); 
 this.add(text4); 
 this.add(label); 
 this.add(cancel); 
 this.add(submit); 
 this.setSize(490,460); 
 this.setLocationRelativeTo(null); 
 this.setVisible(true); 
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
} 

public static void main(String[] args) { 
 ADemo sd=new ADemo(); 
 sd.Server(); 
} 
public void actionPerformed(ActionEvent e) { 
 if(e.getSource()==cancel){ 
 text2.setText(""); 
} 
 if(e.getSource()==submit){ 
  if(text2.getText().length()>0){ 
  Date nowTime = new Date(); 
  SimpleDateFormat matter = new SimpleDateFormat("MM月dd月HH:mm:ss"); 
  t=matter.format(nowTime); 
  a="A说: "+text2.getText(); 
  x=x+"\n"+a+"("+t+")"; 
  text1.setText(x); 
  text2.setText(""); 
  press=1; 
}
} 
} 

public void Server() { 
 new Thread(this).start(); 
 try { 
 ServerSocket ss=new ServerSocket(9203); 
 Socket s=ss.accept(); 
 OutputStream out=s.getOutputStream();  
 PrintStream pr=new PrintStream(out,true); 
 while (true) { 
  if(press==1&& a!=""){ 
  pr.println(a); 
  press=0; 
} 
} 
} 
 catch (Exception e) { 
  e.printStackTrace(); 
} 
} 

public void run() { 
 try { 
 ServerSocket sk=new ServerSocket(9204); 
 Socket s=sk.accept(); 
 InputStream in=s.getInputStream(); 
 InputStreamReader is=new InputStreamReader(in); 
 BufferedReader br=new BufferedReader(is); 
 while(true){ 
  String x1=br.readLine(); 
  Date nowTime = new Date(); 
  SimpleDateFormat 
  matter = new SimpleDateFormat("MM月dd月HH:mm:ss"); 
  t=matter.format(nowTime); 
  x=x+"\n"+x1+"("+t+")"; 
  text1.setText(x); 
  text2.setText(""); 
} 
}
catch (Exception e) { 
 e.printStackTrace(); 
} 
} 
} 
