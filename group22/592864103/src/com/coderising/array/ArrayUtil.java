import java.util.Arrays;

/**
 * Created by leonhardt on 2017/3/17.
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        int length = origin.length;
        int temp[] = new int[length];
        int k = 0;
        for (int i = length - 1; i >= 0; i--) {
            temp[k] = origin[i];
            k++;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray) {
        int temp[] = new int[oldArray.length];
        int k = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                temp[k] = oldArray[i];
                k++;
            }
        }
        return temp;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2) {
        int i = 0, k = 0, j = 0;
        int[] res = new int[array1.length + array2.length];
        while (array1[i] != 0 && array2[k] != 0) {
            if (array1[i] < array2[k]) {
                res[j] = array1[i];
                i++;
                j++;
            } else if (array1[i] > array2[k]) {
                res[j] = array2[k];
                k++;
                j++;
            } else {
                if (array1[i] == res[j]) {
                    i++;
                    k++;
                } else {
                    res[j] = array1[i];
                    i++;
                    k++;
                    j++;
                }
            }
        }
        return Arrays.copyOf(res, j);
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     *
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int[] oldArray, int size) {
        return Arrays.copyOf(oldArray, oldArray.length + size);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {
        if (max == 0) {
            return new int[]{1};
        } else if (max == 1) {
            return new int[]{1, 1};
        } else {
            int res[] = new int[max];
            res[0] = 1;
            res[1] = 1;
            for (int i = 2; i < max; i++) {
                res[i] = res[i - 2] + res[i - 1];
            }
            return res;
        }
    }


    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int j = 0;
        int[] res = new int[max];
        for (int i = 1; i < max; i++) {
            int flag = 0;
            for (int k = 1; k <= i - i / 2; k++) {
                if (i % k == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                res[j] = i;
                j++;
            }
        }
        return Arrays.copyOf(res, j);
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int[] res = new int[max];
        int j = 0;
        for (int i = 1; i <max; i++){
            int total = 0;
            for (int k = 1; k <= max - max/2; k++){
                if (max%k == 0){
                    total += k;
                }
            }
            if (i == total){
                res[j] = i;
                j++;
            }
        }
        return Arrays.copyOf(res,j);
    }


    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array     *
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator) {
        if (array.length == 0) {
            return null;
        } else {
            String res = Integer.toString(array[0]);
            if (array.length == 1) {
                return res;
            } else {
                for (int i = 1; i < array.length; i++) {
                    res += res + seperator + Integer.toString(array[i]);
                }
                return res;
            }
        }
    }


}
