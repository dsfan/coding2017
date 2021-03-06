package com.coding.basic.stack;
import java.util.Stack;;
/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	private Stack<Integer> normalStack = new Stack<Integer>();
	private Stack<Integer> minNumStack = new Stack<Integer>();
	
	public void push(int data){
		if(normalStack.isEmpty()){
			minNumStack.push(data);
		}else {
			if (minNumStack.peek().intValue() >= data) {
				minNumStack.push(data);
			}
		}
		normalStack.push(data);
	}
	public int pop(){
		if (normalStack.isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		Integer pop = normalStack.pop();
		if(pop.equals(minNumStack.peek())){
			minNumStack.pop();
		}
		return pop;
	}
	public int findMin(){
		if (normalStack.isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		return minNumStack.peek().intValue();
	}
}
