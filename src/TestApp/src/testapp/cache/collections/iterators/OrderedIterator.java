/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

import java.util.Iterator;

/**
 * Интерфейс уполрядоченного итератора
 *
 * @author Илья Юхновский
 */
public interface OrderedIterator extends Iterator {

    /**
     * Проверить наличие предыдущей записи относительно текущей позиции
     * 
     * @return <code>true</code> если в итераторе есть предыдущий элемент 
     * относительно текущей позиции
     */
    boolean hasPrevious();

    /**
     * Получить предыдущий, относительно текущей позиции, элемент из коллекции.
     *
     * @return предыдущий, относительно текущей позиции, элемент
     * @throws java.util.NoSuchElementException если достигнут конец итератора
     */
    Object previous();

}
