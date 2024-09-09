import java.util.ArrayList;
import java.util.List;

public class Fibo {

    private static Integer[] nums = new Integer[] {4, 8, 16, 32, 128, 1000, 10_000};

    public static void main(String args[]) {
        Implementa("fibo1", nums);
        Implementa("fibo2", nums);
        Implementa("fibo3", nums);
    }

    public static long getTime() {
        return System.nanoTime();
    }

    public static void Implementa(String metodo, Integer[] nums) {
        for (int num : nums) {
            long t1 = getTime();

            int result = 0;
            switch (metodo) {
                case "fibo1":
                    if (num < 100) { 
                        result = fibo1(num);
                    } else {
                        System.out.println(metodo + "(" + num + ") is too large for fibo1.");
                        continue;
                    }
                    break;
                case "fibo2":
                    result = fibo2(num);
                    break;
                case "fibo3":
                    result = fibo3(num);
                    break;
                default:
                    System.out.println("Unknown method: " + metodo);
                    continue;
            }

            long t2 = getTime();
            long t = t2 - t1;
            t /= 1_000_000; 

            System.out.println(metodo + "(" + num + ") :" + result);
            System.out.println("Tempo Decorrido: " + t + "ms.");
        }
    }

    public static int fibo1(int n) {
        if (n < 2)
            return n;
        return fibo1(n - 1) + fibo1(n - 2);
    }

    public static int fibo2(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input");
        List<Integer> f = new ArrayList<>();
        f.add(0); 
        if (n > 0) f.add(1);

        for (int i = 2; i <= n; i++) {
            f.add(f.get(i - 1) + f.get(i - 2));
        }

        return f.get(n);
    }

    public static int fibo3(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input");
        List<Integer> f = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            f.add(-1);
        }
        return lookupFibo(f, n);
    }

    private static int lookupFibo(List<Integer> f, int n) {
        if (f.get(n) >= 0) {
            return f.get(n);
        }

        if (n <= 1) {
            f.set(n, n);
        } else {
            f.set(n, lookupFibo(f, n - 1) + lookupFibo(f, n - 2));
        }

        return f.get(n);
    }
}
