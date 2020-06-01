package controller;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    private long time;
    private int currentSize;
    private int[] array;
    private int[] buffer1;
    private int[] buffer2;

    public Sort(int size){
        this.currentSize = size;
        this.time = sortTime(createArray());
    }
    public long getTime() {
        return time;
    }

    private int[] createArray() {
        array = new int[currentSize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(currentSize);
        }
        return array;
    }
    private int[] directMergeSortingAlgorithm(int[] array){
        buffer1 = Arrays.copyOf(array, array.length);
        buffer2 = new int[array.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, array.length);
        return result;
    }

    private static int[] mergeSortInner(int[] buffer1, int[] buffer2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }

    private long sortTime(int[] array) {
        long startTime = System.nanoTime() /10;
        directMergeSortingAlgorithm(array);
        long endTime = System.nanoTime() /10;
        return  endTime - startTime;
    }
}
