package Calculator;

import java.util.HashMap;

public class  Operator{
	
	public static HashMap<Character, Integer> operatorPredecedenceMap;
	
	
	// can add more binary operators
	public static void buildOperatorPrecedenceMap(){
		operatorPredecedenceMap = new HashMap<Character, Integer>();
		operatorPredecedenceMap.put('+', 3);
		operatorPredecedenceMap.put('-', 3);
		operatorPredecedenceMap.put('*', 2);
		operatorPredecedenceMap.put('/', 2);
		operatorPredecedenceMap.put('(', 4);
		operatorPredecedenceMap.put(')', 4);
		
	}
	
	// do the same with other braces like { [
	public static boolean isOpeningBrace(char ch){
		if(ch == '('){
			return true;
		}
		return false;
	}
	
	
	public static boolean isClosingBrace(char ch){
		if(ch == ')'){
			return true;
		}
		return false;
	}
	
	public static boolean isOperator(char ch){
		
		if(operatorPredecedenceMap == null){
			buildOperatorPrecedenceMap();
		}
		
		if(operatorPredecedenceMap.containsKey(ch)){
			return true;
		}
		
		return false;
	}
	
	public static HashMap<Character, Integer> getOperatorPrecedenceMap(){
		if(operatorPredecedenceMap == null){
			buildOperatorPrecedenceMap();
		}
		return operatorPredecedenceMap;
	}
	
	public static Integer applyOperator(Character operator, Integer val1, Integer val2) throws Exception {
		
		if(operatorPredecedenceMap == null){
			buildOperatorPrecedenceMap();
		}
		
		if(!operatorPredecedenceMap.containsKey(operator)){
			throw new Exception("Illegeal operator " +  operator);
		}

		switch (operator) {
		case '+':
			return val1 + val2;
		
		case '-':
			return val2 - val1;
		case '*':
			return val2 * val1;
		case '/':
			return val2 / val1;
		default:
			break;
		}
		return null;
	}

	public static boolean hasLowerPrecedence(Character op1, Character op2) throws Exception {
		if(operatorPredecedenceMap == null){
			buildOperatorPrecedenceMap();
		}
		
		if(!operatorPredecedenceMap.containsKey(op1)){
			throw new Exception("Illegeal operators " +  op1);
		}
		
//		if(op2 == '(' || op2 == ')'){
//			return false;
//		}
		
		return operatorPredecedenceMap.get(op1) >= operatorPredecedenceMap.get(op2);
	}
	
}
