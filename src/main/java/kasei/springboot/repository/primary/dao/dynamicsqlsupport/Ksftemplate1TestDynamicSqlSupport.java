package kasei.springboot.repository.primary.dao.dynamicsqlsupport;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class Ksftemplate1TestDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Ksftemplate1Test ksftemplate1Test = new Ksftemplate1Test();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> wfOrunid = ksftemplate1Test.wfOrunid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = ksftemplate1Test.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> age = ksftemplate1Test.age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Ksftemplate1Test extends SqlTable {
        public final SqlColumn<String> wfOrunid = column("WF_ORUNID", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> age = column("AGE", JDBCType.VARCHAR);

        public Ksftemplate1Test() {
            super("KSFTEMPLATE1_TEST");
        }
    }
}
