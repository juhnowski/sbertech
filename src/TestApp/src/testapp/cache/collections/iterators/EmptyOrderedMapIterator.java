/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

/** 
 * Реализация пустого упорядоченного итератора для Map
 * 
 * @author Илья Юхновский
 */
public class EmptyOrderedMapIterator extends AbstractEmptyIterator implements OrderedMapIterator, ResettableIterator {

    public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();

    /**
     * Конструктор.
     */
    protected EmptyOrderedMapIterator() {
        super();
    }

}
