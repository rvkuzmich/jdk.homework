package ru.gb.jdk.homework.hw3.task2;

public class Main {
    public static void main(String[] args) {
        Integer[] array1 = {1, 3, 5, 7};
        Integer[] array2 = {1, 3, 5, 7};
        Integer[] array3 = {1, 3, 5, 9};
        Integer[] array4 = {1, 3, 5};

        String[] arrayS1 = {"cat", "dog", "cat"};
        String[] arrayS2 = {"cat", "dog", "cat"};
        String[] arrayS3 = {"cat", "rat", "cat"};
        String[] arrayS4 = {"cat", "dog"};


        System.out.println(compareArrays(array1, array2));
        System.out.println(compareArrays(array1, array3));
        System.out.println(compareArrays(array1, array4));
        System.out.println(compareArrays(arrayS1, arrayS2));
        System.out.println(compareArrays(arrayS1, arrayS3));
        System.out.println(compareArrays(arrayS1, arrayS4));
        System.out.println(compareArrays(array4, arrayS1));

    }
    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].getClass().equals(arr2[i].getClass())) {
                return false;
            }
        }
        return true;
    }

}
