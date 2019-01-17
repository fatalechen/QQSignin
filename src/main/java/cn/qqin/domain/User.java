package cn.qqin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author:cgz
 * @Description:
 * @Date: create in 15:43 2019/1/16
 * @Version:
 * @Modified by:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString
public class User {
    private String  username;
    private String  password;
}
