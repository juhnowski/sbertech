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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_CAPACITY;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_LOAD_FACTOR;
import static testapp.cache.collections.AbstractHashedMap.DEFAULT_THRESHOLD;

/**
 * Тестовый класс для юнит тестирования класса {@link LRUMap}.
 * @author Илья Юхновский
 */
public class LRUMapTest {
    
    public LRUMapTest() {
    }
    
    private final int maxItems = 100;
    
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
     * Тест метода {@link AbstractLinkedMap#get}.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска значения методом containsValue",instance.get(keys[0]),values[0]);        
    }

    /**
     * Тест метода {@link LRUMap#moveToMRU(testapp.cache.collections.AbstractLinkedMap.LinkEntry)}.
     */
    @Test
    public void testMoveToMRU() {
        System.out.println("moveToMRU");
        
        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractLinkedMap.LinkEntry entry = (AbstractLinkedMap.LinkEntry)instance.getEntry(keys[0]);
        instance.moveToMRU(entry);
        String expResult = "{Key 2=Value 2, Key 3=Value 3, Key 1=Value 1}";
        assertEquals("Метод moveToMRU не сдвигает записб в конец списка",expResult, instance.toString());
    }

    /**
     * Тест метода {@link LRUMap#updateEntry(testapp.cache.collections.AbstractHashedMap.HashEntry, java.lang.Object)}. 
     */
    @Test
    public void testUpdateEntry() {
        System.out.println("updateEntry");
        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        instance.put(keys[0], values[0]);
        
        AbstractLinkedMap.LinkEntry entry = (AbstractLinkedMap.LinkEntry)instance.getEntry(keys[0]);
        instance.updateEntry(entry, values[1]);
        assertEquals("Ошибка поиска значения методом containsValue",instance.get(keys[0]),values[1]);
    }

    /**
     * Тест метода {@link LRUMap#addMapping(int, int, java.lang.Object, java.lang.Object)}. 
     */
    @Test
    public void testAddMapping() {
        System.out.println("addMapping");
        int hashIndex = 0;
        int hashCode = 0;
        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        instance.addMapping(hashIndex, hashCode, keys[0], values[0]);
        
        assertEquals("Метод addMapping не добавляет запись в коллекцию",instance.getEntry(hashIndex).value,values[0]);
    }

    /**
     * Тест метода {@link LRUMap#reuseMapping(testapp.cache.collections.AbstractLinkedMap.LinkEntry, int, int, java.lang.Object, java.lang.Object) }. 
     */
    @Test
    public void testReuseMapping() {
        System.out.println("reuseMapping");
        int hashIndex = 0;
        int hashCode = 0;
        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        instance.addMapping(hashIndex, hashCode, keys[0], values[0]);
        AbstractLinkedMap.LinkEntry entry = instance.getEntry(hashIndex);
        instance.reuseMapping(entry, hashIndex, hashCode, keys[1], values[1]);
        assertEquals("Метод reuseMapping не меняет ключ у реиспользуемой записи",instance.getEntry(hashIndex).key,keys[1]);
        assertEquals("Метод reuseMapping не меняет значение у реиспользуемой записи",instance.getEntry(hashIndex).value,values[1]);
        
    }

    /**
     * Тест метода {@link LRUMap#removeLRU(testapp.cache.collections.AbstractLinkedMap.LinkEntry)  }. 
     */
    @Test
    public void testRemoveLRU() {
        System.out.println("removeLRU");
        LRUMap instance = new LRUMap(maxItems);
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        AbstractLinkedMap.LinkEntry entry = (AbstractLinkedMap.LinkEntry)instance.getEntry(keys[1]);
        assertTrue("Заглушка возвращает не true", instance.removeLRU(entry));
    }

    /**
     * Тест метода {@link LRUMap#isFull()}. 
     */
    @Test
    public void testIsFull() {
        System.out.println("isFull");
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        LRUMap instance = new LRUMap(keys.length);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Метод isFull неверно работает",instance.isFull());
    }

    /**
     * Тест метода {@link LRUMap#maxSize() }. 
     */
    @Test
    public void testMaxSize() {
        System.out.println("maxSize");
        LRUMap instance = new LRUMap(maxItems);
        assertEquals("Метод maxSize возвращает неверное значение",maxItems, instance.maxSize());
    }

    /**
     * Тест метода {@link LRUMap#isScanUntilRemovable() }. 
     */
    @Test
    public void testIsScanUntilRemovable() {
        System.out.println("isScanUntilRemovable");
        Object[] keys = getKeys();
        Object[] values = getValues();

        AbstractHashedMap instance = new AbstractHashedMap(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR,DEFAULT_THRESHOLD);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        LRUMap instance1 = new LRUMap(instance, true);
        LRUMap instance2 = new LRUMap(maxItems, true);
        LRUMap instance3 = new LRUMap(maxItems, DEFAULT_LOAD_FACTOR, true);
        assertTrue("Метод isScanUntilRemovable возвращает неверное значение",instance1.isScanUntilRemovable());
        assertTrue("Метод isScanUntilRemovable возвращает неверное значение",instance2.isScanUntilRemovable());
        assertTrue("Метод isScanUntilRemovable возвращает неверное значение",instance3.isScanUntilRemovable());
    }

    /**
     * Тест метода {@link LRUMap#clone() }. 
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        LRUMap instance = new LRUMap(maxItems);
        LRUMap t = (LRUMap)instance.clone();
        assertEquals("Метод clone работает неверно",instance, t);
    }


    /**
     * Тест методов {@link LRUMap#readObject(java.io.ObjectInputStream)} и {@link LRUMap#writeObject(java.io.ObjectOutputStream) }. 
     */
    @Test
    public void testDoReadWriteObject() throws Exception {
        System.out.println("doReadObject");
        Object[] keys = getKeys();
        Object[] values = getValues();

        LRUMap instance = new LRUMap(keys.length);
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        File file = new File("LRUMapTest");
        file.createNewFile();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        instance.doWriteObject(out);
        
        LRUMap t = new LRUMap(keys.length);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));        

        t.doReadObject(in);
        assertEquals("Значения в LRUMap после сохранения в файл и чтения из него изменилась",instance, t);
    }
    
}
