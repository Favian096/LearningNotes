cmake_minimum_required(VERSION 3.19)
project(AlgorithmNotes)

set(CMAKE_CXX_STANDARD 17)

# 2.RecursionPartition chapter
add_executable(Factorial RecursionPartition/Factorial.cpp)
add_executable(Fibonacci RecursionPartition/Fibonacci.cpp)
add_executable(Perm RecursionPartition/Perm.cpp)
add_executable(divide RecursionPartition/divide.cpp)
add_executable(hanoi RecursionPartition/hanoi.cpp)
add_executable(binarySearch RecursionPartition/binarySearch.cpp)
add_executable(bigNumMultiply RecursionPartition/bigNumMultiply.cpp)
add_executable(strassenMatrixMutiply RecursionPartition/strassenMatrixMutiply.cpp)
add_executable(chessBoard RecursionPartition/chessBoard.cpp)
add_executable(mergeSort RecursionPartition/mergeSort.cpp)
add_executable(quickSort RecursionPartition/quickSort.cpp)