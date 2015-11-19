/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;
import java.util.Map;
/**
 * Ограниченный Map
 * 
 * @author Илья Юхновский
 */
public interface BoundedMap extends Map{
    /**
     * Коллекция полная
     * @return true если количество записей достигло максимального значения
     */
    boolean isFull();
    
    /**
     * Получить максимальное количество записей в Map
     * @return Максимальное количество записей в Map 
     */
    int maxSize();
}
