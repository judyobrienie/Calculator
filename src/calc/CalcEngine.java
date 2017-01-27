package calc;

import java.util.Scanner;
import java.util.Stack;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  Judy O'Brien
 * @version 1.
 */
public class CalcEngine
{
   
	String displayUserInput = " ";
  

    /**
     * The 'C' (clear) button was pressed.
     */
    public void clear()
    {
    	displayUserInput = " ";
    	
    }

    
    
  /**  
   * Algorithm convertToPostfix (infix)
   * Converts an infix expression to an equivalent postfix expression.
   * @param take in string of infix and converts to a string of postfix
   * @return a string in postfix notation
   */
    public String convertPostfix(String string){

  		Stack<Character> operatorStack = new Stack<Character>();

  		StringBuffer postfix = new StringBuffer();

  		Character topOperator;
  		for(int i = 0; i < string.length(); i++) {
  			char c = string.charAt(i);
  				
  			if(Character.isLetterOrDigit(c))
				postfix.append(c);
  			
  			// fixing bug to test next character before adding a space
			    if (i+1 >= string.length() || !Character.isLetterOrDigit(string.charAt(i+1)))
	                     postfix.append(" ");
  			else
  			{
  				
  				switch(c)

  				{
  				case '^':
  					operatorStack.push (c);
  					break;
  				case '.':
  					operatorStack.push (c);
  					break;
  				case '+':
  				case '-':
  				case '*':
  				case '/':
  					while (!operatorStack.isEmpty () &&
  							getPrecedence(c) <= getPrecedence(operatorStack.peek()))
  					{
  						postfix.append(operatorStack.peek() + " ");
  						operatorStack.pop();
  						
  					}
  					operatorStack.push (c);
  					break;

  				case '(':
  					operatorStack.push (c);
  					break;

  				case ')':  // stack is not empty if infix expression is valid
  					topOperator = operatorStack.pop();
  					while (topOperator != '(')
  					{
  						postfix.append(topOperator + " ");
  						topOperator = operatorStack.pop();
  						
  					}
  					break;
  				default:
  					break;
  				}
  			}
  		}
  			while (!operatorStack.isEmpty())
  			{
  				topOperator = operatorStack.pop();
  				postfix.append(topOperator + " ");
  				
  			}
  			return postfix.toString();

  		}

  	
    /**
     * 
     */
    
    
    
    public static Double postfixEvaluate(String postfix) {
	 	Stack<Double> valueStack = new Stack<Double> ();
		
		Scanner tokens = new Scanner(postfix);
		
		while (tokens.hasNext()) {
			if (tokens.hasNextInt()) {
				valueStack.push(tokens.nextDouble());
			} else {
				double operandTwo = valueStack.pop();
				double operandOne = valueStack.pop();
				String function = tokens.next();
				
				 switch (function)
				    {
				        
				          
				                case "+":
				                	valueStack.push(operandOne + operandTwo);
				                	break;
				                case "-":
				                	valueStack.push(operandOne - operandTwo);
				                	break;
				                case "*":
				                	valueStack.push(operandOne * operandTwo);
				                	break;
				                case "/":
				                	valueStack.push(operandOne / operandTwo);
				                	break;
				                case "^":
				                	valueStack.push( (double) Math.pow(operandOne, operandTwo));
				                	//working on decimal point calculations. also give it a precedence of 4
				                case ".":
				                	double temp = operandTwo/10;
				                	valueStack.push(operandOne = (temp + operandOne));
				                	break;
				                   
				                default:
				                    break;
				    }// end of switch
				
				
			
			}// end of else
		}// end of while
		return valueStack.peek();
    }
    
    
  	
  	/**
  	 * 
  	 * @param 
  	 * @return
  	 */
  	
  	
  	private static int getPrecedence(char operator)
	{
		switch (operator)
		{
		case '(': case ')': return 0;
		case '+': case '-': return 1;
		case '*': case '/': return 2;
		case '^':           return 3;
		case '.':			return 4;
		} // end switch

		return -1;
		
	}// end getprecedence
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Return the title of this calculation engine.
     */
    public String getTitle()
    {
        return("Judys' Calculator");
    }

    /**
     * Return the author of this engine. This string is displayed as it is,
     * so it should say something like "Written by H. Simpson".
     */
    public String getAuthor()
    {
        return("Judy O'Brien");
    }

    /**
     * Return the version number of this engine. This string is displayed as 
     * it is, so it should say something like "Version 1.1".
     */
    public String getVersion()
    {
        return("Ver. 1.0");
    }

}