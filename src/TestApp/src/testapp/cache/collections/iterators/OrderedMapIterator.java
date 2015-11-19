/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections.iterators;

/**
 * Интерфейс упорядоченного итератора для Map
 * Итератор поддерживает двунаправленное перемещение по итератору
 * 
 * @author Илья Юхновский
 */
public interface OrderedMapIterator extends MapIterator, OrderedIterator {
    
    /**
     * Проверить наличие предыдущей записи относительно текущей позиции
     * 
     * @return <code>true</code> если в итераторе есть предыдущий элемент 
     * относительно текущей позиции
     */
    @Override
    boolean hasPrevious();

    /**
     * Получить предыдущий, относительно текущей позиции, элемент из коллекции.
     *
     * @return предыдущий, относительно текущей позиции, элемент
     * @throws java.util.NoSuchElementException если достигнут конец итератора
     */
    @Override
    Object previous();

}
