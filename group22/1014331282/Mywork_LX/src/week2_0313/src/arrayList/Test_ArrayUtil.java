package arrayList;

//import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import arrayList.ArrayUtil;

public class Test_ArrayUtil {

	@Test
	public void test() {
		
		int[] a1={7,9,30,3,4};
		ArrayUtil.reverseArray(a1);
		System.out.println(Arrays.toString(a1));
		
		int[] a2={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		a2=ArrayUtil.removeZero(a2);
		System.out.println(Arrays.toString(a2));
		
		int[] a=new int[]{3,5,7,8};
		int[] b=new int[]{4,5,6,7};
		int[] c=ArrayUtil.merge(a, b);
		System.out.println(Arrays.toString(c));
		
		int[] oldArray={2,3,6};
		int size=3;
		int[] newArray=ArrayUtil.grow(oldArray,size);
		System.out.println(Arrays.toString(newArray));
		
		int[] fi=ArrayUtil.fibonacci(15);
		System.out.println(Arrays.toString(fi));
		
		int[] su=ArrayUtil.getPrime(23);
		System.out.println(Arrays.toString(su));
		
		int[] wan=ArrayUtil.getPerfectNumbers(30);
		System.out.println(Arrays.toString(wan));
		
		int[] re={3,8,9};
		String r=ArrayUtil.join(re,"-");
		System.out.println(r);
	}

}
