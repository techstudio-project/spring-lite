package com.techstudio.springlite.util;

import com.techstudio.springlite.beans.BeanInstantiationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lj
 * @date 2020/2/7
 */
public class BeanUtils {

    private BeanUtils() {
    }

    public static <T> T instantiateClass(Constructor<T> ctor, Object... args)
            throws BeanInstantiationException {

        try {
            return ctor.newInstance(args);
        }
        catch (InstantiationException ex) {
            throw new BeanInstantiationException("Is it an abstract class?", ex);
        }
        catch (IllegalAccessException ex) {
            throw new BeanInstantiationException("Is the constructor accessible?", ex);
        }
        catch (IllegalArgumentException ex) {
            throw new BeanInstantiationException("Illegal arguments for constructor", ex);
        }
        catch (InvocationTargetException ex) {
            throw new BeanInstantiationException("Constructor threw exception", ex.getTargetException());
        }
    }

}
