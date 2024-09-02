package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
//Comparator.reverseOrder()
public class Main {
    public static void main(String[] args) {
        // Задание 1: Найти наиболее часто встречающееся слово и вывести все такие слова, если их несколько
        System.out.println("=== 1 задание ===");
        List<String> words = new ArrayList<>(Arrays.asList("Серёженька","Шакаладка","Шакаладка", "Цапля", "Шоколадка", "Ролтон", "БигЛанч", "Шоколадка", "БигЛанч", "Я", "Сергей", "Танчик"));
        String result = words.stream()
                // Группируем слова по их идентичности и считаем количество вхождений каждого слова
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                // Группируем слова по количеству их вхождений
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                // Находим максимальное количество вхождений
                .max(Map.Entry.comparingByKey())
                // Среди слов с максимальным количеством вхождений находим самые длинные слова и объединяем их в строку
                .map(e -> {
                    List<String> maxLengthWords = e.getValue().stream()
                            .filter(word -> word.length() == e.getValue().stream().mapToInt(String::length).max().orElse(0))
                            .collect(Collectors.toList());
                    return String.join(", ", maxLengthWords);
                })
                .orElse("");
        System.out.println(result);

        // Задание 2: Найти N самых старших сотрудников мужчин и вывести их имена
        System.out.println("=== 2 задание ===");
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Геннадий", 40, 'М', 50000),
                new Employee("Иван", 35, 'М', 50000),
                new Employee("Петр", 40, 'М', 60000),
                new Employee("Мария", 30, 'Ж', 55000),
                new Employee("Анна", 45, 'Ж', 70000),
                new Employee("Сергей", 50, 'М', 75000)
        ));
        printNEldestMales(employees, 3);
    }

    // Метод для поиска N самых старших сотрудников мужчин и вывода их имен
    public static void printNEldestMales(List<Employee> employees, int n) {
        System.out.println(
                employees.stream()
                        // Фильтруем сотрудников по полу (только мужчины)
                        .filter(employee -> employee.getGender() == 'М')
                        // Сортируем сотрудников по возрасту в порядке убывания
                        .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                        // Ограничиваем результат первыми N сотрудниками
                        .limit(n)
                        // Получаем имена сотрудников
                        .map(Employee::getName)
                        // Сортируем имена в порядке возрастания
                        .sorted()
                        // Объединяем имена в строку с заданным форматом
                        .collect(Collectors.joining(", ", n + " самых старших сотрудников в порядке возрастания возраста зовут: ", ";")));
    }
}