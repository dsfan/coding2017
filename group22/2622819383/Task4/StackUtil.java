
public class StackUtil {


    /**
    * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
    * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
    */
    public static void reverse(Stack s) {        
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }
        
        s = temp;
    }

    /**
    * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
    * 
    * @param o
    */
    public static void remove(Stack s,Object o) {
        Stack temp = new Stack();
        while (s.peek() != o) {
            temp.push(s.pop());
        }
        s.pop();
        
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
    }

    /**
    * 从栈顶取得len个元素, 原来的栈中元素保持不变
    * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
    * @param len
    * @return
    */
    public static Object[] getTop(Stack s,int len) {
        Stack temp = new Stack();
        Object[] ret = new Object[len];
        int index = 0;
        
        while (0 < len--) {
            temp.push(ret[index] = s.pop());
            index++;
        }
        while (!temp.isEmpty())
            s.push(temp.pop());
        return ret;
    }
    /**
    * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
    * 使用堆栈检查字符串s中的括号是不是成对出现的。
    * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
    * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
    * @param s
    * @return
    */
    public static boolean isValidPairs(String s){
        char[] arrS = s.toCharArray();
        Stack temp = new Stack();
        for (i = 0; i < arrS.length; i++) {
            switch(s[i]) {
                case '(': case '[': case '{': temp.push(s[i]); break;
                case ')':
                    //不要忘记判断temp是否为空啊！
                    if (temp.isEmpty() || temp.pop() != '(') return false;
                    break;
                case ']':
                    if (temp.isEmpty() || temp.pop() != '[') return false;
                    break;
                case '}':
                    if (temp.isEmpty() || temp.pop() != '{') return false;
                    break;
                default: break;
            }
        }
        
        
        return temp.isEmpty();
    }


}
