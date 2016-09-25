# -
菜鸟计算器初步代码（1，功能键边界测试没,2，不能进行负数运算。）
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
 * 一个计算器的界面设置。 
 */  
public class Jisuanqi1 extends JFrame implements ActionListener {  
    /** 计算器上的键的显示名字 */  
    private final String[] KEYS = {"sin","cos","tan","ln","Cnm","asin","acos",
    		"atan","X^y","n!", "7", "8", "9","Backspace", "C", "4", "5", "6", 
    		"*", "/", "1", "2", "3","+", "-", "0",".", "="};  
      
    /** 计算器上键的按钮 */  
    private JButton keys[] = new JButton[KEYS.length];  
 
      /** 计算结果文本框 */  
   private JTextField resultText = new JTextField(15);  
  
    // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字  
    private boolean firstDigit = true;  
    // 计算的中间结果。  
    private double resultNum = 0.0;  
    // 当前运算的运算符  
    private String operator = "=";  
    // 操作是否合法  
    private boolean operateValidFlag = true;  
   
  
    /** 
     * 构造函数 
     */  
    public Jisuanqi1() {   
    	super();
        //初始化计算器
        init();  
        // 设置计算器的背景颜色  
        this.setBackground(Color.LIGHT_GRAY);  
        this.setTitle("菜鸟计算器");  
        // 在屏幕(200, 200)坐标处显示计算器  
       this.setLocation(200, 200);  
        //不允许修改计算器的大小
       // this.setResizable(false);
        // 使计算器中各组件大小合适  
        this.pack();  
    } 
  

	// 初始化计算器  的方法 
    private void init() {  

    	resultText.setFont(resultText.getFont().deriveFont(Font.BOLD,
                  (float) 32.0));
    	resultText.setEditable(false);
    	resultText.setHorizontalAlignment(JTextField.RIGHT);
    	resultText.setBackground(Color.GRAY);
    	resultText.setForeground(Color.WHITE);
        
        // 初始化计算器上键的按钮，将除0行外的键放在一个画板内  
        JPanel calckeysPanel = new JPanel();  
        // 用网格布局器，5行，5列的网格，网格之间的水平方向间隔为8个象素，垂直方向间隔为8个象素  
        calckeysPanel.setLayout(new GridLayout(5, 5, 8, 8));  
         
          
        for (int i = 0; i < 25; i++) {  
            keys[i] = new JButton(KEYS[i]);  
            calckeysPanel.add(keys[i]);  
            keys[i].setForeground(Color.blue); 
            keys[i].setFont(keys[i].getFont().deriveFont(
                    Font.BOLD, (float) 20.0));
        } 
       //将0行键放在另一个画板calckeysPanel1内
        JPanel calckeysPanel1= new JPanel();
      //用网格布局器，1行，3列的网格，网格之间的水平方向间隔为8个象素，垂直方向间隔为8个象素 
        calckeysPanel1.setLayout(new GridLayout(1, 3, 8, 8)); 
        for(int i=25;i<28;i++){
        	keys[i] = new JButton(KEYS[i]);  
            calckeysPanel1.add(keys[i]);  
            keys[i].setForeground(Color.blue); 
            keys[i].setFont(keys[i].getFont().deriveFont(
                    Font.BOLD, (float) 20.0));
        }
        // 运算符键用红色标示，其他键用蓝色表示  
        for(int i=0;i<10;i++){
        keys[i].setForeground(Color.red); } 
        keys[24].setForeground(Color.red);  
        keys[23].setForeground(Color.red);  
        keys[19].setForeground(Color.red);  
        keys[13].setForeground(Color.red);  
        keys[14].setForeground(Color.red);
        keys[18].setForeground(Color.red);
        
       
        // 下面进行计算器的整体布局 :
        // 新建一个大的画板，将上面建立的calckeysPanel和calckeysPanel1画板放在该画板内  
        JPanel panel1 = new JPanel();  
        //画板采用边界布局管理器，画板里组件之间的水平和垂直方向上间隔都为8象素  
        panel1.setLayout(new BorderLayout(8, 8));  
     
        panel1.add("North", calckeysPanel);  
        panel1.add("South",calckeysPanel1 ); 
       
        // 建立一个画板放文本框  
        JPanel top = new JPanel();  
        top.setLayout(new GridLayout());  
        top.add(resultText);  
  
        // 整体布局  
        getContentPane().setLayout(new BorderLayout(8, 8));  
        getContentPane().add("North", top);  
        getContentPane().add("South",panel1 ); 
 
         
        // 都使用同一个事件侦听器，即本对象。本类的声明中有implements ActionListener  
        for (int i = 0; i < KEYS.length; i++) {  
            keys[i].addActionListener(this);  
        }  
       
        
    }  
  
