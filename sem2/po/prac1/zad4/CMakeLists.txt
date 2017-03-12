cmake_minimum_required(VERSION 3.6)
project(zad4)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c DrzewoBinarne.c DrzewoBinarne.h)
add_executable(zad4 ${SOURCE_FILES})