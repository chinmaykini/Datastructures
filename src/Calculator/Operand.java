package Calculator;

public class Operand {
	
	public static boolean isOperand(char ch){
		if(ch  >= '0' && ch <= '9'){
			return true;
		}
	
		return false;
	}
	
	public int getIntValue(String number){
		return Integer.parseInt(number);
	}

}
