package org.com.lzz.lucene;

import java.util.List;
import java.util.Map;

public interface DataBaseDao {
    public int foodcount();
    public List<Map<String, Object>> queryFood(int start, int rws);
}