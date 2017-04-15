package com.kaiba.microserver.hystrix;

import java.util.List;

/**
 * Created by luliru on 2017/2/22.
 */
public interface TagService {

    List<String> queryTags(int groudId);
}
