#!/usr/bin/env ruby

class Jawna
	def initialize(slowo)
		@slowo = slowo
	end

	def to_s
		@slowo
	end

	def zaszyfruj(klucz)
		zaszyfrowane = ""
		for i in 0...@slowo.length
			zaszyfrowane += klucz[@slowo[i]]
		end
		return Zaszyfrowane.new(zaszyfrowane)
	end
end


class Zaszyfrowane
	def initialize(slowo)
		@slowo = slowo
	end

	def to_s
		@slowo
	end

	def odszyfruj(klucz)
		odszyfrowane = ""
		for i in 0...@slowo.length
			odszyfrowane += klucz.key(@slowo[i])
		end
		return Jawna.new(odszyfrowane)
	end
end


klucz = { "a" => "b", "b" => "r", "r" => "y", "y" => "u", "u" => "a"}

s = Jawna.new("ruby")
z = s.zaszyfruj(klucz)
puts z

# t = Zaszyfrowane.new(z)
t = Zaszyfrowane.new("yaru")
puts t.odszyfruj(klucz)
