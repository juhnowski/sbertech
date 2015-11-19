/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;

/**
 * Определяет простую пару ключ-значение.
 * 
 * @author Илья Юхновский
 */
public interface KeyValue {

    /**
     * Получить ключ из пары.
     *
     * @return ключ 
     */
    Object getKey();

    /**
     * Получить значение из пары.
     *
     * @return значение
     */
    Object getValue();

}
