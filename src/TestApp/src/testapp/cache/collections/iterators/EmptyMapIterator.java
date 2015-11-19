/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

/** 
 * Реализация абстрактного пустого итератора
 * 
 * @author Илья Юхновский
 */
public class EmptyMapIterator extends AbstractEmptyIterator implements MapIterator, ResettableIterator {

    public static final MapIterator INSTANCE = new EmptyMapIterator();

    /**
     * Конструктор.
     */
    protected EmptyMapIterator() {
        super();
    }

}
