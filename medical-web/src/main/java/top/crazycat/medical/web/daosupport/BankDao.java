package top.crazycat.medical.web.daosupport;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
@Component
public class BankDao extends AbstractJdbcDaoSupport<BankDO> {
    @Override
    protected String tableName() {
        return "bank";
    }
    public List<BankDO> queryAll(){
        return query(BaseQuery.getInstance(new BankDO()));
    }

    public List<BankDO> queryById(String bankId) {
        BankDO b = new BankDO();
        b.setBankId(bankId);
        return super.query(BaseQuery.getInstance(b));
    }
}
