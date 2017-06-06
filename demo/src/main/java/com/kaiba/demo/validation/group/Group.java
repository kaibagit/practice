package com.kaiba.demo.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by luliru on 2017/6/6.
 */
@GroupSequence({Default.class, GroupA.class, GroupB.class})
public interface Group {
}
