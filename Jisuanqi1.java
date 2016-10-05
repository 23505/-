package java1;
import java.awt.BorderLayout;  
import java.awt.Color;
import java.math.BigDecimal;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JTextField; 

/** 
 * һ���������Ľ������á� 
 */  
public class Jisuanqi1 extends JFrame implements ActionListener {  
    /** �������ϵļ�����ʾ���� */  
    private final String[] KEYS = {"ln","n|C|m","sin","cos","tan","X^y","n!","arcsin","arccos",
    		"arctan","C","Backspace", "7", "8", "9","+", "-", "4", "5", "6", 
    		"*", "/", "1", "2", "3","sqrt","lg", "0",".", "="};  
      
    /** �������ϼ��İ�ť */  
    private JButton keys[] = new JButton[KEYS.length];  
 
      /** �������ı��� */  
   private JTextField resultText = new JTextField(15);  
  
    // ��־�û������Ƿ����������ʽ�ĵ�һ������,�������������ĵ�һ������  
    private boolean firstDigit = true;  
    // ������м�����  
    private double resultNum = 0.0;  
    // ��ǰ����������  
    private String operator = "=";  
    // �����Ƿ�Ϸ�  
    private boolean operateValidFlag = true;  
   
  
    /** 
     * ���캯�� 
     */  
    public Jisuanqi1() {   
    	super();
        //��ʼ��������
        init();  
        // ���ü������ı�����ɫ  
        this.setBackground(Color.LIGHT_GRAY);  
        this.setTitle("���������");  
        // ����Ļ(200, 200)���괦��ʾ������  
       this.setLocation(200, 200);  
        //�������޸ļ������Ĵ�С
        this.setResizable(false);
        // ʹ�������и������С����  
        this.pack();  
    } 
  
	// ��ʼ��������  �ķ��� 
    private void init() {  

    	resultText.setFont(resultText.getFont().deriveFont(Font.BOLD,
                  (float) 32.0));
    	resultText.setEditable(false);
    	resultText.setHorizontalAlignment(JTextField.RIGHT);
    	resultText.setBackground(Color.GRAY);
    	resultText.setForeground(Color.WHITE);
        
        // ��ʼ���������ϼ��İ�ť��
        JPanel calckeysPanel = new JPanel();  
        // �����񲼾�����6�У�5�е���������֮���ˮƽ������Ϊ8�����أ���ֱ������Ϊ8������  
        calckeysPanel.setLayout(new GridLayout(6, 5, 8, 8));  
        for (int i = 0; i < 30; i++) {  
            keys[i] = new JButton(KEYS[i]);  
            calckeysPanel.add(keys[i]);  
            keys[i].setForeground(Color.blue); 
            keys[i].setBackground(Color.white);
            keys[i].setFont(keys[i].getFont().deriveFont(
                    Font.BOLD, (float) 20.0));
        } 
    
        // ��������ú�ɫ��ʾ������������ɫ��ʾ  
        for(int i=0;i<10;i++){
        keys[i].setForeground(Color.green); }
        keys[10].setForeground(Color.red);
        keys[11].setForeground(Color.red);
        keys[15].setForeground(Color.black);  
        keys[16].setForeground(Color.black);  
        keys[20].setForeground(Color.black);  
        keys[21].setForeground(Color.black);  
        keys[25].setForeground(Color.cyan);
        keys[26].setForeground(Color.cyan);
        
        // ����һ��������ı���  
        JPanel top = new JPanel();  
        top.setLayout(new BorderLayout());  
        top.add(resultText);  
  
        // ���岼��  
        getContentPane().setLayout(new BorderLayout());  
        getContentPane().add("North",top);  
        getContentPane().add("South",calckeysPanel );
        
        // ��ʹ��ͬһ���¼����������������󡣱������������implements ActionListener  
        for (int i = 0; i < KEYS.length; i++) {  
            keys[i].addActionListener(this);  
        }  
       
        
    }  
  
