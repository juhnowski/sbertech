/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

import java.util.NoSuchElementException;

/** 
 * Абстрактный класс пустого итератора
 * 
 * @author Илья Юхновский
 */
abstract class AbstractEmptyIterator {
 
    /**
     * Конструктор
     */
    protected AbstractEmptyIterator() {
        super();
    }

    public boolean hasNext() {
        return false;
    }

    public Object next() {
        throw new NoSuchElementException("Итератор не сдержит элементов");
    }

    public boolean hasPrevious() {
        return false;
    }

    public Object previous() {
        throw new NoSuchElementException("Итератор не сдержит элементов");
    }

    public int nextIndex() {
        return 0;
    }

    public int previousIndex() {
        return -1;
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("метод add() не поддерживается для пустого итератора");
    }

    public void set(Object obj) {
        throw new IllegalStateException("Итератор не сдержит элементов");
    }

    public void remove() {
        throw new IllegalStateException("Итератор не сдержит элементов");
    }

    public Object getKey() {
        throw new IllegalStateException("Итератор не сдержит элементов");
    }

    public Object getValue() {
        throw new IllegalStateException("Итератор не сдержит элементов");
    }

    public Object setValue(Object value) {
        throw new IllegalStateException("Итератор не сдержит элементов");
    }

    public void reset() {
        // ничего не делаем
    }

}
