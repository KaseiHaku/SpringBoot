package kasei.springboot.repository.slaver.dao.dynamicsqlsupport;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class PersonDSS {

    public static final  PersonTable PERSON_TABLE = new PersonTable();
    public static final SqlColumn<String> id = PERSON_TABLE.id;
    public static final SqlColumn<String> name = PERSON_TABLE.name;
    public static final SqlColumn<Integer> age = PERSON_TABLE.age;


    public static class PersonTable extends SqlTable {
        public final SqlColumn<String> id = this.column("id", JDBCType.VARCHAR);
        public final SqlColumn<String> name = this.column("name", JDBCType.VARCHAR);
        public final SqlColumn<Integer> age = this.column("age", JDBCType.INTEGER);


        public PersonTable() {
            super("person");
        }
    }


}
