import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void firstParamentrCheckException () {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 12, 15);
                }
        );
    }


    @Test
    public void firstParametrCheckExceptionMessage () {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 12, 15);;
        });
        assertEquals("Name cannot be null.", exception.getMessage());
}

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\t"})
    public void firstParametrNullSpaceTabCheckException (String parametr){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(parametr, 12, 15);;
        });
}

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\t"})
    public void firstParametrNullSpaceTabCheckExceptionMessage (String parametr){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(parametr, 12, 15);;
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void secondParamentrCheckException () {
        assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("ABC", -2, 15);
                }
        );
    }

    @Test
    public void secondParametrCheckExceptionMessage () {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ABC", -12, 15);;
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void thirdParamentrCheckException () {
        assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("ABC", 2, -15);
                }
        );
    }

    @Test
    public void thirdParametrCheckExceptionMessage () {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ABC", 12, 15);;
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        String nameN = null;
        Horse horse = new Horse("firstPar", 2,5);
         Field field= horse.getClass().getDeclaredField("name");
         field.setAccessible(true);
         nameN = (String) field.get(horse);
         assertEquals("firstPar", nameN);
    }

    @Test
    public void getSpeed() throws NoSuchFieldException, IllegalAccessException {

        Horse horse = new Horse("firstPar", 2,5);
        Field field= horse.getClass().getDeclaredField("speed");
        field.setAccessible(true);
        double nameN = (double) field.get(horse);
        assertEquals(2, nameN);
    }

    @Test
    public void getDistance() throws NoSuchFieldException, IllegalAccessException {

        Horse horse = new Horse("firstPar", 2,5);
        Field field= horse.getClass().getDeclaredField("distance");
        field.setAccessible(true);
        double nameN = (double) field.get(horse);
        assertEquals(5, nameN);
    }

    @Test
    public void getNoDistance() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("firstPar", 2);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void move () {
        try (MockedStatic<Horse> fakeHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("Name", 5,6).move();
            fakeHorse.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.9, 0.15, 0.6})
    public void moveDistanceCalcbyFormula(Double parametr) {
        try(MockedStatic<Horse> fakeHorse = Mockito.mockStatic(Horse.class)) {
            fakeHorse.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(parametr);
            Horse horse = new Horse("Name", 15,20);
            horse.move();
            assertEquals(20 + 15 * Horse.getRandomDouble(0.2,0.9),horse.getDistance());
        }
    }
}
