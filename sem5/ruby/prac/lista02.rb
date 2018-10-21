def statystyka_slow(filename)
  puts filename.split('.')[0]
  dict = Hash.new(0)
  counter = 0
  File.foreach('lista2-teksty/' + filename) do |line|
    line.chomp.split.each do |word|
      if word.size > 3
        dict[word] += 1
        counter += 1
      end
    end
  end
  dict.sort_by {|_, v| v}.reverse!.each do |k, v|
    stat = 1.0 * v / counter
    if stat < 0.001
      break
    end
    puts '%s, %s' % [stat, k.to_s]
  end
end

def test_statystyka_slow()
  # statystyka_slow('krol-ryszard-ii.txt')
  statystyka_slow('makbet.txt')
  # statystyka_slow('miarka-za-miarke.txt')
  # statystyka_slow('sen-nocy-letniej.txt')
end


class Phonebook
  def initialize()
    @phone_numbers = {}
    @groups = {}
  end

  def add_person(name, phone_number, group_list)
    if phone_number.nil? # or phone_number.size != 9
      return nil
    end
    @phone_numbers[name] = phone_number
    group_list.each do |g|
      if @groups.key? g
        @groups[g] << name
      else
        @groups[g] = [name]
      end
    end
    true
  end

  def get_number(name)
    @phone_numbers[name]
  end

  def get_group(group)
    @groups[group]
  end

  def get_persons_groups(name)
    res = []
    @groups.each do |group, name_list|
      if name_list.include? name
        res << group
      end
    end
    res
  end
end

def test_phonebook
  phonebook = Phonebook.new
  # phonebook.add_person('Ola', '1234', %w(friends work))
  # phonebook.add_person('Aga', '5678', %w(friends))
  # puts phonebook.get_number('Ola')
  # p phonebook.get_group('friends')
  running = true
  puts 'New phonebook'
  while running
    print '=> '
    input = gets.chomp.split(' ')
    case input[0]
    when 'add'
      a = phonebook.add_person(input[1], input[2], input[3..-1])
      if a
        puts 'New person added.'
      else
        puts 'Error: Phone number is incorrect.'
      end
    when 'get'
      puts phonebook.get_number(input[1])
    when 'get_group'
      puts phonebook.get_group(input[1])
    when 'get_groups'
      puts phonebook.get_persons_groups(input[1])
    when 'end'
      running = false
    else
      puts 'End with \'end\''
    end
  end
end

test_phonebook
# test_statystyka_slow