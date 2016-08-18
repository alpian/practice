#include <fstream>
#include <iostream>
#include <sstream>
#include <vector>


class Day {
public:
    Day(std::string const& date, std::string const& open, std::string const& high, std::string const& low, std::string const& close);
    std::string toString();
    
private:
    std::string date;
    double open;
    double high;
    double low;
    double close;
};

Day::Day(std::string const& date, std::string const& open, std::string const& high, std::string const& low, std::string const& close) {
    this->date = date;
    this->open = std::stod(open);
    this->high = std::stod(high);
    this->low = std::stod(low);
    this->close = std::stod(close);
}

std::string Day::toString() {
    std::stringstream buffer;
    buffer << "Day [" << date << " " << open << " " << high << " " << low << " " << close << "]";
    return buffer.str();
}

int main(int argc, const char * argv[]) {
    if (argc < 2) {
        std::cout << "Insufficient arguments, stocks file required.\n";
        std::exit(1);
    }
    std::cout << argv[1] << "\n";
    std::ifstream stocksFile(argv[1]);
    std::string line;
    std::vector<Day*> days;
    std::getline(stocksFile, line); // ignore first line
    while (std::getline(stocksFile, line)) {
        std::istringstream lineStream(line);
        std::string date,open,high,low,close;
        std::getline(lineStream, date, ',');
        std::getline(lineStream, open, ',');
        std::getline(lineStream, high, ',');
        std::getline(lineStream, low, ',');
        std::getline(lineStream, close, ',');
        
        Day* day = new Day(date, open, high, low, close);
        days.push_back(day);
        
        std::cout << day->toString() << "\n";
    }
    // insert code here...
    std::cout << "Hello, World!\n";
    return 0;
}
