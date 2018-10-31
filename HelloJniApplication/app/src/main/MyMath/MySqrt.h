//
// Created by Lynxz on 2018/8/29.
//

#ifndef HELLOJNIAPPLICATION_MYSQRT_H
#define HELLOJNIAPPLICATION_MYSQRT_H

#include <string>

class MyException : public std::exception {

public:
    int16_t errNo;
    std::string errMsg;

    MyException(int16_t errNo, std::string errMsg);
};

// 声明中通过 throw(e1,e2) 来说明可能抛出的异常类型集合,也可不写,表示本方法可能抛出异常
// 若写成 throw(), 则表示本方法不会抛出异常
// 若实现时抛出了未声明的异常,比如e3,则程序默认走 std::terminate(),崩溃
double mySqrt(double) throw(MyException);

#include <string>
#include <sstream>

template<typename T>
std::string to_string(T value) {
    std::ostringstream os;
    os << value;
    return os.str();
}


#endif //HELLOJNIAPPLICATION_MYSQRT_H
