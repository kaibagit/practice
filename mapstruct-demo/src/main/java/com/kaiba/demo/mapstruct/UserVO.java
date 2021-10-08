package com.kaiba.demo.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * UserVO1
 * Created by luliru on 2021/10/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    private Integer id;
    private String name;
    private String createTime;
    private LocalDateTime updateTime;
}
