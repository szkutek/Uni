
Flags = -Wall -Wextra -std=c++11
#-Wreturn-type -pedantic -pedantic-errors -Wundef 
CPP = g++


main : Makefile   main.o   surf.o   surface.o
	$(CPP) $(Flags) -o main   main.o  surf.o surface.o

main.o : Makefile   main.cpp   surf.h
	$(CPP) -c $(Flags) main.cpp -o  main.o

surf.o : Makefile  surf.cpp   surf.h 
	$(CPP) -c $(Flags) surf.cpp -o  surf.o

surface.o : Makefile  surface.cpp   surface.h surf.h
	$(CPP) -c $(Flags) surface.cpp -o  surface.o

