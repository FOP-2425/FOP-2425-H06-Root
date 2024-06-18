package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

public class DragonCurve {

    /*
     *
     */
    @DoNotTouch
    public DragonCurve() {}

    /*
     *
     */
    @StudentImplementationRequired
    private static int pow(int a, int b) {
        if(b == 0) {
            return 1;
        } else {
            return a * pow(a, b-1);
        }
    }

    /*
     *
     */
    @StudentImplementationRequired
    private static String[] concatenate(String[] a, String[] b) {
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
    @StudentImplementationRequired
    private static String[] replaceAtIndex(String[] arr, int idx, String elem) {
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

    /*
     *
     */
    @StudentImplementationRequired
    public static String[] dragonCurve(int n) {
        if(n <= 0) {
            return new String[]{};
        }
        if (n == 1) {
            return new String[]{"R"};
        }
        else {
            String[] lastOrder = dragonCurve(n - 1);
            String[] firstHalf = concatenate(lastOrder, new String[]{"R"});
            String[] secondHalf = replaceAtIndex(lastOrder, pow(2, n-2)-1, "L");
            return concatenate(firstHalf, secondHalf);
        }
    }
}
