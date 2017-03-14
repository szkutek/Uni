#include <cmath>
#include "rational.h"


int rational::gcd( int n1, int n2 ){
	while (n2!=0) {
		int k = n1 % n2;
		n1 = n2;
		n2 = k;
    	}
	return n1;
}

void rational::normalize( ){
	if( this->denum == 0 ) 
		throw std::runtime_error( "division by 0" );

	if (this->num == 0){
	  this->denum = 1;
          return;
	}

	int k = rational::gcd(this->num, this->denum );
	//if (k>1){
		this->num   /= k;
		this->denum /= k;
	//}

 	if (this->denum < 0){
		this->num = - this->num;
		this->denum = - this->denum;
        }

}


rational operator - ( rational r ){
	return rational( -r.num, r.denum );
}

rational operator + ( const rational& r1, const rational& r2 ){
	int denum = r1.denum * r2.denum;
	int num = r1.num * r2.denum + r2.num * r1.denum;
	return rational(num, denum);
}

rational operator - ( const rational& r1, const rational& r2 ){
	int denum = r1.denum * r2.denum;
	int num = r1.num * r2.denum - r2.num * r1.denum;
	return rational(num, denum);
}

rational operator * ( const rational& r1, const rational& r2 ){
	return rational(r1.num * r2.num, r1.denum * r2.denum);
}

rational operator / ( const rational& r1, const rational& r2 ){
	return rational(r1.num * r2.denum, r1.denum * r2.num);
}

bool operator == ( const rational& r1, const rational& r2 ){
	return (r1.num == r2.num) && (r1.denum == r2.denum);
}

bool operator != ( const rational& r1, const rational& r2 ){
	//return (r1.num != r2.num) || (r1.denum != r2.denum);
	//return !(operator==(r1,r2)); 
	return !(r1==r2);
}

std::ostream& operator << ( std::ostream& stream, const rational& r ){
	if (r.denum == 1){
		stream << r.num;
	}
	else {
		stream << r.num << "/" << r.denum;
	}
	return stream; 
}

