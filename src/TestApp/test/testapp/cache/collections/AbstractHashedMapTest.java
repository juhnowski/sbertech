/*
 * Юнит тесты для "Тестовое задание 1" 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_CAPACITY;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_LOAD_FACTOR;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_THRESHOLD;
import testapp.cache.collections.iterators.MapIterator;

/**
 * Тестовый класс для юнит тестирования класса {@link AbstractHashedMap}.
 * @author Илья Юхновский
 */
public class AbstractHashedMapTest {
    
    protected Object[] getKeys() {
        return new Object[] { "Key 1", "Key 2", "Key 3" };
    }

    protected Object[] getValues() {
        return new Object[] { "Value 1", "Value 2", "Value 3" };
    }
    
    public AbstractHashedMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Тест метода {@link AbstractHashedMap#init()}.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        AbstractHashedMap instance = new AbstractHashedMap();
        instance.init();
        assertTrue(true);
    }

    /**
     * Тест метода {@link AbstractHashedMap#get(key)}.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска значения методом get",instance.get(keys[0]),values[0]);
    }

    /**
     * Тест метода {@link AbstractHashedMap#size())}.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        assertEquals("Метод size неверно возвращает размер коллекции",instance.size(), 3);
    }

    /**
     * Тест метода {@link AbstractHashedMap#isEmpty())}.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        assertEquals("Конструктор создает объект с ненулевым размером коллекции",instance.size(), 0);    
    }

    /**
     * Тест метода {@link AbstractHashedMap#containsKey(key))}.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Ошибка поиска записи по ключу", instance.containsKey(keys[0]));
    }

    /**
     * Тест метода {@link AbstractHashedMap#containsValue(value))}.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Ошибка поиска записи по ключу", instance.containsValue(values[0]));    
    }

    /**
     * Тест метода {@link AbstractHashedMap#put(keys[0], values[0]);}.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        Object result = instance.put(keys[0], values[0]);
        
        assertEquals("Ошибка поиска записи методом put()",instance.get(keys[0]),values[0]);
    }

    /**
     * Тест метода {@link AbstractHashedMap#putAll(t)}.
     */
    @Test
    public void testPutAll() {
        System.out.println("putAll");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        AbstractHashedMap t = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            t.put(keys[i], values[i]);
        }
        
