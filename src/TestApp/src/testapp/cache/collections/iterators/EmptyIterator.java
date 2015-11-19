/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
 
package testapp.cache.collections.iterators;

import java.util.Iterator;


/** 
 * Реализация пустого итератора
 * 
 * @author Илья Юхновский
 */
public class EmptyIterator extends AbstractEmptyIterator implements ResettableIterator {

    public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
    public static final Iterator INSTANCE = RESETTABLE_INSTANCE;

    /**
     * Конструктор.
     */
    protected EmptyIterator() {
        super();
    }

}
