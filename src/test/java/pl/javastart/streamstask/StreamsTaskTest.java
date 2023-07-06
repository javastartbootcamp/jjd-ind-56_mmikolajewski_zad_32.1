package pl.javastart.streamstask;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class StreamsTaskTest {
    StreamsTask streamsTask = new StreamsTask();

    @Test
    void shouldNotFindWomen() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Gerwazy", 20));
        users.add(new User(2L, "Anastazy", 15));
        users.add(new User(3L, "Hieronim", 25));
        //when
        Collection<User> women = streamsTask.findWomen(users);
        //then
        assertEquals(0, women.size());
    }

    @Test
    void shouldReturnOnlyWomenList() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Alicja", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        //when
        Collection<User> women = streamsTask.findWomen(users);
        //then
        assertEquals(2, women.size());
    }

    @Test
    void shouldReturnAverageMenAge() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Ania", 10));
        users.add(new User(2L, "Kasia", 10));
        users.add(new User(3L, "Basia", 10));
        users.add(new User(4L, "Marcin", 15));
        users.add(new User(5L, "Tomek", 20));
        //when
        Double averageMenAge = streamsTask.averageMenAge(users);
        //then
        assertEquals(17.5, averageMenAge);

    }

    @Test
    void shouldReturnAverageAgeEqualsZero() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Ania", 10));
        users.add(new User(2L, "Kasia", 10));
        users.add(new User(3L, "Basia", 10));
        //when
        Double averageMenAge = streamsTask.averageMenAge(users);
        //then
        assertEquals(0, averageMenAge);

    }

    @Test
    void shouldReturnMapAsKeyOfUserId() {
        //given
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "Alicja", 20);
        User user2 = new User(2L, "Dominik", 15);
        User user3 = new User(3L, "Patrycja", 25);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Expense expense1 = new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR);
        Expense expense2 = new Expense(2L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD);
        Expense expense3 = new Expense(3L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR);
        Expense expense4 = new Expense(3L, "Auto", new BigDecimal("50.000"), ExpenseType.CAR);
        List<Expense> expenses1 = new ArrayList<>();
        List<Expense> expenses2 = new ArrayList<>();
        List<Expense> expenses3 = new ArrayList<>();
        List<Expense> allExpenses = new ArrayList<>();
        expenses1.add(expense1);
        expenses2.add(expense2);
        expenses3.add(expense3);
        expenses3.add(expense4);
        allExpenses.add(expense1);
        allExpenses.add(expense2);
        allExpenses.add(expense3);
        allExpenses.add(expense4);
        Map<Long, List<Expense>> map = new HashMap<>();
        map.put(user1.getId(), expenses1);
        map.put(user2.getId(), expenses2);
        map.put(user3.getId(), expenses3);

        //when
        Map<Long, List<Expense>> longListMap = streamsTask.groupExpensesByUserId(users, allExpenses);

        //then
        assertEquals(3, longListMap.size());
        assertEquals(2, longListMap.get(3L).size());

    }

    @Test
    void shouldReturnMapAsKeyOfUser() {
        //given
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "Alicja", 20);
        User user2 = new User(2L, "Dominik", 15);
        User user3 = new User(3L, "Patrycja", 25);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Expense expense1 = new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR);
        Expense expense2 = new Expense(2L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD);
        Expense expense3 = new Expense(3L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR);
        Expense expense4 = new Expense(3L, "Auto", new BigDecimal("50.000"), ExpenseType.CAR);
        List<Expense> expenses1 = new ArrayList<>();
        List<Expense> expenses2 = new ArrayList<>();
        List<Expense> expenses3 = new ArrayList<>();
        List<Expense> all = new ArrayList<>();
        expenses1.add(expense1);
        expenses2.add(expense2);
        expenses3.add(expense3);
        expenses3.add(expense4);
        all.add(expense1);
        all.add(expense2);
        all.add(expense3);
        all.add(expense4);
        Map<User, List<Expense>> map = new HashMap<>();
        map.put(user1, expenses1);
        map.put(user2, expenses2);
        map.put(user3, expenses3);

        //when
        Map<User, List<Expense>> map1 = streamsTask.groupExpensesByUser(users, all);

        //then
        assertEquals(3, map1.size());
        assertEquals(2, map1.get(user3).size());
    }
}