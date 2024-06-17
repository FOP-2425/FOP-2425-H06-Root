package h06.problems;

public class DragonCurve {

    public DragonCurve() {

    }

    public static int power(int a, int b) {
        if(b == 0) {
            return 1;
        }
        else if (b == 1) {
            return a;
        } else {
            return a * power(a, b-1);
        }
    }

    public static String[] concatenate(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
//        System.arraycopy(a, 0, c, 0, a.length);
//        System.arraycopy(b, 0, c, a.length, b.length);

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }

        for (int i = a.length; i < a.length+b.length; i++) {
            c[i] = b[i-a.length];
        }

        return c;
    }

    /*
     * Return a new array!
     */
    public static String[] replaceAtIndex(String[] arr, int idx, String elem) {
        String[] newArr = new String[arr.length];

        for (int i = 0; i < newArr.length; i++) {
            if(i == idx) {
                newArr[idx] = elem;
            } else {
                newArr[i] = arr[i];
            }
        }

        return newArr;
    }

    public static String[] dragonCurve(int n) {
        if(n <= 0) {
            return new String[0];
        }
        if (n == 1) {
            return new String[]{"R"};
        }
        else {
            String[] lastOrder = dragonCurve(n - 1);
            String[] firstHalf = concatenate(lastOrder, new String[]{"R"});
            String[] secondHalf = replaceAtIndex(lastOrder, power(2, n-2)-1, "L");
            return concatenate(firstHalf, secondHalf);
        }
    }
}
