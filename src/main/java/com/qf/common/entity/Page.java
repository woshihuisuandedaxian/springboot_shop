package com.qf.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
   Integer pageNum=1;
   Integer pageSize=6;
}
