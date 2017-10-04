#!/usr/bin/env ruby
require 'gnuplot'

class Proc
	def value(x)
		return self.call(x)
	end

	def zerowe(a,b,e=0.0001)
		# a<b, f(a)*f(b)<0 
		n = 1
		if value(a).abs < e
			return a
		elsif value(b).abs < e 
			return b
		end

		while n<10000 do
			c = (a+b)/2
			fc = value(c)
			if fc.abs <= e
				return c
			end
			if fc * value(a) > 0 
				a = c
			else 
				b = c
			end
			n+=1
		end

		return nil
	end

	def pole(a,b)
		n = 1000.0
		s = 0.0
		dx = (b-a)/n
		# for i in (0...n)
		for i in (1..n)
			s += self.call(a+i*dx)
		end
		s = (s + (self.call(a) + self.call(b))/2.0 ) * dx
		return s
	end

	def pochodna(x)
		h = 0.000001
		return (self.call(x+h) - self.call(x))/h
	end

	def rysuj(a,b)
		range = '[%f : %f]'
		Gnuplot.open do |gp|
		  Gnuplot::Plot.new( gp ) do |plot|


		    plot.xrange range % [a,b]
		    plot.title  "Function plot"
		    plot.ylabel "x"
		    plot.xlabel "f(x)"

		    x = (a..b).collect{|v| v.to_f}
		    y = x.collect{|v| self.call(v)}

		    plot.data << Gnuplot::DataSet.new( [x,y] ) do |ds|
		      ds.with = "lines"
		      ds.linewidth = 2
		    end
		  end
		end
	end
end


# p = Proc.new{|x| x*x*Math.sin(x)}
p = Proc.new{|x| x*x-25}
puts p.value(3.14)
# puts p.zerowe(2.9,4)
puts p.zerowe(0,6)
puts p.pole(0,1)
puts p.pochodna(4)
p.rysuj(-15,15)