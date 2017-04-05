cmake_minimum_required(VERSION 3.7)
project(prac5)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp tree.cpp tree.h)
set(STRING ../prac4/string.h ../prac4/string.cpp)
add_executable(prac5 ${SOURCE_FILES} ${STRING})

