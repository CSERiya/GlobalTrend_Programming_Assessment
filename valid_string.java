import java.util.*;
public class valid_string {

    public boolean isValid(String s){
Stack<Character>st=new Stack<>();
for(char c:s.toCharArray()){
    if(c=='('||c=='{'||c=='[')st.push(c);
    else{
        if(st.empty())return false;
        char top = st.pop();
        if ((c == ')' && top != '(') ||
            (c == '}' && top != '{') ||
            (c == ']' && top != '[')) {
            return false;
        }
    }
}
return st.isEmpty();
    }
    public static void main(String[] args) {
        valid_string validator = new valid_string();

        String[] testStrings = {
            "([])",      
            "()[]{}",  
            "(]",     
            "([)]",    
            "{[]}"    
        };

        for (String test : testStrings) {
            System.out.println("Input: " + test + " -> " + validator.isValid(test));
        }
    }
}
