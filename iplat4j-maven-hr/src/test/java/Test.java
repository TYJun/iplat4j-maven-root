import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String Array23 = "EBN123,dsada,dada,入库出库";
        String[] s12 = Array23.split(",");
        for (int i = 0; i < s12.length; i++) {
            String transportBillNo = s12[i].substring(
                    1, s12[i].length() - 1);
            System.out.println(transportBillNo);
        }
        List<An> list =new ArrayList<>();
        list.add(new An("1"));
        list.add(new An("22"));
        List<An> m =list.stream().filter(an -> an.getAge() == "22").collect(Collectors.toList());

    }
    static class An {
        public String age ;
        public An() {
        }

        public An(String age) {
            this.age = age;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public void aVoid(){
        //冒泡排序
        int[] a = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length-i-1; j++) {
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
    public class TestCopilot {
        //堆排序
        public void heapSort(int[] a) {
            int n = a.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(a, n, i);
            }
            for (int i = n - 1; i > 0; i--) {
                int temp = a[0];
                a[0] = a[i];
                a[i] = temp;
                heapify(a, i, 0);
            }
        }
        //堆调整
        public void heapify(int[] a, int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && a[l] > a[largest]) {
                largest = l;
            }
            if (r < n && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                int temp = a[i];
                a[i] = a[largest];
                a[largest] = temp;
                heapify(a, n, largest);
            }
        }
    }

}
