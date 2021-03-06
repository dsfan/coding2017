package com.coding.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	
	//存放入队列数据
	private Stack<E> stack1;
	
	//存放出队列数据
    private Stack<E> stack2;

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
    public int size() {
        return stack1.size() + stack2.size();   
    }

    public void enQueue(E item) {
    	stack1.push(item);
    }

    public E deQueue() {
    	if (isEmpty()){
    		throw new RuntimeException("The queue is empty");
    	}
		if(stack2.isEmpty()){
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
		}
        return stack2.pop();
    }
 }

