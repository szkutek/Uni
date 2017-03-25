cmake_minimum_required(VERSION 3.6)
project(prac4)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        main.cpp
        string.cpp
        string.h)

add_executable(prac4 ${SOURCE_FILES})