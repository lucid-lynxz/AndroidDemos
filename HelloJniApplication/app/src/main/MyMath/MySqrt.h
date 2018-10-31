//
// Created by Lynxz on 2018/8/29.
//

#ifndef HELLOJNIAPPLICATION_MYSQRT_H
#define HELLOJNIAPPLICATION_MYSQRT_H

#include <string>

double mySqrt(double);

#include <string>
#include <sstream>

template <typename T>
std::string to_string(T value)
{
    std::ostringstream os ;
    os << value ;
    return os.str() ;
}

class MyException {

public:
    int16_t errNo;
    std::string errMsg;

    MyException(int16_t errNo, std::string errMsg);
};

#endif //HELLOJNIAPPLICATION_MYSQRT_H
