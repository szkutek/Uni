cmake_minimum_required(VERSION 3.7)
project(prac11)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp surf.h surf.cpp surface.h surface.cpp)
add_executable(prac11 ${SOURCE_FILES})