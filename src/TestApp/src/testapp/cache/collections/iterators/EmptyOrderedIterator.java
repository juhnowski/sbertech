/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

/** 
 * Реализация пустого упорядоченного итератора
 * 
 * @author Илья Юхновский
 */
public class EmptyOrderedIterator extends AbstractEmptyIterator implements OrderedIterator, ResettableIterator {

    public static final OrderedIterator INSTANCE = new EmptyOrderedIterator();

    /**
     * Конструктор.
     */
    protected EmptyOrderedIterator() {
        super();
    }

}
