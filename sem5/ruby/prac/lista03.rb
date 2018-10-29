def pierwsza(n) # 2.1
  sito = (0..n).to_a
  sqrt_n = Math.sqrt(n)
  sito[1] = 0
  for i in 2..sqrt_n
    if sito[i]
      ((i * i)..n).step(i) do |j|
        sito[j] = 0
      end
    end
  end
  sito.select {|x| x > 0}
end

def doskonale(n) # 2.2
  sums = Array.new(n + 1, 1)
  sums[1] = 0
  for i in 2..n
    (i + i..n).step(i) do |j|
      sums[j] += i
    end
  end
  (0..n).select {|x| sums[x] == x}
end

def rozklad(n) # 3.1
  res = []
  n2 = n
  for i in 2..n2
    if n % i == 0
      e = 0
      while n % i == 0
        n = n / i
        e += 1
      end
      res.push([i, e])
    end
  end
  res
end

def zaprzyjaznione(n) # 3.2
  sums = Array.new(n + 1, 1)
  sums[1] = 0
  for i in 2..n
    (i + i..n).step(i) do |j|
      sums[j] += i
    end
  end
  res = []
  (2..n).each {|i|
    k = sums[i]
    if k < sums.size and sums[k] == i and k < i
      res << [k, i]
    end
  }
  res
end


p pierwsza(20)
p doskonale(10000)
p rozklad(756)
p zaprzyjaznione(1300)