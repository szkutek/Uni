
#include "rational.h"
#include "matrix.h"

int main( int argc, char* argv [ ] )
{
   rational r1( 2, 6 );
   rational r2( 4, 3 ); 
   rational r3( 5, 6 );
   rational r4( 1, 2 );

   std::cout << r1 << "\n";

   matrix m1 = { { 2, 3 }, { 4, 6 } };
   std::cout << m1 << "\n";

   matrix m2 = { { 5,4 }, { 6, rational(1,2) } }; 
   std::cout << m2 << "\n";

   matrix m3 = { { 4,5}, { -1, 2 }};

   std::cout << m1. determinant( ) << "\n";

   std::cout << m1. adjugate( ) << "\n";
   //std::cout << m1. inverse( ) * m1 << "\n";

   //rational z1( 2, 0 );
   //std::cout << z1 << "\n";

   matrix mx1 = { { rational(1,2), rational(-2,7) }, { rational(1,3), rational(2,8) } };
   matrix mx2 = { { rational(-1,3), rational(2,5) }, { rational(2,7), rational(-1,7) } };
	
   std::cout << mx1 * mx2 << "\n";
   std::cout << mx1.inverse() << "\n";

  
   
   return 0;
}

