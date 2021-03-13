package com.yiyi.crm.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

@SuppressWarnings("all")
public abstract class BaseService<T, ID> {

  @Autowired
  private BaseMapper<T, ID> baseMapper;

  /**
   * Insert records.
   * @param entity
   * @return The number of rows inserted.
   */
  public Integer insertSelective(T entity) throws DataAccessException{
    return baseMapper.insertSelective(entity);
  }

  /**
   * Insert record.
   * @param entity
   * @return The key.
   */
  public ID insertHasKey(T entity) throws DataAccessException{
    baseMapper.insertHasKey(entity);
    try {
      return (ID) entity.getClass().getMethod("getId").invoke(entity);
    } catch (Exception e) {
      e.printStackTrace();
      return  null;
    }
  }

  /**
   * Batch insertions.
   * @param entities
   * @return
   */
  public Integer insertBatch(List<T> entities) throws DataAccessException{
    return baseMapper.insertBatch(entities);
  }


  /**
   * Query by id.
   * @param id
   * @return
   */
  public T selectByPrimaryKey(ID id) throws DataAccessException{
    return baseMapper.selectByPrimaryKey(id);
  }


  /**
   * Query by multiple conditions.
   * @param baseQuery
   * @return
   */
  public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException{
    return baseMapper.selectByParams(baseQuery);
  }


  /**
   * Update records.
   * @param entity
   * @return
   */
  public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException{
    return baseMapper.updateByPrimaryKeySelective(entity);
  }


  /**
   * Batch updates.
   * @param entities
   * @return
   */
  public Integer updateBatch(List<T> entities) throws DataAccessException{
    return baseMapper.updateBatch(entities);
  }

  /**
   * Delete single record.
   * @param id
   * @return
   */
  public Integer deleteByPrimaryKey(ID id) throws DataAccessException{
    return baseMapper.deleteByPrimaryKey(id);
  }

  /**
   * Batch deletes.
   * @param ids
   * @return
   */
  public Integer deleteBatch(ID[] ids) throws DataAccessException{
    return baseMapper.deleteBatch(ids);
  }

  public Map<String, Object> queryByParamsForTable(BaseQuery baseQuery) {
    Map<String,Object> result = new HashMap<>();
    PageHelper.startPage(baseQuery.getPage(), baseQuery.getLimit());
    PageInfo<T> pageInfo = new PageInfo<T>(selectByParams(baseQuery));
    result.put("count", pageInfo.getTotal());
    result.put("data", pageInfo.getList());
    result.put("code", 0);
    result.put("msg", "");
    return result;
  }

}