    /** 
     * 处理事件 
     */  
    public void actionPerformed(ActionEvent e) {  
        // 获取事件源的标签  
        String label = e.getActionCommand(); 
         if ("0123456789.".indexOf(label) >= 0) {  
            // 用户按了数字键或者小数点键 或者-键
            handleNumber(label);  
          }else if(label.equals(KEYS[13])){
        	  //用户按了Backspace键
        	  handleBackspace();
           } else if (label.equals(KEYS[14])) {
              // 用户按了"C"键
              handleC();
           }
         else {  
            // 用户按了运算符键:+-*/=和10种运算符。
            handleOperator(label);  
        }  
    }  
    /**
     * 处理Backspace键被按下的事件
     */
    private void handleBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            // 退格，将文本最后一个字符去掉
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // 如果文本没有了内容，则初始化计算器的各种值
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                // 显示新的文本
                resultText.setText(text);
            }
        }
    }
    /** 
     * 处理数字键被按下的事件 
     * */ 
     private void handleNumber(String key) {  
        if (firstDigit) {  
            // 输入的第一个数字  
            resultText.setText(key);  
        }
        else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {  
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面  
            resultText.setText(resultText.getText() + ".");  
        } else if (!key.equals(".")) {  
            // 如果输入的不是小数点，则将数字附在结果文本框的后面  
            resultText.setText(resultText.getText() + key);  
        }  
        // 以后输入的肯定不是第一个数字了  
        firstDigit = false;  
    }  
     /**
      * 处理C键被按下的事件
      */
     private void handleC() {
         // 初始化计算器的各种值
         resultText.setText("0");
         firstDigit = true;
         operator = "=";
     }
     /** 
     * 处理运算符键被按下的事件 
     **/  
    private void handleOperator(String key) {  
        if (operator.equals("/")) {  
            // 除法运算  
            // 如果当前结果文本框中的值等于0  
            if (getNumberFromText() == 0.0) {  
                // 操作不合法  
                operateValidFlag = false;  
                resultText.setText("除数不能为零");  
            } else {  
                resultNum /= getNumberFromText();  
            }  
        } else if (operator.equals("+")) {  
            // 加法运算  
            resultNum += getNumberFromText();  
        } else if (operator.equals("-")) {  
            // 减法运算  
            resultNum -= getNumberFromText();  
        } else if (operator.equals("*")) {  
            // 乘法运算  
            resultNum *= getNumberFromText();  
        } else if (operator.equals("C|nm")) {  
            // 组合数
        	resultNum = Cnm(resultNum,getNumberFromText());
        } else if (operator.equals("sin")) {  
            // 正弦函数运算 
            resultNum = Math.sin(getNumberFromText()*3.1415926/180);  
        } else if (operator.equals("cos")) {  
            // 余弦函数运算  
            resultNum = Math.cos(getNumberFromText()*3.1415926/180);  
        } else if (operator.equals("=")) {  
            // 赋值运算  
            resultNum = getNumberFromText();  
        } else if(operator.equals("tan")) {
        	//正切函数
             resultNum = Math.tan(getNumberFromText()*3.1415926/180);
        } else if(operator.equals("ln")){
        	//ln运算
        	 if (getNumberFromText() <= 0.0) {  
                 // 操作不合法  
                 operateValidFlag = false;  
                 resultText.setText("真数必须大于零，请清零后重新计算");  
             } else {  
                 resultNum =Math.log(getNumberFromText()); }
        } else if(operator.equals("asin")){
        	//反正弦
        	 if ((getNumberFromText() < -1.5707963)||(getNumberFromText()>1.5707963)) {  
                 // 操作不合法  
                 operateValidFlag = false;  
                 resultText.setText("asin的范围是(-π/2,π/2)，请清零后重新计算"); }
        	 else{
        	resultNum = Math.asin(getNumberFromText());}
        } else if(operator.equals("X^y")){
        	//X的y次幂
        	resultNum = Math.pow(resultNum,getNumberFromText());
        } else if(operator.equals("acos")){
        	//反余弦
        	if ((getNumberFromText() <=0)||(getNumberFromText()>3.1415927)) {  
                // 操作不合法  
                operateValidFlag = false;  
                resultText.setText("acos的范围是(0,π)，请清零后重新计算"); }
        	else{resultNum = Math.acos(getNumberFromText());}
        } else if(operator.equals("atan")){
        	//反正切
        	if ((getNumberFromText() < -1.5707963)||(getNumberFromText()>1.5707963)) {  
                // 操作不合法  
                operateValidFlag = false;  
                resultText.setText("atan的范围是(-π/2,π/2)，请清零后重新计算"); }
        	else{resultNum = Math.atan(getNumberFromText());}
        } else if(operator.equals("n!")){
        	//n的阶乘运算
          resultNum = Bf(getNumberFromText());
           
           }  
            if (operateValidFlag) {  
            // 双精度浮点数的运算  
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
        // 运算符等于用户按的按钮  
        operator = key;  
        firstDigit = true;  
        operateValidFlag = true;  
    }  
    public double  Bf(double n){   
        BigDecimal result = new BigDecimal(1);  
        BigDecimal a;  
        for(int i = 2; i <= n; i++){  
            a = new BigDecimal(i);//将i转换为BigDecimal类型  
            result = result.multiply(a);//不用result*a，因为BigDecimal类型没有定义*操作</span><span>  
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
	   z=x/y*k;
	   return z;
	      }
    /** 
     * 从结果文本框中获取数字 
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
