cmake_minimum_required(VERSION 3.6)
project(zad1)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c kolekcja.h kolekcja.c)
add_executable(zad1 ${SOURCE_FILES})