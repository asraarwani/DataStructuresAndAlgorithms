package com.some_domain.www.interviewquestions;

import java.util.*;

/**
 * @author : asraar
 * @date : 09-11-2022 11:10 am
 * <p>
 * Reference : https://crunchify.com/clean-expired-element-from-map-while-adding-elements-at-the-same-time-java-timer-timertask-and-futures-complete-examples/
 */
public class HashMapAsCache {

    private Timer timer = new Timer();

    private static final long EXPIRED_TIME_IN_SEC = 5L;

    private static final Map<String, List<CustomMapEntry>> newMap = new HashMap<>();


    public static void main(String[] args) throws InterruptedException {

        HashMapAsCache instance = new HashMapAsCache();
        instance.deleteExpiredEntries(10);
        do {
            instance.addElementToMap();
            Thread.sleep(20000);
        } while (true);
    }

    public String getItem(String key) {
        List<CustomMapEntry> list = newMap.get(key);
        if (list != null && !list.isEmpty() && list.size() > 0)
            return list.get(0).getValue();
        return null;
    }

    public int getSize() {
        return newMap.size();
    }

    private void deleteExpiredEntries(int seconds) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deleteEntries();
            }
        }, 0, seconds * 1000L);
    }


    private void deleteEntries() {
        Date actualExpiredTime = new Date();
        TimeProvider timeProvider = new TimeProviderImpl();
        actualExpiredTime.setTime(timeProvider.getMillis() - EXPIRED_TIME_IN_SEC * 1000L);
        System.out.println("Size : " + getSize());
        Iterator<Map.Entry<String, List<CustomMapEntry>>> iterator = newMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<CustomMapEntry>> entry = iterator.next();
            List<CustomMapEntry> element = entry.getValue();
            while (element.size() > 0 && element.get(0).getExpiry().compareTo(actualExpiredTime.getTime()) < 0) {
                System.out.println("Expired Element Deleted : " + element.get(0).toString());
                element.remove(0);
            }
            if (element.size() == 0) {
                iterator.remove();
            }
        }
    }

    private void addElementToMap() {
        Random random = new Random();
        TimeProvider timeProvider = new TimeProviderImpl();
        for (int i = 0; i < 10; i++) {
            int randomValue = random.nextInt(1000);
            String randomKey = "Key_" + randomValue;
            CustomMapEntry mapEntry = new CustomMapEntry(randomKey, "Value_" + randomValue, timeProvider.getMillis());
            List<CustomMapEntry> list = new ArrayList<>();
            list.add(mapEntry);
            newMap.put(randomKey, list);
            System.out.println("Item added  : " + mapEntry.toString());
        }
    }

    private interface TimeProvider {
        long getMillis();
    }

    private static class TimeProviderImpl implements TimeProvider {
        @Override
        public long getMillis() {
            return System.currentTimeMillis();
        }
    }

    private class CustomMapEntry {
        private String key;
        private String value;
        private Long expiry;

        public CustomMapEntry(String key, String value, Long expiry) {
            this.key = key;
            this.value = value;
            this.expiry = expiry;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getExpiry() {
            return expiry;
        }

        public void setExpiry(Long expiry) {
            this.expiry = expiry;
        }

        @Override
        public String toString() {
            return "CustomMapEntry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", expiry=" + expiry +
                    '}';
        }
    }
}
