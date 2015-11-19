/*
 * Юнит тесты для "Тестовое задание 1" 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_CAPACITY;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_LOAD_FACTOR;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_THRESHOLD;
import testapp.cache.collections.AbstractLinkedMap.LinkEntry;
import testapp.cache.collections.iterators.MapIterator;
import testapp.cache.collections.iterators.OrderedMapIterator;

/**
 * Тестовый класс для юнит тестирования класса {@link AbstractLinkedMap}.
 * @author Илья Юхновский
 */
public class AbstractLinkedMapTest {
    
    public AbstractLinkedMapTest() {
    }
    
    protected Object[] getKeys() {
        return new Object[] { "Key 1", "Key 2", "Key 3" };
    }

    protected Object[] getValues() {
        return new Object[] { "Value 1", "Value 2", "Value 3" };
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
     * Тест метода {@link AbstractLinkedMap#testInit()}.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.init();
        assertTrue(true);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#containsValue}.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Ошибка поиска значения методом containsValue",instance.containsValue(values[0]));
    }

    /**
     * Тест метода {@link AbstractLinkedMap#clear}.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        instance.clear();
        assertEquals("Метод clear() не полностью очищает коллекцию",instance.size(), 0);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#firstKey}.
     */
    @Test
    public void testFirstKey() {
        System.out.println("firstKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Object result = instance.firstKey();
        assertEquals("Метод firstKey возвращает неверное значение",keys[0], result);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#lastKey}.
     */
    @Test
    public void testLastKey() {
        System.out.println("lastKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Object result = instance.lastKey();
        assertEquals("Метод lastKey возвращает неверное значение",keys[2], result);    }

    /**
     * Тест метода {@link AbstractLinkedMap#nextKey}.
     */
    @Test
    public void testNextKey() {
        System.out.println("nextKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        assertEquals("Метод nextKey возвращает неверное значение",keys[1], instance.nextKey(keys[0]));    
        assertEquals("Метод nextKey возвращает неверное значение",keys[2], instance.nextKey(keys[1]));    
    }

    /**
     * Тест метода {@link AbstractLinkedMap#previousKey}.
     */
    @Test
    public void testPreviousKey() {
        System.out.println("previousKey");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        assertEquals("Метод previousKey возвращает неверное значение",keys[0], instance.previousKey(keys[1]));    
        assertEquals("Метод previousKey возвращает неверное значение",keys[1], instance.previousKey(keys[2]));     }

    /**
     * Тест метода {@link AbstractLinkedMap#getEntry}.
     */
    @Test
    public void testGetEntry() {
        System.out.println("getEntry");
        int index = 0;
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        AbstractLinkedMap.LinkEntry result = instance.getEntry(index);
        assertEquals("Метод getEntry возвращает неверное значение",result.key,keys[0]);
        assertEquals("Метод getEntry возвращает неверное значение",result.value,values[0]);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#addEntry}.
     */
    @Test
    public void testAddEntry() {
        System.out.println("addEntry");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        
        for (int i = 0; i < keys.length-1; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractLinkedMap.HashEntry entry = new AbstractLinkedMap.LinkEntry(null, keys[2].hashCode(), keys[2], values[2]);
        instance.addEntry(entry, DEFAULT_CAPACITY-1);
        
        String expResult="{Key 1=Value 1, Key 2=Value 2, Key 3=Value 3}";
        System.out.println("instance.size()="+instance.size());
        assertEquals("Метод addEntry не добавляет запись в коллекцию",expResult,instance.toString());
    }

    /**
     * Тест метода {@link AbstractLinkedMap#createEntry}.
     */
    @Test
    public void testCreateEntry() {
        System.out.println("createEntry");
        AbstractHashedMap.HashEntry next = null;
        int hashCode = 0;
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        instance.put(keys[0], values[0]);
        
        AbstractHashedMap.HashEntry expResult = instance.getEntry(keys[0]);
        AbstractHashedMap.HashEntry result = instance.createEntry(next, hashCode, keys[0], values[0]);
        assertEquals(expResult, result);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#removeEntry}.
     */
    @Test
    public void testRemoveEntry() {
        System.out.println("removeEntry");
        int hashIndex = 0;
        AbstractHashedMap.HashEntry next = null;
        int hashCode = 0;
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractHashedMap.HashEntry entryBefore = instance.getEntry(keys[0]);
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry entryNext = instance.getEntry(keys[2]);
        
        instance.removeEntry(entryBefore, hashIndex, entry);
        instance.removeEntry(entry, hashIndex, entryNext);
        
        String expResult="{Key 3=Value 3}";
        assertEquals("Ошибки в работе метода removeEntry",expResult, instance.toString());
    }

    /**
     * Тест метода {@link AbstractLinkedMap#entryBefore}.
     */
    @Test
    public void testEntryBefore() {
        System.out.println("entryBefore");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        AbstractHashedMap.HashEntry entryBefore = instance.getEntry(keys[0]);
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry entryNext = instance.getEntry(keys[2]);

        AbstractLinkedMap.LinkEntry result = instance.entryBefore((LinkEntry)entry);
        assertEquals("Метод entryBefore работает с ошибками",entryBefore, result);
    }

    /**
     * Тест метода {@link AbstractLinkedMap#entryAfter}.
     */
    @Test
    public void testEntryAfter() {
        System.out.println("entryAfter");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        AbstractHashedMap.HashEntry entryBefore = instance.getEntry(keys[0]);
        AbstractHashedMap.HashEntry entry = instance.getEntry(keys[1]);
        AbstractHashedMap.HashEntry entryNext = instance.getEntry(keys[2]);

        AbstractLinkedMap.LinkEntry result = instance.entryAfter((LinkEntry)entry);
        assertEquals("Метод entryAfter работает с ошибками",entryNext, result);    
    }

    /**
     * Тест метода {@link AbstractLinkedMap#mapIterator}.
     */
    @Test
    public void testMapIterator() {
        System.out.println("mapIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        MapIterator result = instance.mapIterator();
        int i=0;
        while(result.hasNext()){
            assertEquals("Метод mapIterator возвращает ошибочный iterator",result.next(),keys[i++]);
        }
        
    }

    /**
     * Тест метода {@link AbstractLinkedMap#orderedMapIterator}.
     */
    @Test
    public void testOrderedMapIterator() {
        System.out.println("orderedMapIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        OrderedMapIterator result = instance.orderedMapIterator();
        int i=0;
        while(result.hasNext()){
            assertEquals("Метод orderedMapIterator возвращает ошибочный iterator",result.next(),keys[i++]);
        }
    }

    /**
     * Test of createEntrySetIterator method, of class AbstractLinkedMap.
     */
    @Test
    public void testCreateEntrySetIterator() {
        System.out.println("createEntrySetIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Iterator result = instance.createEntrySetIterator();
        int i = 0;
        while(result.hasNext()){
            AbstractLinkedMap.LinkEntry es = (AbstractLinkedMap.LinkEntry)result.next();
            assertEquals("Метод createEntrySetIterator возвращает ошибочный iterator",es.getKey(),keys[i++]);
        }
    }

    /**
     * Тест метода {@link AbstractLinkedMap#createKeySetIterator}.
     */
    @Test
    public void testCreateKeySetIterator() {
        System.out.println("createKeySetIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Iterator result = instance.createKeySetIterator();
        int i = 0;
        while(result.hasNext()){
            assertEquals("Метод createKeySetIterator возвращает ошибочный iterator",result.next(),keys[i++]);
        }    
    }

    /**
     * Test of createValuesIterator method, of class AbstractLinkedMap.
     */
    @Test
    public void testCreateValuesIterator() {
        System.out.println("createValuesIterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        AbstractLinkedMap instance = new AbstractLinkedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        Iterator result = instance.createValuesIterator();
        int i = 0;
        while(result.hasNext()){
            assertEquals("Метод createValuesIterator возвращает ошибочный iterator",result.next(),values[i++]);
        }
    }
    
}
