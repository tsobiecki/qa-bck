package com.applause.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface BugRepository {

    HashMap<Integer, Integer> getBugsBy(List<Integer> testersIds, Set<Integer> devicesIds);
}
