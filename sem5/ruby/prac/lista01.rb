def pascal(n) # zad 2
  puts '1'
  x = [1, 1]
  puts x.join(' ')

  n -= 2
  while n > 0
    x1 = x[0..-2]
    x2 = x[1..-1]

    x = [1] + x1.zip(x2).map {|a, b| a + b} + [1]
    puts x.join(' ')
    n -= 1
  end
  puts
end


def podzielniki(n) # zad 4
  res = []
  n2 = n
  for i in 2..n2
    if n % i == 0
      while n % i == 0
        n = n / i
      end
      res.push(i)
    end
  end
  p res
end


def tests_pascal()
  pascal(3)
  pascal(5)
  pascal(6)
end

def tests_podzielniki()
  podzielniki(1025)
  podzielniki(32)
  podzielniki(123456)
end

tests_pascal
tests_podzielniki
