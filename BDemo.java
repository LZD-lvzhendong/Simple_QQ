import java.util.Date;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*; 
import java.io.*; 
import java.net.*;
import java.text.SimpleDateFormat;  

public class BDemo extends JFrame implements ActionListener,Runnable{    
 JButton cancel=new JButton("取消");  
 JButton submit=new JButton("发送");  
 JTextArea text1=new JTextArea(); 
 JTextArea text2=new JTextArea(); 
 JTextArea text3=new JTextArea("B：");
 JTextArea text4=new JTextArea();  
 String a="";  
 static String x=""; 
 String t=""; 
 static int press=0;   

public BDemo() {  
 this.setLayout(null);   
 text1.setBackground(Color.white);  
 text1.setForeground(Color.black);  
 text2.setBackground(Color.white);  
 text2.setForeground(Color.red);  
 text4.setBackground(Color.green);  
 text3.setForeground(Color.BLUE);  
 JLabel label=new JLabel(new ImageIcon("image1/a.jpg"));      
 text3.setBounds(10,5,40,15);   
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
 BDemo cd=new BDemo();   
 cd.Server();
} 

public void actionPerformed(ActionEvent e) {  
 if(e.getSource()==cancel){   
 text2.setText("");   
}   
 if(e.getSource()==submit){  
   if(text2.getText().length()>0){    
   a= "B说:"+text2.getText();    
   Date nowTime = new Date();     
   SimpleDateFormat matter = new SimpleDateFormat("MM月dd日  HH:mm:ss");     
   t=matter.format(nowTime);     
   x=x+"\n"+a+"      ("+t+")";   
   text1.setText(x);    
   text2.setText("");     
   press=1;   
}   
}  
} 

public void Server() {   
 new Thread(this).start();  
 try {     
  Socket sk=new Socket("localhost",9203);    
  InputStream in=sk.getInputStream();    
  InputStreamReader is=new InputStreamReader(in);     
  BufferedReader br=new BufferedReader(is); 
  while(true){ 
  String x1=br.readLine();     
  Date nowTime = new Date();     
  SimpleDateFormat matter = new SimpleDateFormat("MM月dd日  HH:mm:ss");      
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

public void run() {   
 try {    
 Socket sk=new Socket("localhost",9204);  
 OutputStream out=sk.getOutputStream();   
 PrintStream pr=new PrintStream(out,true);
 BufferedReader br=new BufferedReader(new InputStreamReader  (System.in));  
 while (true) {     
 if(press==1&&a!=""){     
  pr.println(a);      
  press=0;    
 }   
 }  
 } 
 catch (Exception e) {     
  e.printStackTrace();   
}  
} 
}  
