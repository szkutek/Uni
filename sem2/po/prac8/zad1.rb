#!/usr/bin/env ruby

class Fixnum

	def czynniki
		tab = []

		for i in 1..self
			if self%i == 0
				tab.push(i)
			end
		end
		return tab
	end

	def ack(y)
		if self==0
			return y+1
		elsif y==0
			return (self-1).ack(1)
		else
			return (self-1).ack(self.ack(y-1))
		end
	end

	def doskonala
		czynniki = self.czynniki
		czynniki.inject(0, :+) # suma elem tablicy
		return czynniki.inject(0, :+) - self == self
	end

	def slownie
		return ['zero'] if self == 0

		cyfry = ['zero', "jeden", "dwa", "trzy", "cztery", "piec",
			'szesc', 'siedem', 'osiem', 'dziewiec']
		
		slownie = []
		liczba = self
		while liczba > 0
			slownie.push(cyfry[liczba % 10])
			liczba = liczba/10
		end
		return slownie.reverse

	end
end





puts "6.czynniki"
p 6.czynniki
puts "15.czynniki"
p 15.czynniki
puts ""

puts "2.ack(1)"
puts 2.ack(1)
puts ""

puts "5.doskonala:"
puts 5.doskonala
puts "6.doskonala:"
puts 6.doskonala
puts ""

puts "128.slownie:"
p 128.slownie
puts "63400.slownie:"
p 63400.slownie
puts "0.slownie:"
p 0.slownie