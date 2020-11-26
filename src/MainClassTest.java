import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int number = this.getLocalNumber();
        if (number == 14) {
            System.out.println("Test completed successfully");
        }
        else {
            System.out.println("Test Failed " + number + "!=14");
        }
    }

    @Test
    public void testGetClassNumber() {
        int number = this.getClassNumber();
        if (number > 45) {
            System.out.println("Test completed successfully");
        }
        else {
            System.out.println("Test Failed " + number + "<45");
        }
    }

    @Test
    public void testGetClassString() {
        String phrase = this.getClassString();
        if (phrase.contains("Hello")) {
            System.out.println(phrase);
        } else if (phrase.contains("hello")) {
                System.out.println(phrase);
            } else {
                System.out.println("Test Failed " + phrase + " не содержит Hello или hello");
            }
        }
    }
