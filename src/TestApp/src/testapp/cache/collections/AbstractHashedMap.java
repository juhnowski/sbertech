/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */

package testapp.cache.collections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import testapp.cache.collections.iterators.EmptyIterator;
import testapp.cache.collections.iterators.EmptyMapIterator;
import testapp.cache.collections.iterators.MapIterator;

/**
 * Абстрактная реализация класса HashedMap.
 * @author Илья Юхновский
 */
public class AbstractHashedMap extends AbstractMap implements IterableMap {
    
    protected static final String NO_NEXT_ENTRY = "Нет next()";
    protected static final String NO_PREVIOUS_ENTRY = "Нет previous()";
    protected static final String REMOVE_INVALID = "remove() должен вызываться "
            + "после next()";
    protected static final String GETKEY_INVALID = "getKey() должен вызываться "
            + "после next() и перед remove()";
    protected static final String GETVALUE_INVALID = "getValue() должен "
            + "вызываться после next() и перед remove()";
    protected static final String SETVALUE_INVALID = "setValue() должен "
            + "вызываться после next() и перед remove()";
    
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final int DEFAULT_THRESHOLD = 12;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int MAXIMUM_CAPACITY = 1 << 30;
    protected static final Object NULL = new Object();
    
    protected transient float loadFactor;
    protected transient int size;
    protected transient HashEntry[] data;
    protected transient int threshold;
    protected transient int modCount;
    protected transient EntrySet entrySet;
    protected transient KeySet keySet;
    protected transient Values values;

    /**
     * Конструктор. Использовать только для десериализации.
     */
    protected AbstractHashedMap() {
        super();
    }

    /**
     * Конструктор с определением количества ячеек, коэффициент заполнения и порогом, без валидации.
     * @param initialCapacity  количество ячеек, должна быть степень 2
     * @param loadFactor  коэффициент заполнения, должен быть &gt; 0.0f и как правило &lt; 1.0f
     * @param threshold  порог, должно быть разумным
     */
    protected AbstractHashedMap(int initialCapacity, float loadFactor, int threshold) {
        super();
        this.loadFactor = loadFactor;
        this.data = new HashEntry[initialCapacity];
        this.threshold = threshold;
        init();
    }