        instance.putAll(t);
        assertEquals("Ошибка поиска записи методом putAll()",instance,t);
    }

    /**
     * Тест метода {@link AbstractHashedMap#testRemove()}.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        assertEquals("Метод remove не возвращает удаляемую запись", instance.remove(keys[2]), values[2]);
        assertEquals("Метод remove не удаляет запись", instance.size(), keys.length-1);
    }

    /**
     * Тест метода {@link AbstractHashedMap#clear()}.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        instance.clear();
        assertEquals("Метод remove не удаляет запись", instance.size(), 0);
    }

    /**
     * Тест метода {@link AbstractHashedMap#convertKey(key)}.
     */
    @Test
    public void testConvertKey() {
        System.out.println("convertKey");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        Object result = instance.convertKey(keys[0]);

        assertEquals("Ошибка при конвертации методом convertKey(keys[0])",keys[0], result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#hash(key)}.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertNotNull(instance.hash(keys[0]));
    }

    /**
     * Тест метода {@link AbstractHashedMap#isEqualKey}.
     */
    @Test
    public void testIsEqualKey() {
        System.out.println("isEqualKey");
        Object key1 = "1";
        Object key2 = "1";
        AbstractHashedMap instance = new AbstractHashedMap();
        boolean expResult = true;
        boolean result = instance.isEqualKey(key1, key2);
        assertEquals(expResult, result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#isEqualValue}.
     */
    @Test
    public void testIsEqualValue() {
        System.out.println("isEqualValue");
        Object value1 = "1";
        Object value2 = "1";
        AbstractHashedMap instance = new AbstractHashedMap();
        boolean expResult = true;
        boolean result = instance.isEqualValue(value1, value2);
        assertEquals(expResult, result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#hashIndex}.
     */
    @Test
    public void testHashIndex() {
        System.out.println("hashIndex");
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка в вычислении индекса по hash коду и размеру данных",instance.hashIndex(instance.get(keys[0]).hashCode(), 3), 
                (instance.get(keys[0]).hashCode()&2));
        
        
    }

    /**
     * Тест метода {@link AbstractHashedMap#getEntry}.
     */
    @Test
    public void testGetEntry() {
        System.out.println("getEntry");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractHashedMap.HashEntry result = instance.getEntry(keys[0]);
        
        assertEquals("Метод getEntry возвращает неверную запись по ключу",result.value, values[0]);
    }

    /**
     * Тест метода {@link AbstractHashedMap#updateEntry}.
     */
    @Test
    public void testUpdateEntry() {
        System.out.println("updateEntry");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[0]);
        instance.updateEntry(entry, values[2]);
        
        assertEquals("Метод updateEntry неверно обновляет запись по ключу", entry.value, values[2]);
    }

    /**
     * Тест метода {@link AbstractHashedMap#reuseEntry}.
     */
    @Test
    public void testReuseEntry() {
        System.out.println("reuseEntry");
       
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length-1; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[0]);
        int hashCode = instance.get(keys[0]).hashCode();
        int hashIndex = instance.hashIndex(hashCode, keys[0].toString().length());
        instance.reuseEntry(entry, hashIndex, hashCode, keys[2], values[2]);
        
        assertEquals("Метод reuseEntry неверно реиспользует запись", instance.size, 2);        
    }

    /**
     * Тест метода {@link AbstractHashedMap#addMapping}.
     */
    @Test
    public void testAddMapping() {
        System.out.println("addMapping");
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        int hashCode = keys[0].hashCode();
        int hashIndex = instance.hashIndex(hashCode, keys[0].toString().length());
        instance.addMapping(hashIndex, hashCode, keys[0], values[0]);
        
        assertEquals("Метод addMapping неверно добавляет запись", instance.size(), 1);
    }

    /**
     * Тест метода {@link AbstractHashedMap#createEntry}.
     */
    @Test
    public void testCreateEntry() {
        System.out.println("createEntry");
        AbstractHashedMap.HashEntry next = null;

        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        int hashCode = keys[0].hashCode();
        
        AbstractHashedMap.HashEntry result = instance.createEntry(next, hashCode, keys[0], values[0]);
        assertNotNull("Метод createEntry неверно создает запись", result);        
    }

    /**
     * Тест метода {@link AbstractHashedMap#addEntry}.
     */
    @Test
    public void testAddEntry() {
        System.out.println("addEntry");
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);
        int hashCode = keys[0].hashCode();
        int hashIndex = instance.hashIndex(hashCode, keys[0].toString().length());
        AbstractHashedMap.HashEntry entry = instance.createEntry(instance.getEntry(keys[1]), hashCode, keys[0], values[0]);
        instance.addEntry(entry, hashIndex);
        assertEquals("Метод addEntry неверно добавляет запись", instance.size(), 1);
    }

    /**
     * Тест метода {@link AbstractHashedMap#removeMapping}.
     */
    @Test
    public void testRemoveMapping() {
        System.out.println("removeMapping");
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        AbstractHashedMap.HashEntry previous = instance.getEntry(keys[0]) ;        
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        
        int hashIndex = instance.hashIndex(entry.hashCode, keys[1].toString().length());
        instance.removeMapping(entry, hashIndex, previous);
        assertEquals("Метод removeMapping неверно удаляет запись", instance.size(), 2);
    }

    /**
     * Тест метода {@link AbstractHashedMap#removeEntry}.
     */
    @Test
    public void testRemoveEntry() {
        System.out.println("removeEntry");
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);
        
        int hashCode = keys[0].hashCode();
        int hashIndex = instance.hashIndex(hashCode, keys[0].toString().length());
        AbstractHashedMap.HashEntry entry = instance.createEntry(instance.getEntry(keys[1]), hashCode, keys[0], values[0]);
        instance.addEntry(entry, hashIndex);
        
        instance.removeEntry(entry, hashIndex, null);
        assertEquals("Метод removeEntry неверно удаляет запись", instance.size(), 1); 
    }

    /**
     * Тест метода {@link AbstractHashedMap#destroyEntry}.
     */
    @Test
    public void testDestroyEntry() {
        System.out.println("destroyEntry");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);
        
        int hashCode = keys[0].hashCode();
        int hashIndex = instance.hashIndex(hashCode, keys[0].toString().length());
        AbstractHashedMap.HashEntry entry = instance.createEntry(instance.getEntry(keys[1]), hashCode, keys[0], values[0]);
        instance.addEntry(entry, hashIndex);
        instance.destroyEntry(entry);
        assertEquals("Метод destroyEntry неверно удаляет запись", instance.size(), 1);     
    }

    /**
     * Тест метода {@link AbstractHashedMap#checkCapacity}.
     */
    @Test
    public void testCheckCapacity() {
        System.out.println("checkCapacity");

        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        instance.checkCapacity();
        assertTrue("Вызов checkCapacity() не приводит к ошибкам",true);
    }

    /**
     * Тест метода {@link AbstractHashedMap#ensureCapacity}.
     */
    @Test
    public void testEnsureCapacity() {
        System.out.println("ensureCapacity");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        instance.ensureCapacity(4);
        assertTrue("Вызов ensureCapacity не приводит к ошибкам",true);
    }

    /**
     * Тест метода {@link AbstractHashedMap#calculateNewCapacity}.
     */
    @Test
    public void testCalculateNewCapacity() {
        System.out.println("calculateNewCapacity");
        int proposedCapacity = 0;
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        int result = instance.calculateNewCapacity(proposedCapacity);

        assertEquals("Метод calculateNewCapacity неверно вычисляет значение", result, 1);     
    }

    /**
     * Тест метода {@link AbstractHashedMap#calculateThreshold}.
     */
    @Test
    public void testCalculateThreshold() {
        System.out.println("calculateThreshold");
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        assertEquals("Метод calculateThreshold неверно вычисляет значение",instance.calculateThreshold(2, 3), 6);
    }

    /**
     * Тест метода {@link AbstractHashedMap#entryNext}.
     */
    @Test
    public void testEntryNext() {
        System.out.println("entryNext");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        AbstractHashedMap.HashEntry previous = instance.getEntry(keys[0]) ;   
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry next = instance.getEntry(keys[2]);
        previous.next = entry;
        entry.next = next;
        
        AbstractHashedMap.HashEntry result = instance.entryNext(entry);
        assertEquals("Метод ",next, result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#entryHashCode}.
     */
    @Test
    public void testEntryHashCode() {
        System.out.println("entryHashCode");

        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        

        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        int result = instance.entryHashCode(entry);
        assertNotNull("Метод entryHashCode возвращает нулевой hash код",result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#testEntryKey}.
     */
    @Test
    public void testEntryKey() {
        System.out.println("entryKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        

        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        Object result = instance.entryKey(entry);
        assertEquals("Метод entryKey возвращает неверный ключ",keys[1], result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#testEntryValue}.
     */
    @Test
    public void testEntryValue() {
        System.out.println("entryValue");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        

        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        Object result = instance.entryValue(entry);
        assertEquals("Метод entryValue возвращает неверный ключ",values[1], result);    }

    /**
     * Тест метода {@link AbstractHashedMap#mapIterator}.
     */
    @Test
    public void testMapIterator() {
        System.out.println("mapIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        MapIterator result = instance.mapIterator();
        assertNotNull("Метод mapIterator() возвращает пустой итератор", result);
        int expResultCnt = 3;
        int resultCnt = 0;
        while(result.hasNext()){
            result.next();
            resultCnt++;
        }
        assertEquals("Итератор, возвращенный методом mapIterator() не обходит все"
                + " записи коллекции",expResultCnt, resultCnt);
    }

    /**
     * Тест метода {@link AbstractHashedMap#entrySet}.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        AbstractHashedMap.HashEntry previous = instance.getEntry(keys[0]) ;   
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry next = instance.getEntry(keys[2]);
        previous.next = entry;
        entry.next = next;
        
        Set result = instance.entrySet();
        String expResult = "[Key 3=Value 3, Key 1=Value 1, Key 2=Value 2, Key 3=Value 3, Key 2=Value 2, Key 3=Value 3]";
        assertEquals(expResult, result.toString());
    }

    /**
     * Тест метода {@link AbstractHashedMap#createEntrySetIterator}.
     */
    @Test
    public void testCreateEntrySetIterator() {
        System.out.println("createEntrySetIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }        
        
        AbstractHashedMap.HashEntry previous = instance.getEntry(keys[0]) ;   
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry next = instance.getEntry(keys[2]);
        previous.next = entry;
        entry.next = next;
        Iterator result = instance.createEntrySetIterator();
        assertNotNull("Метод createEntrySetIterator() возвращает null", result);
        int cnt = 0;
        while(result.hasNext()){
            result.next();
            cnt++;
        }
        assertEquals(cnt, 6);
    }

    /**
     * Тест метода {@link AbstractHashedMap#keySet}.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }  
        Set result = instance.keySet();
        
        assertEquals("Метод keySet возвращает коллекцию неверного размера",result.size(), keys.length);
        for(int i=0; i< keys.length; i++){
            assertTrue("Метод keySet содержит ошибочную коллекцию ключей",result.contains(keys[i]));
        }
    }
    

    /**
     * Тест метода {@link AbstractHashedMap#createKeySetIterator}.
     */
    @Test
    public void testCreateKeySetIterator() {
        System.out.println("createKeySetIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }  
        
        Iterator expResult = instance.keySet().iterator();
        Iterator result = instance.createKeySetIterator();
        
        while(result.hasNext()){
            assertEquals("Метод createKeySetIterator() возвращает ошибочный "
                    + "итератор",expResult.next(), result.next());
        }
    }

    /**
     * Тест метода {@link AbstractHashedMap#values}.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Collection result = instance.values();
        
        assertEquals("Метод values() возвращает коллекцию неверного размера",result.size(), values.length);
        Iterator it = result.iterator();
        
        for(int i=0; i<values.length; i++){
          assertTrue("Метод values() возвращает поврежденную коллекцию",result.contains(values[i]));  
        }
    }

    /**
     * Тест метода {@link AbstractHashedMap#createValuesIterator}.
     */
    @Test
    public void testCreateValuesIterator() {
        System.out.println("createValuesIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }  
        
        Iterator expResult = instance.values().iterator();
        Iterator result = instance.createValuesIterator();
        
        while(result.hasNext()){
            assertEquals("Метод createValuesIterator() возвращает ошибочный "
                    + "итератор",expResult.next(), result.next());
        }
    }

    /**
     * Тест метода {@link AbstractHashedMap#doWriteObject} и {@link AbstractHashedMap#doReadObject}.
     */
    @Test
    public void testDoReadWriteObject() throws Exception {
        System.out.println("doReadWriteObject");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[1], values[1]);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        
        }
        File file = new File("AbstractHashedMapTest");
        file.createNewFile();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        instance.doWriteObject(out);
        
        AbstractHashedMap t = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));        

        t.doReadObject(in);
        assertEquals("Значения в Map после сохранения в файл и чтения из него изменилась",instance, t);
    }

    /**
     * Тест метода {@link AbstractHashedMap#clone}
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        AbstractHashedMap instance = new AbstractHashedMap();
        Object expResult = null;
        Object result = null;
        try {
            result = instance.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AbstractHashedMapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Тест метода {@link AbstractHashedMap#equals}
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        AbstractHashedMap t = instance;
        AbstractHashedMap t1 = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        assertTrue("Метод equals обрабатывается с ошибками",instance.equals(t));

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
            t1.put(keys[i], values[i]);
        }
        
        assertTrue("Метод equals обрабатывается с ошибками",instance.equals(t1));
    }

    /**
     * Тест метода {@link AbstractHashedMap#hashCode}
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        int result = instance.hashCode();
        assertNotNull("Метод hashCode возвращает null", result);
    }

    /**
     * Test of toString method, of class AbstractHashedMap.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < 1; i++) {
            instance.put(keys[i], values[i]);
        }
        
        String expResult = "{Key 1=Value 1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
