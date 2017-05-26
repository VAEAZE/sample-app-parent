package com.senvon.sample.dao;

import com.istock.base.ibatis.IbatisDaoAnnotation4Template;
import com.istock.base.ibatis.IbatisTemplate;
import com.istock.base.ibatis.model.Page;
import com.senvon.sample.model.GoodsInfo;
import com.senvon.sample.model.GoodsInfoExample;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("GoodsInfoDAO")
public class GoodsInfoDAOImpl extends IbatisDaoAnnotation4Template implements GoodsInfoDAO {
    @Resource(name="sqlMapClientTemplate")
    private IbatisTemplate template;

    public GoodsInfoDAOImpl() {
        super();
    }

    public int countByExample(GoodsInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TB_GOODS_INFO.countByExample", example);
        return count;
    }

    public int deleteByExample(GoodsInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("TB_GOODS_INFO.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        GoodsInfo _key = new GoodsInfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TB_GOODS_INFO.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(GoodsInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_GOODS_INFO.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(GoodsInfo record) {
        Object newKey = getSqlMapClientTemplate().insert("TB_GOODS_INFO.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<GoodsInfo> selectByExample(GoodsInfoExample example) {
        List<GoodsInfo> list = getSqlMapClientTemplate().queryForList("TB_GOODS_INFO.selectByExample", example);
        return list;
    }

    public GoodsInfo selectByPrimaryKey(Integer id) {
        GoodsInfo _key = new GoodsInfo();
        _key.setId(id);
        GoodsInfo record = (GoodsInfo) getSqlMapClientTemplate().queryForObject("TB_GOODS_INFO.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(GoodsInfo record, GoodsInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_GOODS_INFO.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(GoodsInfo record, GoodsInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TB_GOODS_INFO.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(GoodsInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_GOODS_INFO.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(GoodsInfo record) {
        int rows = getSqlMapClientTemplate().update("TB_GOODS_INFO.updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<GoodsInfo> selectByPage(GoodsInfoExample example, Page page) {
        List<GoodsInfo> list = this.searchListPageMyObject("TB_GOODS_INFO.selectByExample", example, page);
        return list;
    }

    public IbatisTemplate getSqlMapClientTemplate() {
        return this.template;
    }

    protected static class UpdateByExampleParms extends GoodsInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, GoodsInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}