package lynxz.org.recyclerviewdemo

import java.util.*

/**
 * Created by Lynxz on 2016/11/16.
 * description :
 */

object Demo {
    @JvmStatic fun main(args: Array<String>) {
        val data = ArrayList<String>()
        data.add("hello")
        data.add("hello1")
        data.add("hello2")
        data.add("hello3")

        data.removeAt(2)
        data.remove("hello")
    }
}
