cmake_minimum_required(VERSION 3.7)
project(prac9)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp bigint.cpp bigint.h polynomial.h powerproduct.cpp powerproduct.h rational.cpp rational.h)
add_executable(prac9 ${SOURCE_FILES} ${STRING})

