package hw3;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class s20141362hw3 extends Frame implements ActionListener  {
	
	Label t1,t2;
	Panel p1,p2,p3,p4;
	
	Calculator cal;

	String print="0";
	String result="0";

	public s20141362hw3(String str)
	{
		super(str);
		
		cal=new Calculator();
		
		setSize(640,380);
		setLayout(new GridLayout(6,1));

		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();

		p1.setLayout(new GridLayout(1,5));
		p2.setLayout(new GridLayout(1,5));
		p3.setLayout(new GridLayout(1,5));
		p4.setLayout(new GridLayout(1,5));
		
		t1 = new Label();
		Font f= new Font("돋움체",Font.PLAIN,30);
		t1.setFont(f);
		t1.setText("Calculate table");
		
		t2 = new Label();
		t2.setFont(f);
		t2.setText("Result table");
		
		
		AddButton("7",p1);
		AddButton("8",p1);
		AddButton("9",p1);
		AddButton("/",p1);
		AddButton("C",p1);
		
		AddButton("4",p2);
		AddButton("5",p2);
		AddButton("6",p2);
		AddButton("*",p2);
		AddButton("<-",p2);
		
		AddButton("1",p3);
		AddButton("2",p3);
		AddButton("3",p3);
		AddButton("-",p3);
		AddButton("(",p3);
		
		AddButton("0",p4);
		AddButton(".",p4);
		AddButton("=",p4);
		AddButton("+",p4);
		AddButton(")",p4);
		
		add(t1);
		add(t2);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		
		WindowDestroyer listener= new WindowDestroyer();
		addWindowListener(listener);
		setVisible(true);
	}
	
	void AddButton(String text, Panel p)
	{
		Button b= new Button(text);
		b.addActionListener(this);
		p.add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String t=e.getActionCommand();
		
		cal.input(t);
		
		
		if(t=="=") {
			result=cal.calcul();
		}
		else if(t=="C") {
			print="0";
			result="0";
		}
		else if(t=="<-") {
			if(print.length()!=0) {
				print=print.substring(0,print.length()-1);
			}
		}
		else {
			if(print=="0")
				print=t;
			else
				print=print+t;
		}

		t1.setText(print);
		t2.setText(result);
	}
	public static void main(String[] args)
	{
		s20141362hw3 calculator = new s20141362hw3("Calculator");
	}
}
class Calculator {
	double input;
	double output;
	
	char op;
	char[] oplist= {'+','-','*','/'};

	enum State{OP,NUM,DOT}
	State state;
	String print;
	DecimalFormat formatter;
	boolean dotflag=false;
	
	String forleng;
	
	Calculator(){
		state = State.NUM;
		formatter=new DecimalFormat("#,###.##########");
		output=0;
		input=0;
		print="0";
		op=0;
	}
	void input(String t){
		char nowop=0;
		boolean isnowop=false;
		
		for(int i=0;i<oplist.length;i++)
			if(t.equals(String.valueOf(oplist[i]))) {
				isnowop=true;
				nowop=oplist[i];
				dotflag=false;
				break;
			}
		if(isnowop) {
			switch(state) {
			case OP:
				op=nowop;
				break;
			default:
				if(op==0) {
					op=nowop;
					output=input;
					input=0;
					state=State.OP;
				}
				else {
					doop(op);
					input=0;
					state=State.OP;
				}
				break;
			}
		}
		else if(t.equals("=")) {
			if(op !=0)
				doop(op);
			else
				output=input;
			state=State.OP;
			input=0;
			print=formatter.format(output);
			return;
		}
		else if(t.equals("<-")) {
			switch(state) {
			case OP:
				op=0;
				input=output;
				output=0;
				state=State.NUM;
				break;
			case DOT:
				dotflag=false;
				input=(int)(input);
				state=State.NUM;
				break;
			case NUM:
				if(dotflag==true) {
					forleng=String.valueOf(input);
					forleng=forleng.substring(0,print.length()-1);
					input=Double.valueOf(forleng);
				}
				else if(input>10)
					input=(int)(input/10);
				else
					input=0;
			}
		}
		else if(t.equals("C")) {
			input=0;
			output=0;
			state=State.NUM;
		}
		else if(t.equals(".")) {
			if(state!=State.DOT) {
				dotflag=true;
				if(state==State.OP) {
					doop(op);
					input=0;
				}
			}
			state=State.DOT;
		}
		else {
			if(dotflag==false) {
				if(input==0)
					input=Integer.valueOf(t);
				else
					input=input*10+Integer.valueOf(t);
			}
			else
			{
				if(state==State.DOT) {
					forleng=String.valueOf(input);
					forleng=forleng+"."+t;
					input=Double.valueOf(forleng);
					System.out.println(input);
				}
				else {
					forleng=String.valueOf(input);
					forleng=forleng+t;
					input=Double.valueOf(forleng);
				}
			}
		}
		state=State.NUM;
		print=formatter.format(input);
	}
	public String calcul() {
		System.out.println(print);
		return print;
	}
	void doop(char op) {
		switch(op) {
		case '+':
			output=output+input;
			break;
		case '-':
			output=output-input;
			break;
		case '*':
			output=output*input;
			break;
		case '/':
			output=output/input;
			break;
		}
	}
}

