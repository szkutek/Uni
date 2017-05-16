#!/usr/bin/env ruby

class Proc
	def value(x,y)
		return self.call(x,y)
	end

	def objetosc(a,b,c,d)
		
	end

	def poziomica(a,b,c,d,wysokosc)
		
	end
end


# p = Proc.new{|x| x*x*Math.sin(x)}
p = Proc.new{|x,y| x+y}
puts p.value(3,1)