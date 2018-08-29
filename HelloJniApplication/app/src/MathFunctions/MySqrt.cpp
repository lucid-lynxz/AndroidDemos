//
// Created by Lynxz on 2018/8/29.
//

#include "MySqrt.h"
#include "../main/cpp/AndroidLog.h"

double mySqrt(double x) {
    if (x <= 0) {
        return 0;
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