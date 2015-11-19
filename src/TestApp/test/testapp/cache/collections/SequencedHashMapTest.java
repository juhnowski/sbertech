/*
 * Юнит тесты для "Тестовое задание 1" 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.cache.collections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тестовый класс для юнит тестирования класса {@link SequencedHashMap}.
 * @author Илья Юхновский
 */
public class SequencedHashMapTest {
    
    protected Object[] getKeys() {
        return new Object[] { "Key 1", "Key 2", "Key 3" };
    }

    protected Object[] getValues() {
        return new Object[] { "Value 1", "Value 2", "Value 3" };
    }
    
    public Map makeEmptyMap() {
        return new SequencedHashMap();
    }
 
    public SequencedHashMapTest() {
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
     * Тест метода {@link SequencedHashMap#size()}.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Object[] keys = getKeys();
        int expResult = keys.length;
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < expResult; i++) {
            instance.put(keys[i], values[i]);
        }

        assertEquals("Метод size() не вернул ожидаемое значение",
                     expResult, instance.size());
    }

    /**
     * Тест метода {@link SequencedHashMap#isEmpty}.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        SequencedHashMap instance = new SequencedHashMap();
        boolean result = instance.isEmpty();
        assertEquals("Создался не нулевой SequencedHashMap",true, result);
    }

    /**
     * Тест метода {@link SequencedHashMap#testContainsKey()}.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Ошибка поиска ключа методом containsKey",instance.containsKey(keys[0]));
    }

    /**
     * Тест метода {@link SequencedHashMap#testContainsValue()}.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue("Ошибка поиска значения методом containsKey",instance.containsValue(values[0]));
    }

    /**
     * Тест метода {@link SequencedHashMap#get(o)}.
     */
    @Test
    public void testGet_Object() {
        System.out.println("get");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска значения методом get",instance.get(keys[0]),values[0]);
    }

    /**
     * Тест метода {@link SequencedHashMap#getFirst()}.
     */
    @Test
    public void testGetFirst() {
        System.out.println("getFirst");
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getFirst()",instance.getFirst().getKey(),keys[0]);

    }

    /**
     * Тест метода {@link SequencedHashMap#getFirstKey()}.
     */
    @Test
    public void testGetFirstKey() {
        System.out.println("getFirstKey");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getFirstKey()",instance.getFirstKey(),keys[0]);
    }

    /**
     * Тест метода {@link SequencedHashMap#getFirstValue()}.
     */
    @Test
    public void testGetFirstValue() {
        System.out.println("getFirstValue");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getFirstValue()",instance.getFirstValue(),values[0]);
    }

    /**
     * Тест метода {@link SequencedHashMap#getLast()}.
     */
    @Test
    public void testGetLast() {
        System.out.println("getLast");

        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getLast()",instance.getLast().getKey(),keys[keys.length-1]);

    }

    /**
     * Тест метода {@link SequencedHashMap#getLastKey()}.
     */
    @Test
    public void testGetLastKey() {
        System.out.println("getLastKey");

        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getLastKey()",instance.getLastKey(),keys[keys.length-1]);

    }

    /**
     * Тест метода {@link SequencedHashMap#getLastValue()}.
     */
    @Test
    public void testGetLastValue() {
        System.out.println("getLastValue");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertEquals("Ошибка поиска записи методом getLastValue()",instance.getLastValue(),values[values.length-1]);
    }

    /**
     * Тест метода {@link SequencedHashMap#put()}.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();

        Object result = instance.put(keys[0], values[0]);
        
        assertEquals("Ошибка поиска записи добавленной методом put()",instance.getFirst().getKey(),keys[0]);
    }

    /**
     * Тест метода {@link SequencedHashMap#remove()}.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        assertEquals("Метод remove не возвращает удаляемую запись", instance.remove(keys[2]), values[2]);
        assertEquals("Метод remove не удаляет запись", instance.size(), keys.length-1);
    }

    /**
     * Тест метода {@link SequencedHashMap#putAll()}.
     */
    @Test
    public void testPutAll() {
        System.out.println("putAll");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        SequencedHashMap t = new SequencedHashMap();

        for (int i = 0; i < keys.length; i++) {
            t.put(keys[i], values[i]);
        }
        
        instance.putAll(t);
        assertEquals("Ошибка поиска записи методом putAll()",instance,t);
    }

