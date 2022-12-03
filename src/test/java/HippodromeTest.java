import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullExceptionCheck (){
        assertThrows(IllegalArgumentException.class, () -> {
                    new Hippodrome(null);
                }
        );
    }
    @Test
    public void checkExceptionMessage () {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);;
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void emptyListCheckException (){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());;
        });
    }
    @Test
    public void emptyListCheckMessageException () {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());;
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void horsesListCheck (){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name "+i, 10, 15));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void movetest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        Horse horse1 = new Horse("Name1", 20,25);
        Horse horse2 = new Horse("Name2", 30,35);
        Horse horse3 = new Horse("Name3", 40,45);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertEquals(horse3, hippodrome.getWinner());
    }

}
