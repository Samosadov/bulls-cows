import java.io.*;
import java.util.*;

public class Main
{
    static final int NUM_LEN = 4;
    static final String WIN_MESSAGE = "Да!\n";
    static final String LOSE_MESSAGE = "Лажа...";
    static int aimNum;
    static int attempt;
    static boolean eog = false;
    static boolean win;
    
    public static class Digit {
        int bulls;
        int cows;
        public Digit() {
            bulls = 0;
            cows = 0;
        }
        public Digit(int bulls, int cows) {
            this.bulls = bulls;
            this.cows = cows;
        }
        
        @Override
        public String toString() {
            return "Быки: " + bulls + ", коровы: " + cows;
        }
    }
    
    public static int genNum() {
        List<Integer> digits = new ArrayList<>();
        digits.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int result = 0;
        for (int i = 0; i < NUM_LEN; i++) {
            int tmp = (int) Math.floor(Math.random() * digits.size());
            if (i == 0 && tmp == 0) tmp++;
            result = result * 10 + digits.get(tmp);
            digits.remove(tmp);
        }
        return result;
    }
    
    public static void initScreen() {
		System.out.println("============ Быки и коровы =============");
		aimNum = genNum();
		attempt = 10;
		win = false;
// 		System.out.println("Задумано число: " + aimNum);
    }
    
    public static Digit check(int num) {
        String sNum = String.valueOf(num);
        String sAim = String.valueOf(aimNum);
        int bulls = 0, cows = 0;
        for (int i = 0; i < NUM_LEN; i++) {
            if (sNum.charAt(i) == sAim.charAt(i)) bulls++;
            for (int j = 0; j < NUM_LEN; j++) {
                if ((sNum.charAt(i) == sAim.charAt(j)) && (i != j)) cows++;
            }
        }
        if (bulls == 4) win = true;
        return new Digit(bulls, cows);
    }
    
	public static void main(String[] args) throws Exception {
	    initScreen();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    int num = 0;
	    while (!eog) {
	        while (num != aimNum && attempt > 0) {
	            System.out.print(" -> "); 
	            num = Integer.parseInt(reader.readLine());
	            System.out.println("Попыток осталось: " + attempt);
	            System.out.println(check(num));
	            attempt--;
	        }
	        if (win) System.out.print(WIN_MESSAGE);
	        else {
	            System.out.println("Задумано число: " + aimNum);
	            System.out.print(LOSE_MESSAGE);
	        }
	        System.out.print(" Ще? (y/n) ");
	        if (reader.readLine().equals("n")) eog = true;
	    }
	    reader.close();
	}
}