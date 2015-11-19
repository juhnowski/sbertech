/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */


import testapp.cache.KeysInMemoryCache; 

/**
 * Класс смоки теста кэша
 * @author Илья Юхновский
 */
public class KeysInMemoryCacheSmokeTest {
        public static void main(String[] args) throws InterruptedException {
 
        KeysInMemoryCacheSmokeTest keysCache = new KeysInMemoryCacheSmokeTest();
 
        System.out.println("\n\n==========Test1: keysTestAddRemoveObjects ==========");
        keysCache.keysTestAddRemoveObjects();
        System.out.println("\n\n==========Test2: keysTestExpiredCacheObjects ==========");
        keysCache.keysTestExpiredCacheObjects();
        System.out.println("\n\n==========Test3: keysTestObjectsCleanupTime ==========");
        keysCache.keysTestObjectsCleanupTime();
    }
 
    private void keysTestAddRemoveObjects() {
 
        // timeToLiveInSeconds = 200 seconds
        // timerIntervalInSeconds = 500 seconds
        // maxItems = 6
        KeysInMemoryCache<String, String> cache = new KeysInMemoryCache<String, String>(200, 500, 6);
 
        cache.put("Key 1", "Value 1");
        cache.put("Key 2", "Value 2");
        cache.put("Key 3", "Value 3");
        cache.put("Key 4", "Value 4");
        cache.put("Key 5", "Value 5");
        cache.put("Key 6", "Value 6");
 
        System.out.println("Добавлено 6 кэшируемых объектов... cache.size(): " + cache.size());
        cache.remove("Key 3 ");
        System.out.println("Один объект удален.. cache.size(): " + cache.size());
 
        cache.put("Key 7", "Value 7");
        cache.put("Key 8", "Value 8");
        cache.put("Key 9", "Value 9");
        System.out.println("Два объекта были добавлены для достижения maxItems.. cache.size(): " + cache.size());
 
    }
 
    private void keysTestExpiredCacheObjects() throws InterruptedException {
 
        KeysInMemoryCache<String, String> cache = new KeysInMemoryCache<String, String>(1, 1, 10);
 
        cache.put("Key 1", "Value 1");
        cache.put("Key 2", "Value 2");
        Thread.sleep(3000);
 
        System.out.println("Два добавленных объекта прожили свое время timeToLive. cache.size(): " + cache.size());
 
    }
 
    private void keysTestObjectsCleanupTime() throws InterruptedException {
        int size = 500000;
 
        KeysInMemoryCache<String, String> cache = new KeysInMemoryCache<String, String>(60, 60, 500000);
 
        for (int i = 0; i < size; i++) {
            String value = Integer.toString(i);
            cache.put(value, value);
        }
 
        Thread.sleep(200);
 
        long start = System.currentTimeMillis();
        cache.cleanup();
        double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
 
        System.out.println("Затраченное время на удаление " + size + " объектов " + finish + " сек.");
 
    }
}