    /** 
     * �����¼� 
     */  
    public void actionPerformed(ActionEvent e) {  
        // ��ȡ�¼�Դ�ı�ǩ  
        String label = e.getActionCommand(); 
         if ("0123456789.".indexOf(label) >= 0) {  
            // �û��������ּ�����С����� 
            handleNumber(label);  
          }else if(label.equals(KEYS[11])){
        	  //�û�����Backspace��
        	  handleBackspace();
           } else if (label.equals(KEYS[10])) {
              // �û�����"C"��
              handleC();
           }
         else {  
            // �û������������:+-*/=��10���������
            handleOperator(label);  
        }  
    }  
    /**
     * ����Backspace�������µ��¼�
     */
    private void handleBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            // �˸񣬽��ı����һ���ַ�ȥ��
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // ����ı�û�������ݣ����ʼ���������ĸ���ֵ
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                // ��ʾ�µ��ı�
                resultText.setText(text);
            }
        }
    }
    /** 
     * �������ּ������µ��¼� 
     * */ 
     private void handleNumber(String key) {  
        if (firstDigit) {  
            // ����ĵ�һ������  
            resultText.setText(key);  
        }
        else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {  
            // �������С���㣬����֮ǰû��С���㣬��С���㸽�ڽ���ı���ĺ���  
            resultText.setText(resultText.getText() + ".");  
        } else if (!key.equals(".")) {  
            // �������Ĳ���С���㣬�����ָ��ڽ���ı���ĺ���  
            resultText.setText(resultText.getText() + key);  
        }  
        // �Ժ�����Ŀ϶����ǵ�һ��������  
        firstDigit = false;  
    }  
     /**
      * ����C�������µ��¼�
      */
     private void handleC() {
         // ��ʼ���������ĸ���ֵ
         resultText.setText("0");
         firstDigit = true;
         operator = "=";
     }
     /** 
     * ����������������µ��¼� 
     **/  
    private void handleOperator(String key) {  
        if (operator.equals("/")) {  
            // ��������  
            // �����ǰ����ı����е�ֵ����0  
            if (getNumberFromText() == 0.0) {  
                // �������Ϸ�  
                operateValidFlag = false;  
                resultText.setText("��������Ϊ��");  
            } else {  
                resultNum /= getNumberFromText();  
            }  
        } else if(operator.equals("sqrt")){
        	if(getNumberFromText()<0.0){
        		operateValidFlag = false;  
                resultText.setText("����û��ƽ����");	
        	}else{
        		resultNum=Math.sqrt(getNumberFromText());
        	}
        }else if(operator.equals("lg")){
        	if (getNumberFromText() <= 0.0) {  
                // �������Ϸ�  
                operateValidFlag = false;  
                resultText.setText("������������㣬����������¼���");  
            } else {  
                resultNum =Math.log10(getNumberFromText()); }
        }
        else if (operator.equals("+")) {  
            // �ӷ�����  
            resultNum += getNumberFromText();  
        } else if (operator.equals("-")) {  
            // ��������  
            resultNum -= getNumberFromText();  
        } else if (operator.equals("*")) {  
            // �˷�����  
            resultNum *= getNumberFromText();  
        } else if (operator.equals("n|C|m")) {  
            // �����
        	resultNum = Cnm(resultNum,getNumberFromText());
        } else if (operator.equals("sin")) {  
            // ���Һ������� 
        	if(getNumberFromText()==180||getNumberFromText()==-180){
        		resultNum =0.0;
        	}else if(getNumberFromText()>180||getNumberFromText()<-180){
        		operateValidFlag = false;
        		resultText.setText("sin�ļ��㷶Χ��-�е���");
        	}else{
            resultNum = Math.sin(getNumberFromText()*Math.PI/180); } 
        } else if (operator.equals("cos")) {  
            // ���Һ�������  
        	double x;
        	x=Math.toRadians(getNumberFromText());
        	if(getNumberFromText()==90||getNumberFromText()==-90){
        		resultNum=0.0;
        	}else if(getNumberFromText()>180||getNumberFromText()<-180){
        		operateValidFlag = false;
        		resultText.setText("cos�ļ��㷶Χ��-�е���");	}
        	else {
            resultNum = Math.cos(x);  }
        } else if (operator.equals("=")) {  
            // ��ֵ����  
            resultNum = getNumberFromText();  
        } else if(operator.equals("tan")) {
        	//���к���
        	if(getNumberFromText()==90||getNumberFromText()==-90){
        		operateValidFlag = false;
        		resultText.setText("�ò���������ֵ������");}
            else if(getNumberFromText()==180||getNumberFromText()==-180){
            	resultNum =0.0;
        	}else if(getNumberFromText()>180||getNumberFromText()<-180){
        		operateValidFlag = false;
        		resultText.setText("tan�ļ��㷶Χ��-�е���");	}
        	else{
             resultNum = Math.tan(getNumberFromText()*Math.PI/180);}
        } else if(operator.equals("ln")){
        	//ln����
        	 if (getNumberFromText() <= 0.0) {  
                 // �������Ϸ�  
                 operateValidFlag = false;  
                 resultText.setText("������������㣬����������¼���");  
             } else {  
                 resultNum =Math.log(getNumberFromText()); }
        } else if(operator.equals("arcsin")){
        	//������
        	 if ((getNumberFromText() < -1.0)||(getNumberFromText()>1.0)) {  
                 // �������Ϸ�  
                 operateValidFlag = false;  
                 resultText.setText("arcsin�ķ���ֵ��Χ��(-��/2,��/2)������������¼���"); }
        	 else{
        	resultNum = (Math.asin(getNumberFromText())*180/Math.PI);}
        } else if(operator.equals("X^y")){
        	//X��y����
        	resultNum = Math.pow(resultNum,getNumberFromText());
        } else if(operator.equals("arccos")){
        	//������
        	if ((getNumberFromText() <-1)||(getNumberFromText()>1)) {  
                // �������Ϸ�  
                operateValidFlag = false;  
                resultText.setText("arccos�ķ���ֵ��Χ��(0,��)������������¼���"); }
        	else{resultNum = (Math.acos(getNumberFromText())*180/Math.PI);}
        } else if(operator.equals("arc"
        		+ "tan")){
        	//������
        	resultNum = (Math.atan(getNumberFromText())*180/Math.PI);
        } else if(operator.equals("n!")){
        	//n�Ľ׳�����
          resultNum = Bf(getNumberFromText());
           
           }  
            if (operateValidFlag) {  
            // ˫���ȸ�����������  
            long t1;  
            double t2;  
            t1 = (long) resultNum;  
            t2 = resultNum - t1;  
            if (t2 == 0) {  
                resultText.setText(String.valueOf(t1));  
            } else {  
                resultText.setText(String.valueOf(resultNum));  
            }  
        }  
        // ����������û����İ�ť  
        operator = key;  
        firstDigit = true;  
        operateValidFlag = true;  
    }  
    public double  Bf(double n){   
        BigDecimal result = new BigDecimal(1);  
        BigDecimal a;  
        for(int i = 2; i <= n; i++){  
            a = new BigDecimal(i);//��iת��ΪBigDecimal����  
            result = result.multiply(a);//����result*a����ΪBigDecimal����û�ж���*����</span><span>  
        } 
        double result1;
        result1=result.doubleValue();
        return result1;
    }  
   public double Cnm(double n,double m){
	   double x,y,z,k;
	   x=Bf(n);
	   y=Bf(m);
	   k=Bf(n-m);
	   z=x/(y*k);
	   return z;
	      }
    /** 
     * �ӽ���ı����л�ȡ���� 
     **/  
    private double getNumberFromText() {  
        double result = 0;  
        try {  
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {  
        }
        return result;  
    }  
  
    public static void main(String args[]) {  
        Jisuanqi1 c = new Jisuanqi1();  
        c.setVisible(true);  
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
}  
