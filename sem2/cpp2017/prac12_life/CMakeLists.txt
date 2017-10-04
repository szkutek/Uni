cmake_minimum_required(VERSION 3.7)
project(prac12_life)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES figure.h figure.cpp grid.h grid.cpp life.cpp)
add_executable(prac12_life ${SOURCE_FILES})