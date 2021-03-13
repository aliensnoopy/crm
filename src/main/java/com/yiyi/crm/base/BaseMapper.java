package com.yiyi.crm.base;

import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * BaseMapper  基本方法定义
 */
public interface BaseMapper<T, ID> {
  /**
   * Add record.
   * @param entity
   * @return The number of inserted rows.
   */
  public Integer insertSelective(T entity) throws DataAccessException;

  /**
   * Insert record.
   * @param entity
   * @return
   */
  public Integer insertHasKey(T entity) throws DataAccessException;

  /**
   * Batch insertion.
   * @param entities
   * @return
   */
  public Integer insertBatch(List<T> entities) throws DataAccessException;


  /**
   * Query by id.
   * @param id
   * @return
   */
  public T selectByPrimaryKey(ID id) throws DataAccessException;


  /**
   * Query by multiple conditions.
   * @param baseQuery
   * @return
   */
  public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;


  /**
   * Update single record.
   * @param entity
   * @return
   */
  public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;


  /**
   * Batch updates.
   * @param entities
   * @return
   */
  public Integer updateBatch(List<T> entities) throws DataAccessException;

  /**
   * Delete single record.
   * @param id
   * @return
   */
  public Integer deleteByPrimaryKey(ID id) throws DataAccessException;

  /**
   * Batch deletes.
   * @param ids
   * @return
   */
  public Integer deleteBatch(ID[] ids) throws DataAccessException;
}
