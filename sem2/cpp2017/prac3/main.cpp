
#include "stack.h"
#include <iostream>

int main( int argc, char* argv [ ] )
{
	stack s = {1,2,3};
	std::cout << s.top() << "\n"; 

	s.pop();
	s.pop();
	s.top() = 10;
	std::cout << s.top() << "\n"; 
	s.pop();

	s.push(4);

	std::cout << s.top() << "\n";


	stack s2;

	s2.push(150); 
	std::cout << s2.top() << "\n";


	stack s3(s2);

	std::cout << s3.top() << "\n";
	s3.push(32); 
	std::cout << s3.top() << "\n";


	return 0;
}
