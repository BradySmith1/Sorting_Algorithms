import random
from timeit import default_timer as timer


def bubble_sort(number_list):
    start_time = timer()
    for i in range(len(number_list)):
        for j in range(len(number_list) - 1):
            if number_list[j] > number_list[j + 1]:
                number_list[j], number_list[j + 1] = number_list[j + 1], number_list[j]
    end_time = timer()
    return number_list, (end_time - start_time)


def insertion_sort(number_list):
    start_time = timer()
    for i in range(1, len(number_list)):
        key = number_list[i]
        j = i - 1
        while j >= 0 and key < number_list[j]:
            number_list[j + 1] = number_list[j]
            j -= 1
        number_list[j + 1] = key
    end_time = timer()
    return number_list, (end_time - start_time)


def merge_sort(number_list):
    start_time = timer()
    if len(number_list) > 1:
        mid = len(number_list) // 2
        left = number_list[:mid]
        right = number_list[mid:]

        merge_sort(left)
        merge_sort(right)

        i = j = k = 0

        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                number_list[k] = left[i]
                i += 1
            else:
                number_list[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            number_list[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            number_list[k] = right[j]
            j += 1
            k += 1
    end_time = timer()
    return number_list, (end_time - start_time)


def output_file(name, bubble_list, bubble_elapsed):
    with open(name + ".txt", "w") as file:
        file.write("Sorted List: " + str(bubble_list) + "\n")
        file.write("Time Elapsed: " + str(bubble_elapsed) + "\n")


def main():
    number_size = int(input("Enter the number of elements: "))
    number_list = []
    for i in range(number_size):
        number_list.append(random.randint(1, 100000))
    bubble_list, bubble_elapsed = bubble_sort(number_list)
    insertion_list, insertion_elapsed = insertion_sort(number_list)
    merge_list, merge_elapsed = merge_sort(number_list)
    output_file("Bubble Sort", bubble_list, bubble_elapsed)
    output_file("Insertion Sort", insertion_list, insertion_elapsed)
    output_file("Merge Sort", merge_list, merge_elapsed)


if __name__ == '__main__':
    main()
