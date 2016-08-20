package Calculator;

import java.util.Stack;

public class ExpressionEval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			
//			System.out.println(evaluate("1+2"));
//			System.out.println(evaluate("1+2*3"));
//			System.out.println(evaluate("1*2+3"));
//			System.out.println(evaluate("(1+2)*3"));
//			System.out.println(evaluate("(1+2)*3+2"));
//			System.out.println(evaluate("5-(1+2)*3+2"));
//			System.out.println(evaluate("5-(5+4)/3+2"));
			
			System.out.println(evaluate("5-(5+4*(6-4))/3+2"));
//			System.out.println(evaluate("5-(5+4)/3+2"));
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/*
	 * return the integer evalution of an expression 
	 * 
	 * Assumptions : Blank Spaces ignored
	 */
	public static int evaluate(String expression) throws Exception{
		
		if(expression == null || expression.isEmpty()){
			return 0;
		}
	
		
		// Operator Stack
		Stack<Character> operators = new Stack<Character>();
		
		// Operand Stack
		Stack<Integer> operands = new Stack<Integer>();
	
		char[] tokens = expression.toCharArray();
		
		for(int i=0; i < tokens.length ; i++){
			
			//Blank Spaces ignored
			if(tokens[i] == ' '){
				continue;
			}
			
			
			// Check for numbers
			if(Operand.isOperand(tokens[i])){
				
				StringBuilder sb = new StringBuilder();
				while(i < tokens.length && Operand.isOperand(tokens[i])){
					sb.append(tokens[i]);
					i++;
				}
				// push integer value to operand stack
				operands.push(Integer.parseInt(sb.toString()));
				i--;
			
			// check for opening braces
			}else if(Operator.isOpeningBrace(tokens[i])){
				operators.push(tokens[i]);
			
			// check for closing braces
			}else if(Operator.isClosingBrace(tokens[i])){
				
				while(!operators.isEmpty() && operators.peek() != '('){
					Integer tempVal = Operator.applyOperator(operators.pop(), operands.pop(), operands.pop());
					operands.push(tempVal);
				
				}
				
				// pop opening brace
				if(!operators.isEmpty()){
					operators.pop();
				}else{
					throw new Exception("illegal expression no opening brace " + tokens[i]);
				}
			
			
			}else if(Operator.isOperator(tokens[i])){
				
				while(!operators.isEmpty() && Operator.hasLowerPrecedence(tokens[i], operators.peek())){
					Integer tempVal = Operator.applyOperator(operators.pop(), operands.pop(), operands.pop());
					operands.push(tempVal);
				}
				
				// push current operator
				operators.push(tokens[i]);
				
				
			}else{
				throw new Exception("illegal charater encountered " + tokens[i]);
			}
			
		}
		
		while(!operators.empty()){
			Integer tempVal = Operator.applyOperator(operators.pop(), operands.pop(), operands.pop());
			operands.push(tempVal);
		}
		
		// errro check
		if(operands.isEmpty() || operands.size() > 1 ){
			throw new Exception("Illegeal Expression");
		}
		
		return operands.pop();
	}




}
