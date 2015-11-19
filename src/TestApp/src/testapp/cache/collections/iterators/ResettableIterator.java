/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

import java.util.Iterator;

/** 
 * Итератор реализующий восстановление к первоначальному состоянию.
 *
 * @since Commons Collections 3.0
 * @version $Revision: 646777 $ $Date: 2008-04-10 13:33:15 +0100 (Thu, 10 Apr 2008) $
 * 
 * @author Stephen Colebourne
 */
public interface ResettableIterator extends Iterator {

    /**
     * Сбрасывает итератор в исходное состояние.
     */
    public void reset();

}
