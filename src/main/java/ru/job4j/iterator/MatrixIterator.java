package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
    }

    @Override
    public boolean hasNext() {
        // Пропускаем пустые строки и элементы в пустых строках
        while (row < data.length && (data[row] == null || column >= data[row].length)) {
            row++;      // Переходим к следующей строке
            column = 0; // Сбрасываем индекс столбца на 0
        }
        // Проверяем, есть ли элементы в текущей строке
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = data[row][column];  // Получаем текущий элемент
        // Переходим к следующему элементу
        if (column < data[row].length - 1) {
            column++;  // Если есть элементы в текущей строке, двигаемся вправо по столбцам
        } else {
            column = 0;  // Если дошли до конца строки, переходим к первому столбцу следующей строки
            row++;       // Переходим к следующей строке
        }
        return value;
    }
}