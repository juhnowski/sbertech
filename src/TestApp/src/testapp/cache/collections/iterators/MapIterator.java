/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

import java.util.Iterator;

/**
 * Итератор для <code>Map</code>.
 *
 * @author Илья Юхновский
 */
public interface MapIterator extends Iterator {
    
    /**
     * Проверить есть ли следующий элемент отнсительно текущей позиции
     *
     * @return <code>true</code> если следующий элемент есть в итераторе
     */
    @Override
    boolean hasNext();

    /**
     * Получить следующий <em>key</em> из <code>Map</code>.
     *
     * @return ключ следующей записи итератора
     * @throws java.util.NoSuchElementException если итерация завершена
     */
    @Override
    Object next();

    //-----------------------------------------------------------------------
    /**
     * Получить ключ текущей записи, возвращенной методом <code>next()</code>.
     *
     * @return ключ текущей записи
     * @throws IllegalStateException если предварительно не был вызван метод
     * <code>next()</code>
     */
    Object getKey();

    /**
     * Получить значение текущей записи, возвращенной методом <code>next()</code>.
     *
     * @return текущее значение
     * @throws IllegalStateException если предварительно не был вызван метод 
     * <code>next()</code>
     */
    Object getValue();

    //-----------------------------------------------------------------------
    /**
     * Удалить запись из <code>Map</code>.
     *Этот метод должен вызываться после метода <code>next()</code>.
     *
     * @throws UnsupportedOperationException если удаление не поддерживается map
     * @throws IllegalStateException если предварительно не был вызван метод 
     * <code>next()</code>
     * @throws IllegalStateException если <code>remove()</code> вызывается повторно
     */
    @Override
    void remove();
    
    /**
     * Установить значение в пару по ключу.
     *
     * @param value  новое значение
     * @return старое значение
     * @throws UnsupportedOperationException если метод setValue не поддерживается map
     * @throws IllegalStateException если предварительно не был вызван метод 
     * <code>next()</code>
     */
    Object setValue(Object value);

}