    /**
     * Конструктор с определением начального количества ячеек.
     *
     * @param initialCapacity  количество ячеек, должна быть степени 2
     * @throws IllegalArgumentException если начальное количество значений 1
     */
    protected AbstractHashedMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Конструктор с определением количества ячеек, коэффициентом заполнения
     * и порогом, с проверкой устанавливаемых конструктором значений.
     * 
     * @param initialCapacity  количество значений,должна быть степени 2
     * @param loadFactor  коэффициент заполнения, должен быть &gt; 0.0f и как правило &lt; 1.0f
     * @throws IllegalArgumentException если начальное количества ячеек меньше 1
     * @throws IllegalArgumentException если коэффициент заполнения меньше или равен 0
     */
    protected AbstractHashedMap(int initialCapacity, float loadFactor) {
        super();
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Начальное количества ячеек должна быть больше чем  0");
        }
        if (loadFactor <= 0.0f || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Коэффициент заполнения должен быть больше чем 0");
        }
        this.loadFactor = loadFactor;
        initialCapacity = calculateNewCapacity(initialCapacity);
        this.threshold = calculateThreshold(initialCapacity, loadFactor);
        this.data = new HashEntry[initialCapacity];
        init();
    }

    /**
     * Конструктор копирующий элементы из другого map.
     *
     * @param map  map для копирования
     * @throws NullPointerException если map нулевой
     */
    protected AbstractHashedMap(Map map) {
        this(Math.max(2 * map.size(), DEFAULT_CAPACITY), DEFAULT_LOAD_FACTOR);
        putAll(map);
    }

    /**
     * Инициализация подклассов во время создания, клонирования или десериализации.
     */
    protected void init() {
    }


    /**
     * Получить значение по ключу.
     * 
     * @param key  ключ
     * @return the значение, null если не найдено
     */
    @Override
    public Object get(Object key) {
        key = convertKey(key);
        int hashCode = hash(key);
        HashEntry entry = data[hashIndex(hashCode, data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Получить размер map.
     * 
     * @return размер
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверить пустой ли map на текущий момент.
     * 
     * @return true если размер map на данный момент 0
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Проверить содержит ли map искомый ключ. Поиск ключа в map.
     * 
     * @param key искомый ключ
     * @return true если map содержит искомый ключ
     */
    @Override
    public boolean containsKey(Object key) {
        key = convertKey(key);
        int hashCode = hash(key);
        HashEntry entry = data[hashIndex(hashCode, data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    /**
     * Проверить содержит ли map искомое значение. Поиск значения в map.
     * 
     * @param value искомое значение
     * @return true если map содержит искомое значение
     */
    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            for (int i = 0, isize = data.length; i < isize; i++) {
                HashEntry entry = data[i];
                while (entry != null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        } else {
            for (int i = 0, isize = data.length; i < isize; i++) {
                HashEntry entry = data[i];
                while (entry != null) {
                    if (isEqualValue(value, entry.getValue())) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Поместить пару ключ-значение в map.
     * 
     * @param key  ключ добавляемой пары
     * @param value значение добавляемой пары
     * @return ранее присвоенное значение для данного ключа, null если пара с 
     * таким ключом впервые размещается в map.
     */
    @Override
    public Object put(Object key, Object value) {
        key = convertKey(key);
        int hashCode = hash(key);
        int index = hashIndex(hashCode, data.length);
        HashEntry entry = data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
                Object oldValue = entry.getValue();
                updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        
        addMapping(index, hashCode, key, value);
        return null;
    }

    /**
     * Копировать все записи из map в текущую.
     * 
     * @param map  the map to add
     * @throws NullPointerException if the map is null
     */
    @Override
    public void putAll(Map map) {
        int mapSize = map.size();
        if (mapSize == 0) {
            return;
        }
        int newSize = (int) ((size + mapSize) / loadFactor + 1);
        ensureCapacity(calculateNewCapacity(newSize));
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Удалить запись из map.
     * 
     * @param key ключ удаляемой записи
     * @return возвращает значение удаляемой записи по заданному ключу, 
     * null если запись по заданному ключу не найдена
     */
    @Override
    public Object remove(Object key) {
        key = convertKey(key);
        int hashCode = hash(key);
        int index = hashIndex(hashCode, data.length);
        HashEntry entry = data[index];
        HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
                Object oldValue = entry.getValue();
                removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    /**
     * Очистить map, сбросить размер в 0 и обнулить ссылки для удаления хранимых
     * объектов сборщиком мусора.
     */
    @Override
    public void clear() {
        modCount++;
        HashEntry[] data = this.data;
        for (int i = data.length - 1; i >= 0; i--) {
            data[i] = null;
        }
        size = 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Конвертирует введенный ключ в другой объект для хранения в map.
     * 
     * @param key  ключ на конвертацию
     * @return сконвертированный ключ
     */
    protected Object convertKey(Object key) {
        return (key == null ? NULL : key);
    }
    
    /**
     * Получить hash код для заданного ключа.
     * 
     * @param key значение ключа
     * @return hash код
     */
    protected int hash(Object key) {
        int h = key.hashCode();
        h += ~(h << 9);
        h ^=  (h >>> 14);
        h +=  (h << 4);
        h ^=  (h >>> 10);
        return h;
    }
    
    /**
     * Сравнить два ключа.
     * 
     * @param key1  первый ключ
     * @param key2  второй ключ
     * @return true если равны
     */
    protected boolean isEqualKey(Object key1, Object key2) {
        return (key1 == key2 || key1.equals(key2));
    }
    
    /**
     * Сравнить два значения.
     * 
     * @param value1  первое значение
     * @param value2  второе значение
     * @return true если равны
     */
    protected boolean isEqualValue(Object value1, Object value2) {
        return (value1 == value2 || value1.equals(value2));
    }
    
    /**
     * Получить индекс в хранилище данных для определенного hashCode.
     * 
     * @param hashCode  hash код
     * @param dataSize  размер данных
     * @return индекс
     */
    protected int hashIndex(int hashCode, int dataSize) {
        return hashCode & (dataSize - 1);
    }
    
    /**
     * Получить запись пары по клбчу
     * 
     * @param key  ключ
     * @return запись, null если запись с таким ключом ненайдена
     */
    protected HashEntry getEntry(Object key) {
        key = convertKey(key);
        int hashCode = hash(key);
        HashEntry entry = data[hashIndex(hashCode, data.length)]; 
        while (entry != null) {
            if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Обновляет существущее значение записи пары
     * 
     * @param entry  запись для обновления
     * @param newValue  the new value to store
     */
    protected void updateEntry(HashEntry entry, Object newValue) {
        entry.setValue(newValue);
    }
    
    /**
     * Реиспользовать существующий мапинг, сохранение полностью новых данных.
     * 
     * @param entry  запись для обновления, не null
     * @param hashIndex  индекс в массиве данных
     * @param hashCode  hash код добавляемого ключа
     * @param key  ключ добавляемой пары
     * @param value  значение добавляемой пары
     */
    protected void reuseEntry(HashEntry entry, int hashIndex, int hashCode, Object key, Object value) {
        entry.next = data[hashIndex];
        entry.hashCode = hashCode;
        entry.key = key;
        entry.value = value;
    }
    
    /**
     * Добавить новый мапинг в map.
     * 
     * @param hashIndex  индекс под которым сохранить
     * @param hashCode  hash код ключа
     * @param key  ключ добавляемой пары
     * @param value  значение добавляемой пары
     */
    protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
        modCount++;
        HashEntry entry = createEntry(data[hashIndex], hashCode, key, value);
        addEntry(entry, hashIndex);
        size++;
        checkCapacity();
    }
    
    /**
     * Созать запись для сохранения пары ключ-значени.
     * 
     * @param next  следующая запись в последовательности
     * @param hashCode  использовать hash код
     * @param key  ключ добавляемой пары
     * @param value  значение добавляемой пары
     * @return вновь созданная запись
     */
    protected HashEntry createEntry(HashEntry next, int hashCode, Object key, Object value) {
        return new HashEntry(next, hashCode, key, value);
    }
    
    /**
     * Добавить запись в map.
     *
     * @param entry  добавляемая запись
     * @param hashIndex  индекс под которым сохранена запись в массиве
     */
    protected void addEntry(HashEntry entry, int hashIndex) {
        data[hashIndex] = entry;
        
    }
    
    /**
     * Удалить мапинг из map.
     * 
     * @param entry  удаляемая запись
     * @param hashIndex  индекс в структуре данных
     * @param previous  предыдущая запись в цепочке
     */
    protected void removeMapping(HashEntry entry, int hashIndex, HashEntry previous) {
        modCount++;
        removeEntry(entry, hashIndex, previous);
        size--;
        destroyEntry(entry);
    }
    
    /**
     * Удалить запись из цепочки по заданному индексу.
     * 
     * @param entry  удаляемая запись
     * @param hashIndex  индекс в структуре данных
     * @param previous  предыдущая запись в цепочке
     */
    protected void removeEntry(HashEntry entry, int hashIndex, HashEntry previous) {
        if (previous == null) {
            data[hashIndex] = entry.next;
        } else {
            previous.next = entry.next;
        }
    }
    
    /**
     * Подготовить запись к удалению сборщиком мусора
     * 
     * @param entry  разрушаемая запись
     */
    protected void destroyEntry(HashEntry entry) {
        entry.next = null;
        entry.key = null;
        entry.value = null;
    }
    
    /**
     * Проверить количество ячеек map и увеличть при необходимости.
     */
    protected void checkCapacity() {
        if (size >= threshold) {
            int newCapacity = data.length * 2;
            if (newCapacity <= MAXIMUM_CAPACITY) {
                ensureCapacity(newCapacity);
            }
        }
    }
    
    /**
     * Изменить размер структуры данных в соответствии с новым значением 
     * количества ячеек map .
     * 
     * @param newCapacity  новое количество ячеек в map (степеь двойки и меньше 
     * или равно максимаолно возможному значению для целого)
     */
    protected void ensureCapacity(int newCapacity) {
        int oldCapacity = data.length;
        if (newCapacity <= oldCapacity) {
            return;
        }
        if (size == 0) {
            threshold = calculateThreshold(newCapacity, loadFactor);
            data = new HashEntry[newCapacity];
        } else {
            HashEntry oldEntries[] = data;
            HashEntry newEntries[] = new HashEntry[newCapacity];

            modCount++;
            for (int i = oldCapacity - 1; i >= 0; i--) {
                HashEntry entry = oldEntries[i];
                if (entry != null) {
                    oldEntries[i] = null;  // gc
                    do {
                        HashEntry next = entry.next;
                        int index = hashIndex(entry.hashCode, newCapacity);  
                        entry.next = newEntries[index];
                        newEntries[index] = entry;
                        entry = next;
                    } while (entry != null);
                }
            }
            threshold = calculateThreshold(newCapacity, loadFactor);
            data = newEntries;
        }
    }

    /**
     * Вычисляет новое количество ячеек для map.
     * 
     * @param proposedCapacity  предполагаемое новое количество ячеек
     * @return нормализованное новое количество ячеек для map
     */
    protected int calculateNewCapacity(int proposedCapacity) {
        int newCapacity = 1;
        if (proposedCapacity > MAXIMUM_CAPACITY) {
            newCapacity = MAXIMUM_CAPACITY;
        } else {
            while (newCapacity < proposedCapacity) {
                newCapacity <<= 1;  
            }
            if (newCapacity > MAXIMUM_CAPACITY) {
                newCapacity = MAXIMUM_CAPACITY;
            }
        }
        return newCapacity;
    }
    
    /**
     * Вычисляет новый порог для map, при достижении которого количество ячеек 
     * должно быть увеличено
     * 
     * @param newCapacity  новое количество ячеек
     * @param factor  коэффициент заполнения
     * @return новое пороговое значение
     */
    protected int calculateThreshold(int newCapacity, float factor) {
        return (int) (newCapacity * factor);
    }
    
    /**
     * Получить следующую запись.
     * 
     * @param entry  запрос, не должен быть null
     * @return следующая запись
     * @throws NullPointerException если запись null
     */
    protected HashEntry entryNext(HashEntry entry) {
        return entry.next;
    }
    
    /**
     * Получить поле <code>hashCode</code> из <code>HashEntry</code>.
     * 
     * @param entry  запись запроса, не должна быть null
     * @return поле <code>hashCode</code> из записи
     * @throws NullPointerException если запись null
     */
    protected int entryHashCode(HashEntry entry) {
        return entry.hashCode;
    }
    
    /**
     * Получить поле <code>key</code> из <code>HashEntry</code>.
     * 
     * @param entry  запись запроса, не должна быть null
     * @return поле <code>key</code> из записи
     * @throws NullPointerException если запись null
     */
    protected Object entryKey(HashEntry entry) {
        return entry.key;
    }
    
    /**
     * Получить поле <code>value</code> из <code>HashEntry</code>.
     * 
     * @param entry  запись запроса, не должна быть null
     * @return поле <code>value</code> из записи
     * @throws NullPointerException если запись null
     */
    protected Object entryValue(HashEntry entry) {
        return entry.value;
    }
    
    /**
     * Получить итератор для map.
     * 
     * @return the map iterator
     */
    @Override
    public MapIterator mapIterator() {
        if (size == 0) {
            return EmptyMapIterator.INSTANCE;
        }
        return new HashMapIterator(this);
    }

    /**
     * Реализация MapIterator.
     */
    protected static class HashMapIterator extends HashIterator implements MapIterator {
        
        protected HashMapIterator(AbstractHashedMap parent) {
            super(parent);
        }

        @Override
        public Object next() {
            return super.nextEntry().getKey();
        }

        @Override
        public Object getKey() {
            HashEntry current = currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.GETKEY_INVALID);
            }
            return current.getKey();
        }

        @Override
        public Object getValue() {
            HashEntry current = currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }
            return current.getValue();
        }

        @Override
        public Object setValue(Object value) {
            HashEntry current = currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.SETVALUE_INVALID);
            }
            return current.setValue(value);
        }
    }
    
    /**
     * Получить набор хранимых записей entrySet.
     * 
     * @return набор хранимых записей entrySet
     */
    @Override
    public Set entrySet() {
        if (entrySet == null) {
            entrySet = new EntrySet(this);
        }
        return entrySet;
    }
    
    /**
     * Создать итератор для набора записей.
     * Subclasses can override this to return iterators with different properties.
     * 
     * @return итератор для entrySet
     */
    protected Iterator createEntrySetIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new EntrySetIterator(this);
    }

    /**
     * Реализация EntrySet.
     */
    protected static class EntrySet extends AbstractSet {
        /** Родительский map */
        protected final AbstractHashedMap parent;
        
        protected EntrySet(AbstractHashedMap parent) {
            super();
            this.parent = parent;
        }

        @Override
        public int size() {
            return parent.size();
        }
        
        @Override
        public void clear() {
            parent.clear();
        }
        
        @Override
        public boolean contains(Object entry) {
            if (entry instanceof Map.Entry) {
                Map.Entry e = (Map.Entry) entry;
                Entry match = parent.getEntry(e.getKey());
                return (match != null && match.equals(e));
            }
            return false;
        }
        
        @Override
        public boolean remove(Object obj) {
            if (obj instanceof Map.Entry == false) {
                return false;
            }
            if (contains(obj) == false) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            parent.remove(key);
            return true;
        }

        @Override
        public Iterator iterator() {
            return parent.createEntrySetIterator();
        }
    }

    /**
     * EntrySet итератор.
     */
    protected static class EntrySetIterator extends HashIterator {
        
        protected EntrySetIterator(AbstractHashedMap parent) {
            super(parent);
        }

        @Override
        public Object next() {
            return super.nextEntry();
        }
    }

    /**
     * Получить представление набора ключей keySet для map.
     * 
     * @return представление набора ключей
     */
    @Override
    public Set keySet() {
        if (keySet == null) {
            keySet = new KeySet(this);
        }
        return keySet;
    }

    /**
     * Создать итератор для набора ключей.
     * 
     * @return итерато для keySet
     */
    protected Iterator createKeySetIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new KeySetIterator(this);
    }

    /**
     * Реализация KeySet.
     */
    protected static class KeySet extends AbstractSet {
        /** The parent map */
        protected final AbstractHashedMap parent;
        
        protected KeySet(AbstractHashedMap parent) {
            super();
            this.parent = parent;
        }

        @Override
        public int size() {
            return parent.size();
        }
        
        @Override
        public void clear() {
            parent.clear();
        }
        
        @Override
        public boolean contains(Object key) {
            return parent.containsKey(key);
        }
        
        @Override
        public boolean remove(Object key) {
            boolean result = parent.containsKey(key);
            parent.remove(key);
            return result;
        }

        @Override
        public Iterator iterator() {
            return parent.createKeySetIterator();
        }
    }

    /**
     * Итератор для KeySet.
     */
    protected static class KeySetIterator extends EntrySetIterator {
        
        protected KeySetIterator(AbstractHashedMap parent) {
            super(parent);
        }

        @Override
        public Object next() {
            return super.nextEntry().getKey();
        }
    }
    
    /**
     * Получить значения из map.
     * 
     * @return значения
     */
    @Override
    public Collection values() {
        if (values == null) {
            values = new Values(this);
        }
        return values;
    }

    /**
     * Создать итератор по значениям.
     * 
     * @return the values iterator
     */
    protected Iterator createValuesIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new ValuesIterator(this);
    }

    /**
     * Values implementation.
     */
    protected static class Values extends AbstractCollection {
        /** The parent map */
        protected final AbstractHashedMap parent;
        
        protected Values(AbstractHashedMap parent) {
            super();
            this.parent = parent;
        }

        @Override
        public int size() {
            return parent.size();
        }
        
        @Override
        public void clear() {
            parent.clear();
        }
        
        @Override
        public boolean contains(Object value) {
            return parent.containsValue(value);
        }
        
        @Override
        public Iterator iterator() {
            return parent.createValuesIterator();
        }
    }

    /**
     * Итератор по значениям.
     */
    protected static class ValuesIterator extends HashIterator {
        
        protected ValuesIterator(AbstractHashedMap parent) {
            super(parent);
        }

        @Override
        public Object next() {
            return super.nextEntry().getValue();
        }
    }
    
    /**
     * Класс HashEntry используемый для хранения данных.
     */
    protected static class HashEntry implements Map.Entry, KeyValue {
        /** Следуюая запись в hash цепочке */
        protected HashEntry next;
        /** hash код ключа */
        protected int hashCode;
        /** Ключ */
        protected Object key;
        /** Значение */
        protected Object value;
        
        protected HashEntry(HashEntry next, int hashCode, Object key, Object value) {
            super();
            this.next = next;
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }
        
        @Override
        public Object getKey() {
            return (key == NULL ? null : key);
        }
        
        @Override
        public Object getValue() {
            return value;
        }
        
        @Override
        public Object setValue(Object value) {
            Object old = this.value;
            this.value = value;
            return old;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Map.Entry == false) {
                return false;
            }
            Map.Entry other = (Map.Entry) obj;
            return
                (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey())) &&
                (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()));
        }
        
        @Override
        public int hashCode() {
            return (getKey() == null ? 0 : getKey().hashCode()) ^
                   (getValue() == null ? 0 : getValue().hashCode()); 
        }
        
        @Override
        public String toString() {
            return new StringBuffer().append(getKey()).append('=').append(getValue()).toString();
        }
    }
    
    /**
     * Базовый итератор
     */
    protected static abstract class HashIterator implements Iterator {
        
        /** Родительская map */
        protected final AbstractHashedMap parent;
        /** Текущий индекс в массиве */
        protected int hashIndex;
        /** Последняя возвращаемая запись */
        protected HashEntry last;
        /** Следующая запись */
        protected HashEntry next;
        /** Ожидается количество модификации*/
        protected int expectedModCount;
        
        protected HashIterator(AbstractHashedMap parent) {
            super();
            this.parent = parent;
            HashEntry[] data = parent.data;
            int i = data.length;
            HashEntry next = null;
            while (i > 0 && next == null) {
                next = data[--i];
            }
            this.next = next;
            this.hashIndex = i;
            this.expectedModCount = parent.modCount;
        }

        @Override
        public boolean hasNext() {
            return (next != null);
        }

        protected HashEntry nextEntry() { 
            if (parent.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            HashEntry newCurrent = next;
            if (newCurrent == null)  {
                throw new NoSuchElementException(AbstractHashedMap.NO_NEXT_ENTRY);
            }
            HashEntry[] data = parent.data;
            int i = hashIndex;
            HashEntry n = newCurrent.next;
            while (n == null && i > 0) {
                n = data[--i];
            }
            next = n;
            hashIndex = i;
            last = newCurrent;
            return newCurrent;
        }

        protected HashEntry currentEntry() {
            return last;
        }
        
        @Override
        public void remove() {
            if (last == null) {
                throw new IllegalStateException(AbstractHashedMap.REMOVE_INVALID);
            }
            if (parent.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            parent.remove(last.getKey());
            last = null;
            expectedModCount = parent.modCount;
        }

        @Override
        public String toString() {
            if (last != null) {
                return "Iterator[" + last.getKey() + "=" + last.getValue() + "]";
            } else {
                return "Iterator[]";
            }
        }
    }
    
    //-----------------------------------------------------------------------
    /**
     * Запись данных map в поток stream.
     * 
     * @param out  выходной поток
     * @throws java.io.IOException ошибки потока
     */
    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeFloat(loadFactor);
        out.writeInt(data.length);
        out.writeInt(size);
        for (MapIterator it = mapIterator(); it.hasNext();) {
            out.writeObject(it.next());
            out.writeObject(it.getValue());
        }
    }

    /**
     * Чтение данных map из потока stream.
     * 
     * @param in  входной поток
     * @throws java.io.IOException ошибки потока
     * @throws java.lang.ClassNotFoundException
     */
    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        loadFactor = in.readFloat();
        int capacity = in.readInt();
        int size = in.readInt();
        init();
        threshold = calculateThreshold(capacity, loadFactor);
        data = new HashEntry[capacity];
        for (int i = 0; i < size; i++) {
            Object key = in.readObject();
            Object value = in.readObject();
            put(key, value);
        }
    }
    
    //-----------------------------------------------------------------------
    /**
     * Клонирование map без клонирования ключей и значений.
     *
     * @return неглубокий клон
     * @throws java.lang.CloneNotSupportedException клонирование не поддерживается
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            AbstractHashedMap cloned = (AbstractHashedMap) super.clone();
            cloned.data = new HashEntry[data.length];
            cloned.entrySet = null;
            cloned.keySet = null;
            cloned.values = null;
            cloned.modCount = 0;
            cloned.size = 0;
            cloned.init();
            cloned.putAll(this);
            return cloned;
            
        } catch (CloneNotSupportedException ex) {
            return null;  // should never happen
        }
    }
    
    /**
     * Сравнение этого map с другим.
     * 
     * @param obj  объект для сравнения
     * @return true если равны
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map == false) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        MapIterator it = mapIterator();
        try {
            while (it.hasNext()) {
                Object key = it.next();
                Object value = it.getValue();
                if (value == null) {
                    if (map.get(key) != null || map.containsKey(key) == false) {
                        return false;
                    }
                } else {
                    if (value.equals(map.get(key)) == false) {
                        return false;
                    }
                }
            }
        } catch (ClassCastException ignored)   {
            return false;
        } catch (NullPointerException ignored) {
            return false;
        }
        return true;
    }

    /**
     * Получить hashCode стандартного Map.
     * 
     * @return hash код определенный в стандартном Map интерфейсе
     */
    @Override
    public int hashCode() {
        int total = 0;
        Iterator it = createEntrySetIterator();
        while (it.hasNext()) {
            total += it.next().hashCode();
        }
        return total;
    }

    /**
     * Получить представление map как String.
     * 
     * @return a string version of the map
     */
    @Override
    public String toString() {
        if (size() == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(32 * size());
        buf.append('{');

        MapIterator it = mapIterator();
        boolean hasNext = it.hasNext();
        while (hasNext) {
            Object key = it.next();
            Object value = it.getValue();
            buf.append(key == this ? "(this Map)" : key)
               .append('=')
               .append(value == this ? "(this Map)" : value);

            hasNext = it.hasNext();
            if (hasNext) {
                buf.append(',').append(' ');
            }
        }

        buf.append('}');
        return buf.toString();
    }
}
