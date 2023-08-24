package singleton

import java.lang.reflect.InvocationTargetException


class LazyInitializedSingletonExample private constructor() {

    init {
        if (instance != null) {
            throw RuntimeException(
                "Use getInstance() method to get the single instance of this class."
            )
        }
    }

    companion object {
        private var instance: LazyInitializedSingletonExample? = null
        fun getInstance(): LazyInitializedSingletonExample {

            return instance ?: synchronized(this) {
                instance ?: LazyInitializedSingletonExample().also {
                    instance = it
                }
            }
        }
    }
}

class LazyInitializedSingletonExampleTest() {
    fun test() {
        //Create the 1st instance
        //Create the 1st instance
        val instance1 = LazyInitializedSingletonExample.getInstance()
        //Create 2nd instance using Java Reflection API.
        //Create 2nd instance using Java Reflection API.
        var instance2: LazyInitializedSingletonExample? = null
        try {
            val clazz = LazyInitializedSingletonExample::class.java
            val cons = clazz.getDeclaredConstructor()
            cons.isAccessible = true
            instance2 = cons.newInstance()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
        //now lets check the hash key.
        //now lets check the hash key.
        println("Instance 1 hash:" + instance1.hashCode())
        println("Instance 2 hash:" + instance2.hashCode())
    }
}

