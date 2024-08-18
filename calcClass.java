package Calc;
import java.util.Scanner;
public class calcClass {
	//Variables
	Scanner sc;
	String doubText,prevOp,op,def;
	double arg,res;
	long intArg;
	boolean isNinety,cont,terminated,isZero;
	final double e=2.7182818284;
   final double pi=3.1415926535;
   //Constructor to assign values-Debugged
	calcClass(){
		sc = new Scanner(System.in);
		def="nulll";
		op=def;
		prevOp = def;
		res=0;
		isZero=false;
		terminated = false;
		isNinety = false;
	}
	//Main method to launch-Debugged
	public static void main(String[] args) {
		calcClass cClass = new calcClass();
		cClass.displayInst();
		cClass.loop();
	}
	//Instructions-Debugged
	public void displayInst() {
		
		System.out.println("Hello!");
		System.out.println("Enter Operator According to the operation required(Default is addition).");
		System.out.println("Precision Upto fifteen Decimal Places. ");
		System.out.println("Enter angles in Degree.");
		System.out.println("Enter e for napier's constant and pi for circle ratio.");
		System.out.println("Addition -> +");
		System.out.println("Subtraction -> -");
		System.out.println("Multiplication -> *");
		System.out.println("Division -> /");
		System.out.println("Remainder -> %");
		System.out.println("Sine -> sin");
		System.out.println("Co-sine -> cos");
		System.out.println("Tangent -> tan");
		System.out.println("Exponent/Nth Root ( Enter 1/n ) -> ^");
		System.out.println("Logarithm(Enter arguument first , then base) -> log");
		System.out.println("Greatest Integer Function -> []");
		System.out.println("Quadratic Equation -> x");
		System.out.println("Answer -> =");
		System.out.println("Terminating the Calculator -> #");
		System.out.println("Change Operator -> ~");
		System.out.println("Begin Calculating !");
	}
	//Method to input arg or operator(op)-Debugged
	public void input() {
		if(sc.hasNextDouble()) {
		arg = sc.nextDouble();
		if (arg==0 && op!=def)
			isZero=true;
		if(arg == 90)
			isNinety=true;
		}
		else if(sc.hasNext()) {
		op=sc.next();	
		if(op==null)
			op=def;
		}
	}
	//Method to input arg only-Debugged
	public double inputArg() {
		boolean invalidArg=true;double var=0;
		while(invalidArg) {
		if(sc.hasNextDouble()) {
			invalidArg=false;
			var = sc.nextDouble();
			if (arg==0 && op!=def)
				isZero=true;
			}
			else {
				System.out.println("Enter a valid Argument");
				invalidArg=true;
			}
		}
		return var;
	}
	//Method to call other methods based on operators
	public void process() {
		//For debugging
//		System.out.println(arg);
//		System.out.println(res);
//		System.out.println(op);
//		System.out.println(prevOp);
		//for beginning arg
		if(op==def)
			add();
		//Comparing Operators and calling respective methods
	else {
		switch(op) {
		case "+" :  	add();
		break;
		case "-" : 		sub();
		break;
		case "*" : 		mult();	
		break;
		case "/" : 		div();
		break;
		case "x" : 		quad();
		break;
		case "sin" : 	res=sine(arg);
		break;
		case "cos" : 	res=cosine(arg);
		break;
		case "tan" : 	if(arg==90) {
						 if(isNinety) {
							 	System.out.println("Not Defined");
							 	isNinety=false;
						 }
						}
						else
						res=tangent(arg);
		break;
		case"e":		arg=e;
						op=prevOp;
		break;
		case "pi" :		arg=pi;
						op=prevOp;
		break;
		case "log": 	if(arg==0||res==0)
							{
								if(isZero) {
								System.out.println("Argument can't be Zero");
								op=def;
								prevOp=def;
								isZero=false;
								}
							}
						else if(arg<0||res<0) {
		 				System.out.println("Argument in log can't be negative");
		 				op=def;
		 				prevOp=def;
		 				}
		 				else 	 				 
							res=((log(res))/(log(arg)));
		break;
		case "^" : 		if(arg==0)
						arg=1;
						if(isZero)
						{arg=0;
						isZero=false;
						}
						res=exp(res,arg);
		break;
		case "%" : 		rem();
		break;
		case "[]" : 	gif();
		break;
		case "=" : 	
						switch(prevOp) {
							case "+" : 		add();
							break;
							case "-" :		sub();
							break;
							case "*" : 		mult();
							break;
							case "/" : 		div();
							break;
							case "x" : 		quad();
							break;
							case "[]" : 	gif();
							break;
							case "^" :		if(arg==0)
											arg=1;
											if(isZero)
											{arg=0;
											isZero=false;
											}
											exp(res,arg);
							break;
							case "%" : 		rem();
							break;
						}
						ans();
			break;
			case "#" : 		terminated = true;
							System.out.println("Thanks for Using !");
			break;
			case "~" : 		op=def;
							prevOp=def;
			break;
			default : 		if(op.startsWith("1/")) {
								arg=(Double.parseDouble(op.substring(2)));
								op=prevOp;
								arg=1/arg;
			}
			else 
				System.out.println("Please Enter a valid Operator/Argument");
			op=def;
			prevOp=def;
			break;
		}
	}
		//Assigning previous Operator to perform calculation on arg prior to "=".-Debugged
		prevOp=op;
	}
	public double tangent(double angle) {
		double ret =0;
			ret=(sine(angle))/(cosine(angle));
		return ret;
	}
	//Method for GIF-Debugged
	public void gif() {
		doubText=Double.toString(res);
		res=Double.parseDouble(doubText.substring(0, doubText.indexOf(".")));
		arg=0;
	}
	//Method for Quadratic-Debugged
	public void quad() {
		double a,b,c;
		System.out.println("Enter coefficients -");
		System.out.print("a = ");
		a=inputArg();
		System.out.print("b = ");
		b=inputArg();
		System.out.print("c = ");
		c=inputArg();
		double D = (exp(b,2))-(4*a*c);
		if(D<0)
			System.out.println("Imaginary roots");
		else if(D==0)
			res = (-b)/(2*a);
		else
		res= (((-b) + exp(D,0.5))/(2*a));
		System.out.println(".~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~.-~.-~.-~.-~.-~.-~.");
		System.out.print(res + " , ");
		if(D<0)
			System.out.println("Imaginary roots");
		else if(D==0)
			res = (-b)/(2*a);
		else
		res=(((-b) - exp(D,0.5))/(2*a));
		System.out.println(res);
		res=0;
		op=def;
		prevOp=def;
		System.out.println(".~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~.-~.-~.-~.-~.-~.-~.");
	}
	//Method for division--Debugged
	public void div() {
		if(isZero)
			{System.out.println("Divisor can't be zero");
			isZero=false;
			res=0;
			op=def;
			prevOp=def;
			}
		if(arg!=0) {
		res/=arg;
		arg=0;
		}
	}
	//Method for multiplication-Debugged
	public void mult() {
		if(arg==0)
			arg=1;
		 if(isZero)
			{arg=0;
			isZero=false;
			}
		res*=arg;
		arg=0;
	}
	//Method for subtraction-Debugged
	public void sub() {
		res-=arg;
		arg=0;
	}
	//Method for Addition-Debugged
	public void add() {
		res+=arg;
		arg=0;
	}
	//Method for modulo-Debugged
	public void rem() {
		if(isZero)
		{System.out.println("Divisor can't be zero");
		isZero=false;
		res=0;
		op=def;
		prevOp=def;
		}
		if(arg!=0){
		res = res % arg;
		arg =0;
	}
	}
	//Method for sin-Debugged
	public double sine(double angle) {
		double s=1,ret=0;
		int n=1;
		while(angle>=180) {
			angle-=180;
		}
		if(angle>90)
			angle-=180;
		angle = (angle*pi)/180;
		do {
		ret+=s*(exp(angle,n))/(fact(n));
		n+=2;
		s*=-1;
		}while(n<=10000);
		return ret;
	}
	//Method to aid factorial for sin-Debugged
	public double fact(double a) {
		double fact=1;
		for (double i=a;i>0;--i)
		{
			fact*=i;
		}
		return fact;
	}
	//Method for cos-Debugged
	public double cosine(double angle) {
		double temp=0,s=1,ret=0;
		int n=0;
		while(angle>=180) {
			angle-=180;
		}
		if(angle>90)
		{
			temp=angle;
			angle-=180;
		}
		angle = (angle*pi)/180;
		do {
		ret+=s*(exp(angle,n))/(fact(n));
		n+=2;
		s*=-1;
		}while(n<=10000);
		if(temp>90)
			ret=-ret;
		return ret;
	}
	//Method for exponent and root-Debugged
	public double exp(double b, double i) {
		
	double ret=1;
	doubText = Double.toString(i);
	if(doubText.endsWith(".0")) //Checking if index is integer
	{
		intArg=(long)i;
		if(intArg>0) {//Positive Integer
			for(int m=0;m<intArg;m++) {
				ret*=b;
			}
		}
		else if (intArg==0)//Zero
			ret=1;
		else {
			for(int m=0;m>intArg;m--) {//Negative Integer
				ret*=b;
			}
			ret=1/ret;
		}
	}
	else {//Non-integer powers- Newton formula
		double n = 1/i;
		double t=b/2 ; //Temporary variable
		ret =b/2; //Initial assumption
		do {
			t=ret;
			ret=(t - (exp(t,n)-b)/(n*(exp(t,n-1)))); 
		}while((t-ret)>0.000000000000001 || (t-ret)<(-0.000000000000001));
    }
//	arg=0; //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnthis and sin cos additionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
	return ret;
	}
	//Method for log- Newton Formula-Debugged	
	public double log(double num) {
	double ret=0;
	double x;
	int s=1,n=1;
	if(num<=0)
		return ret;
	//For number less than 1
	else if(num>0&&num<=1) {
		ret=-log(1/num);
	}
	else if(num==e)
		ret=1;
	else if(num>1&&num<=2) {
		x=num-1;
		do {
			ret+=s*(exp(x,n))/n;
			s*=-1;
			n++;
		}while(n<10000);
		
	}
	else if(num>2) {
		ret=log(2)+log(num/2);
	}
	return ret;
	}
	//Method to keep calculator going on-Debugged
	public void loop() {
		while(!terminated) {
			if(prevOp=="^") {
				inputArg();
			}
			else{
				input();
			}
		process();
		}
	}
	//Method for "="-Debugged
	public void ans() {
		//if precision wanted up to any specific decimal place
//		doubText = Double.toString(res);
//		int dec = doubText.indexOf(".");
//		doubText=doubText.substring(0, (dec+5));
//		res = Double.parseDouble(doubText);
		System.out.println(".~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~.-~.-~.-~.-~.-~.-~.");
		System.out.println(res);
		res=0;
		op=def;
		prevOp=def;
		System.out.println(".~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~-.~.-~.-~.-~.-~.-~.-~.");
	}
}