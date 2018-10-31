//
// Created by Lynxz on 2018/8/29.
//

#include "MySqrt.h"
#include "../cpp/AndroidLog.h"
#include <string>

MyException::MyException(int16_t errNo, std::string errMsg) {
    this->errNo = errNo;
    this->errMsg = errMsg;
}

double mySqrt(double x) throw(MyException) {
    if (x <= 0) {
        throw MyException(-1, "input invalide: must be >=0 but input: " + to_string(x));
//        return 0;
    }

    double result;
    double delta;
    result = x;

    // do ten iterations
    int i;
    for (i = 0; i < 10; ++i) {
        if (result <= 0) {
            result = 0.1;
        }
        delta = x - (result * result);
        result = result + 0.5 * delta / result;
    }
    LOGD("mysqrt(%g) = %g", x, result);
    return result;
}