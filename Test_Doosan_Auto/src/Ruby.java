
public class Ruby {

}

require "spreadsheet";
book = Spreadsheet.open("C:/Ruby22/Test_Data.xls")
sheet1 = book.worksheet("Sheet1") # can use an index or worksheet name
rows = sheet1.count;
puts rows;
i=2
while i <= rows
  print "#{i} "
  book.worksheet('Sheet1').each do |row|
    puts "################"
    puts row.at(0)
if row.at(0) == "Module"
  puts "Leave it because its Column"
    else
      CHK1 = row.at(0)
      CHK2 = ".rb"
      Check = "#{CHK1}#{CHK2}"
      puts Check
    require './Check'
    CHK1 (row)
end
    puts "&&&&&&&&&&&&&&&&&&&"
  i+=1
end
end
