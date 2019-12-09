package kasei.springboot.repository.slaver.dao.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

public class PersonDaoMapperSP {

    public static String buildSelectById(final String id) {

        SQL person = new SQL() { // 匿名内部类
            // 以下代码是动态语句块,构造方法执行完再执行该语句块
            {
                SELECT("*");
                FROM("person");
                WHERE("id=" + id);

            }
        };

        return person.toString();
    }
}
