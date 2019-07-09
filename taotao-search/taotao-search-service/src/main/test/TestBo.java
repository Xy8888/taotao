import org.junit.Test;

/**
 * 冒泡排序
 */
public class TestBo {

    @Test
    public void test() {
        int[] arr = {2,5,1,3,6,9,8,7,4,3,2,1};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int n = 0; n < arr.length - 1 - i; n++) {
                if (arr[n] > arr[n+1]) {
                    swap(arr, n, n+1);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
