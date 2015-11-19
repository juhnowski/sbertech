/*
 * Тестовое задание 1 
 * Для ЗАО "Сбербанк-Технологии" г. Рязань
 */
package testapp.property;

import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testapp.TestApp;

/**
 * Тестовый класс для юнит тестирования класса {@link PropertyValues}.
 * @author Илья Юхновский
 */
public class PropertyValuesTest {
    
    public PropertyValuesTest() {
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
     * Тест метода {@link PropertyValues#getPropValues()}.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPropValues() throws Exception {
        System.out.println("getPropValues");
        PropertyValues instance = new PropertyValues();
        instance.getPropValues();
        assertTrue(
                ((TestApp.THREAD_POOL_SIZE == 14)
                && (TestApp.IN_PATH.equals("C:\\SBERBANK\\IN"))
                && (TestApp.OUT_PATH.equals("C:\\SBERBANK\\OUT"))
                && (TestApp.ERR_PATH.equals("C:\\SBERBANK\\ERR"))
                && (TestApp.SWAP_PATH.equals("C:\\SBERBANK\\SWAP"))
                && (TestApp.SP_PATH.equals("C:\\SBERBANK\\SP"))
                && (TestApp.TMP_PATH.equals("C:\\SBERBANK\\TMP"))
                && (TestApp.TIME_TO_LIVE_IN_SECONDS == 160)
                && (TestApp.TIMER_INTERVAL_IN_SECONDS== 170)
                && (TestApp.MAX_CACHE_SIZE == 1100)
                && (TestApp.WAIT_FILE_TIME == 1100)       
                && (TestApp.RUNTIME_MODE == 1)
                && (TestApp.LOG_LEVEL==Level.FINEST))
        );
    }
    
    /**
     * Тест метода {@link PropertyValues#getPropValues()}.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPropValuesWrongConfigPath() throws Exception {
        System.out.println("getPropValues");
        PropertyValues instance = new PropertyValues("C:\\qqqqqqqqqqqqqq");
        try{
            instance.getPropValues();
            assertTrue(false);
        }catch(Exception e){
            assertTrue(true);
        }

    }    

    /**
     * Тест метода {@link PropertyValues#setThreadPoolSize()}.
     */
    @Test
    public void testSetThreadPoolSize() {
        System.out.println("setThreadPoolSize");
        String threadPoolSize = "200";
        PropertyValues instance = new PropertyValues();
        instance.setThreadPoolSize(threadPoolSize);
        assertTrue(TestApp.THREAD_POOL_SIZE==200);
    }

     /**
     * Тест метода {@link PropertyValues#setThreadPoolSize()}.
     * Проверка условия, что THREAD_POOL_SIZE должен быть неотрицательным
     */
    @Test
    public void testNegativeSetThreadPoolSize() {
        System.out.println("NegativeSetThreadPoolSize");
        String threadPoolSize = "-1";
        PropertyValues instance = new PropertyValues();
        try{
            instance.setThreadPoolSize(threadPoolSize);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        assertFalse(TestApp.THREAD_POOL_SIZE == -1);
    }
    
     /**
     * Тест метода {@link PropertyValues#setThreadPoolSize}.
     * Проверка условия, что THREAD_POOL_SIZE не может быть равен 0
     */    
    @Test
    public void testNullSetThreadPoolSize() {
        System.out.println("NullSetThreadPoolSize");
        String threadPoolSize = "0";
        PropertyValues instance = new PropertyValues();
        try{
            instance.setThreadPoolSize(threadPoolSize);
        } catch(IllegalArgumentException e){
            assertTrue(true);
        }
        assertFalse(TestApp.THREAD_POOL_SIZE == 0);
    }
    
    /**
     * Тест метода {@link PropertyValues#setInPath(inPath)}.
     */
    @Test
    public void testSetInPath() {
        System.out.println("setInPath");
        String inPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setInPath(inPath);
        assertTrue(TestApp.IN_PATH.equals(inPath));
    }

    /**
     * Тест метода {@link PropertyValues#setOutPath(outPath)}.
     */
    @Test
    public void testSetOutPath() {
        System.out.println("setOutPath");
        String outPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setOutPath(outPath);
        assertTrue(TestApp.OUT_PATH.equals(outPath));
    }

    /**
     * Тест метода {@link PropertyValues#setErrPath(errPath)}.
     */
    @Test
    public void testSetErrPath() {
        System.out.println("setErrPath");
        String errPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setErrPath(errPath);
        assertTrue(TestApp.ERR_PATH.equals(errPath));
    }

    /**
     * Тест метода {@link PropertyValues#setSwapPath(swapPath)}.
     */
    @Test
    public void testSetSwapPath() {
        System.out.println("setSwapPath");
        String swapPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setSwapPath(swapPath);
        assertTrue(TestApp.SWAP_PATH.equals(swapPath));    
    }

    /**
     * Тест метода {@link PropertyValues#setSpPath(spPath)}.
     */
    @Test
    public void testSetSpPath() {
        System.out.println("setSpPath");
        String spPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setSpPath(spPath);
        assertTrue(TestApp.SP_PATH.equals(spPath));    
    }

    /**
     * Тест метода {@link PropertyValues#setTmpPath(tmpPath)}.
     */
    @Test 
    public void testSetTmpPath() {
        System.out.println("setTmpPath");
        String tmpPath = "C:\\";
        PropertyValues instance = new PropertyValues();
        instance.setTmpPath(tmpPath);
        assertTrue(TestApp.TMP_PATH.equals(tmpPath));
    }

    /**
     * Тест метода {@link PropertyValues#setTimeToLiveInSeconds(parseParam)}.
     */
    @Test
    public void testSetTimeToLiveInSeconds() {
        System.out.println("setTimeToLiveInSeconds");
        String parseParam = "2";
        PropertyValues instance = new PropertyValues();
        instance.setTimeToLiveInSeconds(parseParam);
        assertTrue(TestApp.TIME_TO_LIVE_IN_SECONDS == 2);
    }

    /**
     * Тест метода {@link PropertyValues#setTimerIntervalInSeconds(parseParam)}.
     */
    @Test
    public void testSetTimerIntervalInSeconds() {
        System.out.println("setTimerIntervalInSeconds");
        String parseParam = "200";
        PropertyValues instance = new PropertyValues();
        instance.setTimerIntervalInSeconds(parseParam);
        assertTrue(TestApp.TIMER_INTERVAL_IN_SECONDS == 200);
    }

    /**
     * Тест метода {@link PropertyValues#setMaxCacheSize(parseParam)}.
     */
    @Test
    public void testSetMaxCacheSize() {
        System.out.println("setMaxCacheSize");
        String parseParam = "999";
        PropertyValues instance = new PropertyValues();
        instance.setMaxCacheSize(parseParam);
        assertTrue(TestApp.MAX_CACHE_SIZE == 999);
    }

    /**
     * Тест метода {@link PropertyValues#setWaitFileTime(parseParam)}.
     */
    @Test
    public void testSetWaitFileTime() {
        System.out.println("setWaitFileTime");
        String parseParam = "99";
        PropertyValues instance = new PropertyValues();
        instance.setWaitFileTime(parseParam);
        assertTrue(TestApp.WAIT_FILE_TIME == 99);
    }

    /**
     * Тест метода {@link PropertyValues#setRuntimeMode(parseParam)}.
     */
    @Test
    public void testSetRuntimeMode() {
        System.out.println("setRuntimeMode");
        String parseParam = "1";
        PropertyValues instance = new PropertyValues();
        instance.setRuntimeMode(parseParam);
        assertTrue(TestApp.RUNTIME_MODE == 1);
    }

    
    /**
     * Тест метода {@link PropertyValues#setLogLevel(parseParam)}.
     */
    @Test
    public void testSetLogLevel() {
        System.out.println("setLogLevel");
        String parseParam = "CONFIG";
        Level old = TestApp.LOG_LEVEL;
        System.out.println(old);
        PropertyValues instance = new PropertyValues();
        instance.setLogLevel(parseParam);
        System.out.println(TestApp.LOG_LEVEL);
        boolean result = TestApp.LOG_LEVEL.equals(Level.CONFIG);
        TestApp.LOG_LEVEL = old;
        assertTrue(result);
    }
    
}
