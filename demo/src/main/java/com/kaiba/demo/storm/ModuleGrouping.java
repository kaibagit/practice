//package com.kaiba.demo.storm;
//
//import backtype.storm.task.TopologyContext;
//import backtype.storm.tuple.Fields;
//import backtype.storm.grouping.CustomStreamGrouping;
//import backtype.storm.tuple.Tuple;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by kaiba on 2016/9/6.
// */
//public class ModuleGrouping implements CustomStreamGrouping, Serializable {
//    int numTasks = 0;
//
//    public List<Integer> chooseTasks(List<Object> values) {
//        List<Integer> boltIds = new ArrayList<Integer>();
//        if(values.size()>0){
//            String str = values.get(0).toString();
//            if(str.isEmpty()){
//                boltIds.add(0);
//            }else{
//                boltIds.add(str.charAt(0) % numTasks);
//            }
//        }
//        return boltIds;
//    }
//
//    public void prepare(TopologyContext context, Fields outFields, List<Integer> targetTasks) {
//        numTasks = targetTasks.size();
//    }
//
//    @Override
//    public void prepare(int i) {
//
//    }
//
//    @Override
//    public List<Integer> taskIndices(Tuple tuple) {
//        return null;
//    }
//}
