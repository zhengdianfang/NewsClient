package com.zhengdianfang.newsclientdemo.utils

import java.lang.reflect.Field

import junit.framework.TestCase.fail

object ReflectionUtils {
    fun Value(classToReflect: Class<*>, fieldNameValueToFetch: String): Any? {
        try {
            val reflectField = reflectField(classToReflect, fieldNameValueToFetch)
            reflectField!!.isAccessible = true
            return reflectField.get(classToReflect)
        } catch (e: Exception) {
            fail("Failed to reflect $fieldNameValueToFetch")
        }

        return null
    }

    // get an instance value
    fun reflectValue(objToReflect: Any, fieldNameValueToFetch: String): Any? {
        try {
            val reflectField = reflectField(objToReflect.javaClass, fieldNameValueToFetch)
            return reflectField!!.get(objToReflect)
        } catch (e: Exception) {
            fail("Failed to reflect $fieldNameValueToFetch")
        }

        return null
    }

    // find a field in the class tree
    fun reflectField(classToReflect: Class<*>, fieldNameValueToFetch: String): Field? {
        try {
            var reflectField: Field? = null
            var classForReflect: Class<*>? = classToReflect
            do {
                try {
                    reflectField = classForReflect!!.getDeclaredField(fieldNameValueToFetch)
                } catch (e: NoSuchFieldException) {
                    classForReflect = classForReflect!!.superclass
                }

            } while (reflectField == null || classForReflect == null)
            reflectField.isAccessible = true
            return reflectField
        } catch (e: Exception) {
            fail("Failed to reflect $fieldNameValueToFetch from $classToReflect")
        }

        return null
    }

    // set a value with no setter
    fun refectSetValue(objToReflect: Any, fieldNameToSet: String, valueToSet: Any) {
        try {
            val reflectField = reflectField(objToReflect.javaClass, fieldNameToSet)
            reflectField!!.set(objToReflect, valueToSet)
        } catch (e: Exception) {
            fail("Failed to reflectively set $fieldNameToSet=$valueToSet")
        }

    }

}