package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[] methods = {"Линейный метод поиска", "Бинарный метод поиска"};
    public static boolean resultOk = false;
    public static int n;
    public static int[] arr;
    public static Scanner scanner = new Scanner(System.in);
    public static int val;
    public static int pos = -1;

    public static void main(String[] args) {
        System.out.println("Введите количество элементов: ");
        while (true) {
            n = scanner.nextInt();
            if (n > 0) break;
            System.out.println("Неверные данные. Попробуйте еще раз.");
        }

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));

        System.out.println("Введите число, индекс которогтхотите найти: ");
        val = scanner.nextInt();

        System.out.println("Введите номер метода, которым хотите найти индекс элемента: ");
        int idMethod = 0;
        while (idMethod < methods.length) {
            System.out.println(idMethod + ". " + methods[idMethod]);
            idMethod++;
        }
        while (true) {
            idMethod = scanner.nextInt();
            if (idMethod >= 0 && idMethod < methods.length) break;
            System.out.println("Неверные данные. Попробуйте еще раз.");
        }

        switch (idMethod) {
            case 0 -> linerSearch();
            case 1 -> binarySearch();
        }
    }

    private static void linerSearch() {
        System.out.println(methods[0]);
        int j = 0;

        while (j < n) {
            if (arr[j] == val) {
                pos = j;
                resultOk = true;
                finish(pos);
                break;
            }
            j++;
        }
        finish(pos);
    }

    private static void binarySearch() {
        System.out.println(methods[1]);
        int first = 0;
        int last = n - 1;
        quickSort(first, last);
        System.out.println(Arrays.toString(arr));

        while (first < last) {
            int middle = (first + last) / 2;
            if (val == arr[middle]) {
                first = middle;
                last = first;
                resultOk = true;
                pos = middle;
                finish(pos);
                break;
            } else {
                if (val > arr[middle]) first = middle + 1;
                else last = middle - 1;
            }
        }
        finish(pos);
    }

    private static void finish(int j) {
        if (resultOk) System.out.println("Индекс элемента: " + j);
        else System.out.println("Такого эдемента нет в массиве");
    }

    private static void quickSort(int low, int high) {
        if (arr.length == 0) return;
        if (low >= high) return;
        int middle = low + (high - low) / 2;
        int opora = arr[middle];
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < opora) {
                i++;
            }
            while (arr[j] > opora) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (high > i)
            quickSort(i, high);
    }
}