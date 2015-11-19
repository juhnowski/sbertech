/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;

import testapp.cache.collections.iterators.MapIterator;
import java.util.Map;

/**
 * Определяет map по которому можно перемещаться напрямую, без необходимости 
 * создавать набор записей.
 *
 * @author Илья Юхновский
 */
public interface IterableMap extends Map {

    /**
     * Получить <code>MapIterator</code> по map.
     * 
     * @return итератор по map
     */
    MapIterator mapIterator();
    
}