    /**
     * Тест метода {@link SequencedHashMap#clear()}.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        SequencedHashMap t = new SequencedHashMap();

        for (int i = 0; i < keys.length; i++) {
            t.put(keys[i], values[i]);
        }
        
        instance.clear();
        assertEquals("Ошибка очистки записей методом clear()", instance.size(),0);
    }

    /**
     * Тест метода {@link SequencedHashMap#equals(obj)}.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        SequencedHashMap t = new SequencedHashMap();

        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
            t.put(keys[i], values[i]);
        }
        
        assertTrue("Ошибка сравнения методом equals",instance.equals(t));
    }

    /**
     * Тест метода {@link SequencedHashMap#hashCode()}.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        assertTrue(instance.hashCode()>0);
    }
     
    
    /**
     * Тест метода {@link SequencedHashMap#toString()}.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Object[] keys = getKeys();
        Object[] values = getValues();
        String expectedResult = "[Key 1=Value 1,Key 2=Value 2,Key 3=Value 3]";
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        assertEquals(instance.toString(),expectedResult);
    }

    /**
     * Тест метода {@link SequencedHashMap#keySet()}.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");

        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        String result = instance.keySet().toString();
        String expectedResult = "[Key 1, Key 2, Key 3]";
        
        assertEquals("Ошибка в методе keySet()",result,expectedResult);
        
    }

    /**
     * Тест метода {@link SequencedHashMap#values()}.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Object[] keys = getKeys();
        Object[] values = getValues();

        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

       String expectedResult = "[Value 1, Value 2, Value 3]";
       assertEquals("Ошибка метода values",instance.values().toString(), expectedResult);
    }

    /**
     * Тест метода {@link SequencedHashMap#entrySet()}.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");

        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        String result = instance.entrySet().toString();
        String expectedResult = "[[Key 1=Value 1], [Key 2=Value 2], [Key 3=Value 3]]";

        assertEquals("Ошибка метода entrySet()",result, expectedResult);
    }

    /**
     * Тест метода {@link SequencedHashMap#clone()}.
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        
        Object expResult = instance;
        Object result = instance.clone();
        assertEquals("Ошибка метода clone", expResult, result);
    }

    /**
     * Тест метода {@link SequencedHashMap#get(index)}.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int index = 0;
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        Object expResult = null;
        Object result = instance.get(index);
        assertEquals("Ошибка в методе get(index)", instance.get(index), keys[index]);
    }

    /**
     * Тест метода {@link SequencedHashMap#getValue(index)}.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int index = 0;
        
        Object[] keys = getKeys();
        Object[] values = getValues();

        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        Object expResult = null;
        Object result = instance.getValue(index);
        assertEquals("Ошибка в методе get(index)", instance.getValue(index), values[index]);
    }

    /**
     * Тест метода {@link SequencedHashMap#indexOf(key)}.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        
        int expResult = 0;
        int result = instance.indexOf(keys[0]);
        assertEquals(expResult, result);
    }

    /**
     * Тест метода {@link SequencedHashMap#iterator()}.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }

        int expResult = 3;
        Iterator result = instance.iterator();
        while (result.hasNext()) {
            result.next();
            expResult--;
        }
        
        assertEquals("Ошибка в реализации итератора", expResult, 0);
    }

    /**
     * Тест метода {@link SequencedHashMap#lastIndexOf(key)}.
     */
    @Test
    public void testLastIndexOf() {
        System.out.println("lastIndexOf");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[0], values[i]);
        }
        
        int result = instance.lastIndexOf(keys[0]);
        assertEquals("Ошибка в реализации метода lastIndexOf",result,0);
        
    }

    /**
     * Тест метода {@link SequencedHashMap#sequence()}.
     */
    @Test
    public void testSequence() {
        System.out.println("sequence");
        Object[] keys = getKeys();
        Object[] values = getValues();
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
        
        List result = instance.sequence();
        assertEquals("Метод sequence() возвращает",keys.length, result.size());
        
        for (int i = 0; i < result.size(); i++) {
            assertEquals("Ключ " + i + " не тот же самый что в  Map",
                         keys[i], result.get(i));
        }
    }

    /**
     * Тест метода {@link SequencedHashMap#remove(index)}.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        Object[] keys = getKeys();
        Object[] values = getValues();
        int index = 0;
        
        SequencedHashMap instance = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            instance.put(keys[i], values[i]);
        }
 
        Object result = instance.remove(index);
        assertEquals("Удаляется неверное значение",result, values[index]);
        assertEquals("Удаление записи не приводит к уменьшению массива",instance.size(), 2);
    }

    /**
     * Тест метода {@link SequencedHashMap#remove(index)}.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadWriteExternal() throws Exception {
        System.out.println("readWriteExternal");
        
        Object[] keys = getKeys();
        Object[] values = getValues();
        File file = new File("tst");
        file.createNewFile();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));

        SequencedHashMap map = new SequencedHashMap();
        SequencedHashMap t = new SequencedHashMap();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }

        if (!(map instanceof Serializable)) return;  

        map.writeExternal(out);

        ObjectInput in = new ObjectInputStream(new FileInputStream(file));        
        t.readExternal(in);
        
        assertEquals("Map после сохранения в файл и чтения из него изменилась",map, t);
    }
    
}
